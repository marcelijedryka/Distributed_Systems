from fastapi import FastAPI, HTTPException
from fastapi.responses import HTMLResponse
import requests
import random

app = FastAPI()

# estimating reading time based on recieved informations

def calculate_reading_time(pages):
    
    hours = pages // 60
    minutes = pages - 60 * hours

    return f"{hours}h {minutes}min"

# requesting book reviews from Good Reads

def get_book_reviews(isbn):

    url = f"https://www.goodreads.com/book/review_counts.json?&isbns={isbn}"
    response = requests.get(url)

    if response.status_code == 200:
        data = response.json()
        return data['books'][0]['average_rating']
    else:
        return "-"

# requesting random book from selected category via Google Books (with possible error handling)
    
def get_random_book(category):
    
    url = f"https://www.googleapis.com/books/v1/volumes?q=subject:{category}&maxResults=40"
    response = requests.get(url)

    if response.status_code == 200:
        data = response.json()
        if 'items' in data and data['items']:
            random_book = random.choice(data['items'])
            volume_info = random_book.get('volumeInfo', {})
            title = volume_info.get('title', 'Unknown')
            authors = ", ".join(volume_info.get('authors', ['Unknown']))
            isbn = volume_info.get('industryIdentifiers', [{}])[0].get('identifier', 'Unknown')
            cover_url = volume_info.get('imageLinks', {}).get('thumbnail', None)
            number_of_pages = volume_info.get('pageCount', 'Unknown')
            average_rating= get_book_reviews(isbn)
            if number_of_pages != "Unknown":
                average_reading_time =  calculate_reading_time(number_of_pages)
            else:
                average_reading_time = "-"

            return {
                "title": title,
                "author": authors,
                "cover_url": cover_url,
                "isbn": isbn,
                "number_of_pages": number_of_pages,
                "average_reading_time": average_reading_time,
                "average_rating": average_rating,
            }
        
        else:
            raise ValueError("No books found in the specified category")
    else:
        raise requests.HTTPError(f"Unable to fetch from Google Books API. Status code: {response.status_code}")
    

