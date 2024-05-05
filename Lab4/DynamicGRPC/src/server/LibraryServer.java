package server;

import generated.Book;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class LibraryServer {
    private Server server;
    private static final Logger logger = Logger.getLogger(LibraryServer.class.getName());

    private void start() throws IOException
    {
        int port = 50051;
        try {
            String address = "127.0.0.5";
            SocketAddress socket = new InetSocketAddress(InetAddress.getByName(address), port);
        } catch(UnknownHostException e) {};


        server = ServerBuilder.forPort(50051).executor((Executors.newFixedThreadPool(16)))
                .addService(new CommanderI())
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("Shutting down gRPC server...");
                LibraryServer.this.stop();
                System.err.println("Server shut down.");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final LibraryServer server = new LibraryServer();
        server.start();
        server.blockUntilShutdown();
    }

}

