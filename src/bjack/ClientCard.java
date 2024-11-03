package bjack;

import java.io.*;
import java.net.Socket;

public class ClientCard {
    
    public static void main(String[] args) {
        int port = 3000;
        String host = "localhost";
        Console cons = System.console();

        try {
            Socket sock = new Socket(host, port);

            // Get input & output streams
            InputStream is = sock.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            OutputStream os = sock.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            String playerDetails = cons.readLine("Enter ALL players' details (name|bal|bet): ");
            dos.writeUTF(playerDetails);
            dos.flush();

            String fromServer = "";
            while(fromServer != null) {
                fromServer = dis.readUTF();
                if(fromServer == null) 
                    break;
                if(!fromServer.contains("BlackJack")) {
                    System.out.println(fromServer);
                    if(fromServer.contains("ended"))
                        break;
                    String action = cons.readLine("[draw / end]: ");
                    dos.writeUTF(action);
                    dos.flush();
                }
            }
            dos.close();
            bos.close();
            os.close();

            dis.close();
            bis.close();
            is.close();

            sock.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        

    }
}
