package refactoring.lab2;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import refactoring.lab2.services.DictionaryServiceImpl;

import java.io.IOException;

public class ServerMain {
    public static void main( String[] args ) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080)
                .addService(new DictionaryServiceImpl())
                .build();

        server.start();

        System.out.println("ServerMain started");

        server.awaitTermination();
    }
}
