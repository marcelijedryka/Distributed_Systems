import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Technic {

    private final static String EXCHANGE_NAME = "hospital_exchange";

    public static void main(String[] argv) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please enter Technic ID and specializations -> ");

        try {
            String line = reader.readLine();
            String[] command = line.split(" ");
            int techID = Integer.parseInt(command[0]);
            List<String> specializations = new ArrayList<>();
            for (int i = 1; i < command.length; i++) {
                specializations.add(command[i]);
            }

            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            for (String specialization : specializations) {
                String queueName = "queue_" + specialization;
                channel.queueDeclare(queueName, true, false, false, null);
                channel.queueBind(queueName, EXCHANGE_NAME, "order." + specialization);

                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                    System.out.println("Technic " + techID + " has received new request: " + message);

                    Random random = new Random();
                    int taskTime = random.nextInt(10);

                    try {
                        System.out.println("Technic " + techID + " is working on results");
                        Thread.sleep(taskTime * 1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    String[] parts = message.split(":");
                    String examType = parts[1];
                    String doctorID = parts[2];
                    String patientInfo = parts[3];
                    String result = examType + ":" + patientInfo + ":done";

                    channel.basicPublish(EXCHANGE_NAME, "result." + doctorID, null, result.getBytes(StandardCharsets.UTF_8));
                    System.out.println("Technic " + techID + " sent: " + result);

                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                };

                channel.basicQos(1);
                channel.basicConsume(queueName, false, deliverCallback, consumerTag -> {});
            }

            String broadcastQueueName = "technic_broadcast_" + techID;
            channel.queueDeclare(broadcastQueueName, false, false, false, null);
            channel.queueBind(broadcastQueueName, EXCHANGE_NAME, "broadcast.all");

            DeliverCallback broadcastCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("Technic " + techID + " received message from Admin: " + message);
            };

            channel.basicConsume(broadcastQueueName, true, broadcastCallback, consumerTag -> {});

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
