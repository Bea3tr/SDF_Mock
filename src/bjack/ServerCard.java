package bjack;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class ServerCard {
    public static void main(String[] args) throws IOException {
        // Default port
        int port = 3000;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        ExecutorService thrPool = Executors.newFixedThreadPool(3);
        ServerSocket server = new ServerSocket(port);
        System.out.println("Waiting for connection...");

        while(true) {
            Socket sock = server.accept();
            System.out.println("Connection established!");

            BlackJack bj = new BlackJack(sock);
            thrPool.submit(bj);
        }
    }

}
