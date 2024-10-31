package bjack;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class BlackJack implements Runnable {

    private final Socket sock;
    private List<Card> banker;

    public BlackJack(Socket s) {
        this.sock = s;
        this.banker = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            // Get input & output streams
            InputStream is = sock.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            OutputStream os = sock.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            // Create deck for playing
            CardDeck cd = new CardDeck();
            List<Card> deck = cd.getDeck();

            // Get client input for # of players, to distribute cards
            int numPlayers = Integer.parseInt(dis.readUTF());
            List<List<Card>> players = cd.distributeCards(deck, banker, numPlayers);

            // Check banker cards
            banker.forEach(System.out::println);
            if(cd.isBlackJack(banker)) {
                dos.writeUTF("BlackJack! Banker wins, round over");
                dos.flush();

                dos.close();
                bos.close();
                os.close();

                dis.close();
                bis.close();
                is.close();
                sock.close();
            } else {
                
            }



            



        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    
    
}
