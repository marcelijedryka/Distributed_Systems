import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class JavaClient {

    public static void main(String[] args) throws IOException {

        System.out.println("CHAT CLIENT");
        String serverName = "localhost";
        int serverPortNumber = 12345;

        Socket socketTCP = null;
        DatagramSocket socketUDP = null;
        MulticastSocket socketMULTICAST = null;

        try {

            socketTCP = new Socket(serverName, serverPortNumber);
            socketUDP = new DatagramSocket(socketTCP.getLocalPort());
            socketMULTICAST = new MulticastSocket(5000);
            InetAddress addressMulticast = InetAddress.getByName("226.4.5.6");
            socketMULTICAST.joinGroup(addressMulticast);

            InetAddress address = InetAddress.getByName("localhost");

            PrintWriter out = new PrintWriter(socketTCP.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socketTCP.getInputStream()));
            Scanner sc = new Scanner(System.in);

            MulticastSocket finalSocketMULTICAST = socketMULTICAST;

            Thread receiverMulticast = new Thread(() -> {

                try{

                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                    while (true) {

                        finalSocketMULTICAST.receive(receivePacket);
                        String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

                        System.out.println("- \nReceived multicast message from group member: " + receivedMessage + "\nNew message: ");

                    }

                }
                catch (IOException e){}
            });

            Thread receiver = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println(" - \nYou recieved a message! \n" + serverMessage);
                        System.out.print("New message: ");
                    }
                }
                catch (IOException e) {
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println(" - \nDisconnected from the server");
                    }else{
                        System.out.println(" - \nServer connection lost - try restart Client");
                    }

                }
            });

            DatagramSocket finalSocketUDP = socketUDP;

            Thread sender = new Thread(() -> {
                try {

                    String myMessage;
                    while (true) {

                        System.out.print("New message: ");
                        myMessage = sc.nextLine();

                        if (!"/disconnect".equalsIgnoreCase(myMessage) && (!myMessage.startsWith("U ") && (!myMessage.startsWith("M ")))) {
                            out.println(myMessage);
                            out.flush();
                        } else if (myMessage.startsWith("U ")) {
                            String finalMessage = myMessage.substring(2);
                            byte[] sendData = finalMessage.getBytes();
                            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, serverPortNumber);
                            finalSocketUDP.send(sendPacket);
                        } else if (myMessage.startsWith("M ")) {
                            String finalMessage = myMessage.substring(2);
                            byte[] sendData = finalMessage.getBytes();
                            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, addressMulticast , 5000);
                            finalSocketMULTICAST.send(sendPacket);

                        } else {
                            out.println("exit");
                            out.flush();
                            receiver.interrupt();
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println(" - \nDisconnected from the server");
                    out.println("exit");
                    out.flush();
                }
            });

            receiver.start();
            sender.start();
            receiverMulticast.start();

            sender.join();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socketTCP != null) {
                socketTCP.close();
            }
            if(socketUDP != null){
                socketUDP.close();
            }

            if(socketMULTICAST != null){
                socketMULTICAST.leaveGroup(InetAddress.getByName("226.4.5.6"));
                socketMULTICAST.close();
            }
        }

    }
}
