package refactor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Client client = new Client("http://localhost:8080/calculator");
        client.startClient();
    }

}
