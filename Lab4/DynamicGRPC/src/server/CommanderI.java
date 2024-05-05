package server;
import io.grpc.stub.StreamObserver;
import generated.*;

import java.util.ArrayList;
import java.util.List;

public class CommanderI extends CommanderGrpc.CommanderImplBase{
    private List<Book> books;

    public CommanderI() {
        this.books = new ArrayList<>();
    }

    @Override
    public void processCommand(CommandRequest request, StreamObserver<CommandResponse> responseObserver) {
        String command = request.getCommand();
        String serverResponse;

        if(command.startsWith("add")){
            String[] data = command.split(" ");
            if(data.length == 3){
                Book newBook = Book.newBuilder().setId(books.size() + 1)
                        .setTitle(data[1])
                        .setPageCount(Integer.parseInt(data[2]))
                        .build();
                books.add(newBook);
                serverResponse = "Book " + data[1] + " has been added to collection";
                System.out.println("Request for new book has been accepted -> Title: " + data[1]);

            }else{
                serverResponse = "Wrong usage of command -> use add with book's title and amount of pages";
            }
        } else if (command.startsWith("count")) {
            serverResponse = "Book collection size is: " + books.size();
            System.out.println("New request for collection size handled");
        } else if (command.startsWith("list")) {
            StringBuilder responseBuilder = new StringBuilder();
            for(Book book : books){
                responseBuilder.append("\nTitle: ").append(book.getTitle());
                responseBuilder.append("\nPage count: ").append(book.getPageCount());
                responseBuilder.append("\nId: ").append(book.getId());
                responseBuilder.append("\n");
            }
            serverResponse = responseBuilder.toString();
            System.out.println("Request for book listing has been handled");
        }else{
            serverResponse = "Unknown command";
            System.out.println("New request for unknown command");
        }

        CommandResponse response = CommandResponse.newBuilder()
                .setResult(serverResponse)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}
