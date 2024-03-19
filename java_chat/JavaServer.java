import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class JavaServer {

    private static final HashMap<Socket, PrintWriter> clientOutputs = new HashMap<>();
    private static final HashMap<Integer, String> usernameBase = new HashMap<>();

    public static void main(String[] args) throws IOException {

        System.out.println("CHAT SERVER");
        int portNumber = 12345;
        ServerSocket serverSocketTCP = null;
        DatagramSocket serverSocketUDP = null;
        byte[] receiveData = new byte[1024];

        try {

            serverSocketTCP = new ServerSocket(portNumber);
            serverSocketUDP = new DatagramSocket(portNumber);

            // handling UDP

            DatagramSocket finalServerSocketUDP = serverSocketUDP;
            Thread udpHandler = new Thread(() -> {

                try{

                    while (true) {

                        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        finalServerSocketUDP.receive(receivePacket);
                        String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                        int clientPort = receivePacket.getPort();

                        System.out.println("Received [UDP] message from  " + usernameBase.get(clientPort) +": " + receivedMessage);

                        forwardMessage(clientPort, receivedMessage);

                    }

                }catch (IOException e){
                    e.printStackTrace();
                }
            });

            udpHandler.start();


            while (true) {

                //handling TCP

                Socket clientSocket = serverSocketTCP.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                clientOutputs.put(clientSocket, out);
                ClientHandlerTCP client = new ClientHandlerTCP(clientSocket);
                System.out.println("New client " + client.username + " has joined the server");
                Thread t = new Thread(client);
                t.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocketTCP != null) {
                serverSocketTCP.close();
            }
            if (serverSocketUDP != null) {
                serverSocketUDP.close();
            }
        }

    }

    public static void forwardMessage(int senderPort, String message) {

        for (Map.Entry<Socket, PrintWriter> entry : clientOutputs.entrySet()) {
            Socket reciever = entry.getKey();

            if (reciever.getPort() == senderPort) {
                continue;
            }

            PrintWriter out = entry.getValue();
            String newMessage = usernameBase.get(senderPort) + ": " + message;
            out.println(newMessage);
        }
    }

    private static class ClientHandlerTCP implements Runnable {
        private Socket clientSocket;
        private BufferedReader in;
        private String username;

        public ClientHandlerTCP(Socket clientSocket) {

            try {

                this.username = generateUsername();
                this.clientSocket = clientSocket;
                this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                usernameBase.put(clientSocket.getPort(), username);


            } catch (IOException e) {
                System.out.println("Client " + username + " has disconnected.");
                clientOutputs.remove(clientSocket);
                try {
                    clientSocket.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }

        }

        public String generateUsername() {

            String[] partOne = {"Happy", "Sad", "Funny", "Clever", "Brave", "Gentle", "Swift", "Lucky", "Charming", "Kind"};
            String[] partTwo = {"Soldier", "Penguin", "Scientist", "Builder", "Warrior", "Lady", "Bird", "Cat", "Bob", "Snake"};

            Random roll = new Random();

            return partOne[roll.nextInt(partOne.length)] + partTwo[roll.nextInt(partTwo.length)];
        }

        public void run() {

            try {

                String message;

                while ((message = in.readLine()) != null) {

                    if ("exit".equalsIgnoreCase(message)) {
                        System.out.println("Client " + username + " has disconnected.");
                        break;
                    }
                    System.out.println("Received [TCP] message from " + username + ": " + message);
                    forwardMessage(clientSocket.getPort(), message);
                }
            } catch (IOException e) {

                System.out.println("Client " + username + "has disconnected.");
                clientOutputs.remove(clientSocket);

                try {
                    clientSocket.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }

        }

    }

}
