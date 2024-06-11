import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Doctor {

    private final static String EXCHANGE_NAME = "hospital_exchange";

    public static void main(String[] argv) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your doctor ID: ");
        String doctorID = scanner.nextLine();

        String resultQueueName = channel.queueDeclare().getQueue();
        channel.queueBind(resultQueueName, EXCHANGE_NAME, "result." + doctorID);

        Thread resultReceiver = new Thread(() -> {
            try {
                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                    System.out.println("Doctor has received results -> " + message);
                };

                channel.basicConsume(resultQueueName, true, deliverCallback, consumerTag -> {});

                String broadcastQueueName = "doctor_broadcast_" + doctorID;
                channel.queueDeclare(broadcastQueueName, false, false, false, null);
                channel.queueBind(broadcastQueueName, EXCHANGE_NAME, "broadcast.all");

                DeliverCallback broadcastCallback = (consumerTag, delivery) -> {
                    String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                    System.out.println("Doctor " + doctorID + " received message from Admin: " + message);
                };

                channel.basicConsume(broadcastQueueName, true, broadcastCallback, consumerTag -> {});

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        resultReceiver.start();

        while (true){
            System.out.println("Input new request or 'exit' to close program:");
            String[] request = scanner.nextLine().split(" ");

            if("exit".equals(request[0])){
                break;
            }

            if (request.length != 3){
                System.out.println("Invalid request, please try again");
                continue;
            }

            if (!"hip".equals(request[0]) && !"knee".equals(request[0]) && !"elbow".equals(request[0])) {
                System.out.println("Invalid exam type. Please enter 'hip', 'knee', or 'elbow'.");
                continue;
            }
            String order = "order:" + request[0] + ":" + doctorID + ":" + request[1] + request[2];
            channel.basicPublish(EXCHANGE_NAME, "order." + request[0], null, order.getBytes(StandardCharsets.UTF_8));

            System.out.println("Doctor has made a request: " + order);
        }

        resultReceiver.interrupt();
        channel.close();
        connection.close();
        scanner.close();

    }

}
