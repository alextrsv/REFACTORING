package refactoring.lab2;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import refactoring.lab2.grpc.DictionaryServiceGrpc;
import refactoring.lab2.grpc.DictionaryServiceOuterClass;

/**
 * Hello world!
 *
 */
public class Client {
    public static void main( String[] args ) {

        ManagedChannel channel =  ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext()
                .build();

        System.out.println("Client started");
        UserInteractiveService.start(channel);

    }
}