@app.get("/", response_class = HTMLResponse)
def read_root():
    return """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Recommender</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .book-info {
            text-align: center;
        }

        .book-cover {
            max-width: 200px;
            max-height: 300px;
        }

        .saved-books {
            margin-top: 20px;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <div class="book-info">
        <h1>Book Recommender</h1>
        <label for="category">Select category:</label>
        <select id="category" onchange=this.value>
            <option value="architecture">Architecture</option>
            <option value="fantasy">Fantasy</option>
            <option value="horror">Horror</option>
            <option value="mystery_and_detective_stories">Mystery</option>
            <option value="romance">Romance</option>
            <option value="history">History</option>
            <option value="science">Science</option>
            <option value="travel">Travel</option>
        </select>
        <div id="book-details">
            <p>Choose category.</p>
        </div>
        <div id="error-message" class="error">></div>
        <button onclick="rerollRandomBook()">Roll</button>
        <button onclick="saveBook()">Add to my book list</button>
        <button id="view-button" onclick="toggleSaveButton()">View my book list</button>
    </div>

    <div class="saved-books" id="saved-books" style="display: none;"></div>

        <script>
        let savedBooksDiv = document.getElementById('saved-books');
        let isSavedBooksVisible = false;
        let savedBooks = [];

        function toggleSaveButton() {
            savedBooksDiv.style.display = isSavedBooksVisible ? 'none' : 'block';
            isSavedBooksVisible = !isSavedBooksVisible;
            const viewButton = document.getElementById('view-button');
            viewButton.textContent = isSavedBooksVisible ? 'Hide my book list' : 'View my book list';
        }

        async function getRandomBook(category) {
            await fetch(`/random-book/${category}/`)
                .then(response => {
                    if(!response.ok) {
                        throw new Error('Unable to reach the server.Try again later');
                    }
                    return response.json();
                })
                .then(data => {
                    const bookDetails = document.getElementById('book-details');
                    bookDetails.innerHTML = `
                        <h2>${data.title}</h2>
                        <p>Author: ${data.author}</p>
                        <p>ISBN: ${data.isbn}</p>
                        <p>Pages: ${data.number_of_pages}</p>
                        <p>Estimated reading time: ${data.average_reading_time}</p>
                        <p>Average Rating: ${data.average_rating}</p>
                        ${data.cover_url ? `<img src="${data.cover_url}" class="book-cover">` : ''}
                    `;
                })
                .catch(error => {
                    const errorMessage = document.getElementById('error-message');
                    errorMessage.textContent = error.message;
                });
        }

        function rerollRandomBook() {
            const errorMessage = document.getElementById('error-message');
            errorMessage.textContent = '';

            const categorySelect = document.getElementById('category');
            const category = categorySelect.value;
            getRandomBook(category);
        }

        async function saveBook() {
            const bookDetails = document.getElementById('book-details');
            const title = bookDetails.querySelector('h2').textContent;
            const coverUrl = bookDetails.querySelector('.book-cover') ? bookDetails.querySelector('.book-cover').src : '';
            const savedBook = { title, coverUrl };

            await fetch('/random-book/saved', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(savedBook),
            })
            .then(response => response.json())
            .then(data => {
                console.log(data.message);
                savedBooks.push(savedBook);
                updateSavedBooks();
            })
            .catch(error => console.error('Unable to save book:', error));
        }

        async function showSavedBooks() {
            const savedBooksDiv = document.getElementById('saved-books');
            savedBooksDiv.innerHTML = ''; 

            await fetch('/random-book/saved')
                .then(response => response.json())
                .then(savedBooks => {
                    if (savedBooks.length > 0) {
                        savedBooksDiv.innerHTML = '<h2>My read list:</h2>';
                        savedBooks.forEach((book, index) => {
                            savedBooksDiv.innerHTML += `
                                <div style="display: flex; align-items: center; margin-bottom: 10px;">
                                    <img src="${book.coverUrl}" class="book-cover" style="max-width: 50px; max-height: 75px; margin-right: 10px;">
                                    <p style="margin: 0;"> ${book.title} </p>
                                    <button onclick="deleteBook(${index})">Delete</button>
                                </div>`;
                        });
                    } else {
                        savedBooksDiv.innerHTML = `<p>Your list is empty</p>`;
                    }
                })
                .catch(error => console.error('Unable to fetch book list: ', error));
        }

        async function deleteBook(index) {
            const deletedBook = savedBooks[index];

            await fetch(`/random-book/saved/${index}`, {
                method: 'DELETE'
            })
            .then(response => {
                if (response.ok) {
                    savedBooks.splice(index, 1);
                    updateSavedBooks();
                } else {
                    console.error('Unable to delete book');
                }
            })
            .catch(error => console.error('Unable to delete book: ', error));
        }

        function updateSavedBooks() {
            savedBooksDiv.innerHTML = '';
            showSavedBooks();
        }

        showSavedBooks();
        rerollRandomBook();

    </script>
</body>
</html>

"""

# handling book selection

@app.get("/random-book/{category}/", response_model = dict)
async def read_random_book(category: str):
    try:
        return get_random_book(category)
    except HTTPException as e:
        raise e

saved_books = []

# saving book to book list 

@app.post("/random-book/saved", response_model = dict)
async def add_book_to_saved(book: dict):
    saved_books.append(book)
    return {
        "message": "Book saved successfully",
        "book": book,
        }

# sharing book list resource at request 

@app.get("/random-book/saved")
async def get_saved_books():
    return saved_books

# deleting resource form book list 

@app.delete("/random-book/saved/{index}", response_model=dict)
async def delete_book(index: int):
    if index < 0 or index >= len(saved_books):
        raise HTTPException(status_code = 404, detail= "Unable to delete this item")
    
    deleted_book = saved_books.pop(index)

    return {
        "message": "Book deleted successfully", 
        "deleted_book": deleted_book
        }
