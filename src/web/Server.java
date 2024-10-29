package web;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Server {
    public static void main(String[] args) {
        int port = 3000;
        if(args.length > 0)
            port = Integer.parseInt(args[0]);
        
        try{
            ExecutorService thrPool = Executors.newFixedThreadPool(3);
            ServerSocket server = new ServerSocket(port);
            System.out.println("Waiting for connection on port " + port);

            while(true) {
                Socket sock = server.accept();
                ClientHandler handler = new ClientHandler(sock);
                thrPool.submit(handler);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
}
