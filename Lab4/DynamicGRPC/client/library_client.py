import grpc
import commander_pb2_grpc, commander_pb2

def main():

    channel = grpc.insecure_channel("localhost:50051")
    client = commander_pb2_grpc.CommanderStub(channel)

    # command_request = commander_pb2.CommandRequest(command="add Wiedzmin 320")
    # response = client.processCommand(command_request)
    # print(response.result)

    # command_request = commander_pb2.CommandRequest(command="add Lalka 630")
    # response = client.processCommand(command_request)

    # print(response.result)

    # command_request = commander_pb2.CommandRequest(command="list")
    # response = client.processCommand(command_request)
    # print(response.result)

    # command_request = commander_pb2.CommandRequest(command="count")
    # response = client.processCommand(command_request)
    # print(response.result)

    while True:
        command = input("Input your command: ")

        if command == 'exit':
            break

        command_request = commander_pb2.CommandRequest(command=command)
        response = client.processCommand(command_request)
        print(response.result)


if __name__ == "__main__":
    main()

