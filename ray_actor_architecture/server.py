import ray
import string
import random
from name_node import NameNode


def generate_string() -> str:
    random_string = ''.join(random.choice(string.ascii_letters) for _ in range(30))
    return random_string


def show_status(name_node : NameNode) -> None:
    data = ray.get(name_node.show_status.remote())

    for i,d in enumerate(data):
        print(f"NODE {i}")
        for key in d.keys():
            print(f"ID: {key} DATA: {d[key]}")

        print("\n-----------------\n")

def clear_nodes(name_node : NameNode) -> None:
    response = ray.get(name_node.remove_all.remote())
    print(f"\n{response}\n")

def new_data(name_node : NameNode, data_name: str) -> None:
    ray.get(name_node.post.remote(data_name, generate_string()))

def replace_data(name_node : NameNode, data_name : str) -> None:
    response = ray.get(name_node.put.remote(data_name, generate_string()))
    print(f"\n{response}\n")

def delete_data(name_node : NameNode, data_name : str) -> None:
    response = ray.get(name_node.delete.remote(data_name))
    print(f"\n{response}\n")

def show_artefacts(name_node : NameNode) -> None:
    artefacts = ray.get(name_node.get_artefacts.remote())

    for key in artefacts.keys():
        print(f"\nARTEFACT'S NAME: {key}")
        for block in artefacts[key]:
            print(f"\tBLOCK: {block}")
            for node in artefacts[key][block]:
                print(f"\t\tNODE: {node}")

def get_data(name_node : NameNode, data_name : str) -> None:
    response = ray.get(name_node.get.remote(data_name))
    print(f"\n{response}\n")


def test_solution(name_node : NameNode) -> None:

    print("\nCLEARING ALL DATA FROM NODES")

    clear_nodes(name_node)

    new_data(name_node, "TEST1")
    new_data(name_node, "TEST2")
    new_data(name_node, "TEST3")

    print("NEW DATA HAS BEEN ADDED -> TEST1, TEST2, TEST3")

    show_status(name_node)

    print("\n GETTING DATA FOR TEST1:")
    
    get_data(name_node, "TEST1")

    print("REPLEACING DATA FOR TEST5 THAT DOES NOT EXISTS:")

    replace_data(name_node, "TEST5")

    print("REPLEACING DATA FOR TEST1 THAT DOES EXISTS:")

    replace_data(name_node, "TEST1")

    print("DELETING DATA FOR TEST 2: \n")

    delete_data(name_node, "TEST2")

    show_status(name_node)

    print("PRINTING ARTEFACTS")

    show_artefacts(name_node)

    print("\nCLEARING ALL DATA FROM NODES")

    clear_nodes(name_node)

if __name__ == "__main__":

    ray.init()

    print("RAY SERVER STARTED")

    node_amount = int(input("Enter amount of storage nodes you want to create: "))
    split_amount = int(input("Enter amount of splits: "))
    copy_amount = int(input("Enter amount of chunk copies: "))

    name_node = NameNode.remote(node_amount,split_amount,copy_amount)

    while True:
        print("\n")
        print("-> TO PERFORM TESTS INPUT T (ALL DATA IN NODES WILL BE CLEARED!)")
        print("-> TO GENERATE STRING AND ADD IT TO NODES INPUT G AND DATA'S NAME")
        print("-> TO DELETE DATA INPPUT D AND DATA'S NAME")
        print("-> TO REPLACE STRING WITH NEW ONE INPUT R AND DATA'S NAME")
        print("-> TO GET INFORMATION ABOUT DATA INPUT I AND DATA'S NAME")
        print("-> TO GET INFORMATION ABOUT ARTEFACTS INPUT A")
        print("-> TO GET INFORMATION ABOUT STORAGE NODE STATUS INPUT S")
        print("-> TO EXIT PRINT X")

        command = input("Select command: ")
        cmd = command[:1]

        if cmd == "T":
            test_solution(name_node)
        elif cmd == "G":
            new_data(name_node, command[2:])
        elif cmd == "D":
            delete_data(name_node, command[2:])
        elif cmd == "R":
            replace_data(name_node, command[2:])
        elif cmd == "I":
            get_data(name_node, command[2:])
        elif cmd == "X":
            break 
        elif cmd == "A":
            show_artefacts(name_node)
        elif cmd == "S":
            show_status(name_node)
        else:
            print("Unknown command")

    ray.shutdown()

    print("RAY SERVER NO LONGER RUNNING")
