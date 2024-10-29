package web;

import java.net.Socket;
import java.io.*;

public class ClientHandler implements Runnable {

    private final Socket sock;

    public ClientHandler(Socket s) {
        this.sock = s;
    }

    @Override
    public void run() {
        System.out.println("Connection established!");

        try {
            // Get input & output streams
            InputStream is = sock.getInputStream();
            DataInputStream dis = new DataInputStream(new BufferedInputStream(is));

            OutputStream os = sock.getOutputStream();
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(os));

            String fromClient = "";
            while (fromClient != null) {
                fromClient = dis.readUTF();
                if (fromClient == null)
                    break;
                if (fromClient.equals("end"))
                    break;

                // Input e.g. num operator num (65 * 65)
                String[] input = fromClient.split(" ");
                if (input.length < 3) {
                    dos.writeUTF("Invalid input! Please try again");
                    dos.flush();
                } else {
                    int num1 = Integer.parseInt(input[0]);
                    int num2 = Integer.parseInt(input[2]);
                    int res = 0;
                    switch (input[1]) {
                        case "+":
                            res = num1 + num2;
                            break;

                        case "-":
                            res = num1 - num2;
                            break;

                        case "*":
                            res = num1 * num2;
                            break;

                        case "/":
                            res = num1 / num2;
                            break;
                        
                        default:
                            dos.writeUTF("Unknown operator, please try again");
                            dos.flush();
                            continue;
                    }
                    dos.writeUTF("Result is: " + String.valueOf(res));
                    dos.flush();
                }
            }
            System.out.println("Terminating connection with client...");

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
