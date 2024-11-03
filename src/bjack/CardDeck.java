package bjack;

import java.util.*;
import java.util.stream.*;

public class CardDeck {

    private List<Card> deck = new ArrayList<>();
    private String[] SUIT = { "HEARTS", "SPADE", "DIAMOND", "CLUB" };
    private String[] NUMBER = { "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN",
            "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING" };

    enum S {
        HEARTS("HEARTS"), SPADE("SPADE"), DIAMOND("DIAMOND"), CLUB("CLUB");

        private String name;

        private S(String name) {
            this.name = name;
        }

        public String getName() {return this.name;}
    };

    enum NUM {
        ACE(1, "ACE"), TWO(2, "TWO"), THREE(3, "THREE"), FOUR(4, "FOUR"), FIVE(5, "FIVE"), SIX(6, "SIX"), SEVEN(7, "SEVEN"), EIGHT(8, "EIGHT"), NINE(9, "NINE"), TEN(10, "TEN"), JACK(10, "JACK"), QUEEN(10, "QUEEN"),
        KING(10, "KING");

        private int num;
        private String name;

        private NUM(int n, String name) {
            this.num = n;
            this.name = name;
        }

        public int getNum() {return this.num;}
        public String getName() {return this.name;}
    };

    public List<Card> getDeck() {
        return this.deck;
    }

    public CardDeck() {
        createDeckEnum(deck);
    }

    public void createDeck(List<Card> deck) {
        for (String suit : SUIT) {
            for (String num : NUMBER) {
                Card card = new Card(suit, num);
                deck.add(card);
            }
        }
        Collections.shuffle(deck);
    }

    public void createDeckEnum(List<Card> deck) {
        for (S s : S.values()) {
            for (NUM n : NUM.values()) {
                Card card = new Card(s, n);
                deck.add(card);
            }
        }
        Collections.shuffle(deck);
    }

    // BlackJack Operations
    public void draw(List<Card> deck, List<Card> player) {
        Card topCard = deck.get(0);
        player.add(topCard);
        deck.remove(topCard);
    }

    public List<Player> declarePlayers(String details) {
        List<Player> players = new ArrayList<>();
        // Parse player details (name|bal|bet)
        String[] playerDetails = details.split(" ");
        for (int i = 0; i < playerDetails.length; i++) {
            System.out.println(playerDetails[i]);
            String[] player = playerDetails[i].split("\\|");
            String name = player[0];
            int balance = Integer.parseInt(player[1]);
            int bet = Integer.parseInt(player[2]);
            System.out.println("Name: " + name + " Balance: " + balance + "Bet: " + bet);
            Player p = new Player(name, balance, bet);
            players.add(p);
        }
        return players;
    }

    public void distributeCards(List<Player> players, List<Card> banker, List<Card> deck) {
        for (int d = 0; d < 2; d++) {
            for (int i = 0; i < players.size(); i++) {
                Player p = players.get(i);
                p.getCards().add(deck.remove(0));
                if(i == players.size() - 1) {
                    banker.add(deck.remove(0));
                }
            }
        }
    }

    public void openCard(List<Player> players, List<Card> banker, String name) {
        List<Player> player = players.stream()  
                                        .filter(p -> p.getName().equals(name)) 
                                        .collect(Collectors.toList());
        Player p = player.get(0);
        p.getCards().forEach(System.out::println);
        System.out.println(checkHigher(banker, p));
        players.remove(p);
    }

    public void openAll(List<Player> players, List<Card> banker) {
        for (Player p : players) {
            System.out.println("Player: " + p.getName());
            p.getCards().forEach(System.out::println);
            System.out.println(checkHigher(banker, p));
        }
    }

    public String showCard(Player player) {
        String cards = "Player: " + player.getName();
        for (Card c : player.getCards()) {
            cards += "\n" + c;
        }
        return cards;
    }

    public String showCard(List<Card> banker) {
        String cards = "Banker: ";
        for (Card c : banker)
            cards += "\n" + c;
        return cards;
    }

    public int cardValue(List<Card> player) {
        int value = 0;
        int ace = 0;
        for (Card c : player) {
            if(c.getN() == NUM.ACE)
                ace += 1;
            if (c.getN().getNum() == 1 && player.size() == 2)
                value += 11;
            else
                value += c.getN().getNum();
        }
        if(value > 21)
            value = 0;
        if(ace == 2 && player.size() == 2)
            value = 21;

        return value;
    }

    public boolean hasAce(List<Card> player) {
        boolean hasAce = false;
        for (Card c : player) {
            if (c.getN() == NUM.ACE)
                hasAce = true;
        }
        return hasAce;
    }

    public String checkHigher(List<Card> banker, Player player) {
        int bankerScore = cardValue(banker);
        int playerScore = cardValue(player.getCards());
        if (bankerScore > playerScore){
            player.setBalance(player.getBalance()-player.getBet());
            return "Banker has a higher value. Current balance: " + player.getBalance();
        }
        else if (playerScore > bankerScore){
            player.setBalance(player.getBalance()+player.getBet());
            return "Player has a higher value. Current balance: " + player.getBalance();
        }
        else
            return "Player has gotten away. Current balance: " + player.getBalance();
    }

    public boolean isBlackJack(List<Card> player) {
        if (player.size() == 2 && cardValue(player) == 21)
            return true;
        return false;
    }
}
