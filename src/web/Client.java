package web;

import java.net.Socket;
import java.io.*;

public class Client {
    public static void main(String[] args) {
        int port = 3000;
        String host = "localhost";
        if(args.length > 0) {
            String[] input = args[0].split(":");
            host = input[0];
            port = Integer.parseInt(input[1]);
        }

        try {
            Socket sock = new Socket(host, port);
            System.out.println("Connected to socket on port " + port);

            // Get input & output streams
            InputStream is = sock.getInputStream();
            DataInputStream dis = new DataInputStream(new BufferedInputStream(is));

            OutputStream os = sock.getOutputStream();
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(os));

            Console cons = System.console();

            String fromServer = "";
            while(fromServer != null) {
                // if(fromServer.startsWith("Terminating"))
                //     break;
                String toServer = cons.readLine(">>> ");
                dos.writeUTF(toServer);
                dos.flush();

                if(toServer.equals("end"))
                    break;
        
                fromServer = dis.readUTF();
                System.out.println("From server: " + fromServer);
                if(fromServer == null)
                    break;
            }
            dos.close();
            os.close();
            dis.close();
            is.close();
            sock.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        

    }
    
}
