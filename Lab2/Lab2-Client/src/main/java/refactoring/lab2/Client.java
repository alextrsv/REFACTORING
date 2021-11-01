package refactoring.lab2;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * Hello world!
 *
 */
public class Client {
    public static void main( String[] args ) {

        String host = System.getenv("SERVER_HOST");
        String port =  System.getenv("SERVER_PORT");
        ManagedChannel channel =  ManagedChannelBuilder.forTarget(host + ":" + port)
                .usePlaintext()
                .build();

        System.out.println("Client started");
        UserInteractiveService.start(channel);

    }
}
