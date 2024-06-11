import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Admin {

    private final static String EXCHANGE_NAME = "hospital_exchange";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        String adminQueueName = channel.queueDeclare().getQueue();
        channel.queueBind(adminQueueName, EXCHANGE_NAME, "#");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("Admin received: " + message);
        };

        channel.basicConsume(adminQueueName, true, deliverCallback, consumerTag -> {});

        System.out.println("Admin is monitoring the communication...");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String message = scanner.nextLine();

            if (message.equalsIgnoreCase("exit")) {
                break;
            }

            channel.basicPublish(EXCHANGE_NAME, "broadcast.all", null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("Admin sent: " + message);
        }

        channel.close();
        connection.close();
    }
}

