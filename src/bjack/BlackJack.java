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
            if (cd.isBlackJack(banker)) {
                dos.writeUTF("BlackJack! Banker wins, round over");
                dos.flush();

            } else {
                // Let players see card one at a time
                for (List<Card> player : players) {
                    if (cd.isBlackJack(player)) {
                        dos.writeUTF("Player " + players.indexOf(player) + 1 + " has BlackJack");
                        dos.flush();
                    } else {
                        dos.writeUTF(cd.showCard(players, player));
                        dos.flush();
                    }
                    String fromClient = "";
                    boolean endTurn = false;
                    while (!endTurn) {
                        fromClient = dis.readUTF();
                        if (fromClient == null)
                            break;
                        switch (fromClient) {
                            case "draw":
                                cd.draw(deck, player);
                                dos.writeUTF(cd.showCard(players, player));
                                dos.flush();
                                break;

                            case "end":
                                endTurn = true;
                                break;

                            default:
                        }
                    }
                }
                // After all players have see & drawn their cards
                System.out.println(cd.showCard(banker));
                Console cons = System.console();
                boolean endRound = false;
                while (!endRound) {
                    String fromBanker = cons.readLine("Command: ");
                    String[] terms = fromBanker.split(" ");
                    switch (terms[0]) {
                        case "draw":
                            cd.draw(deck, banker);
                            System.out.println(cd.showCard(banker));
                            break;

                        case "open":
                            if (terms[1].equals("all")) {
                                cd.openAll(players, banker);
                            } else {
                                int idx = Integer.parseInt(terms[1]);
                                cd.openCard(players, banker, idx);
                            }
                            break;

                        case "end":
                            endRound = true;
                            System.out.println("Round ended");
                            dos.writeUTF("Round ended");
                            dos.flush();
                            break;

                        default:

                    }
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
