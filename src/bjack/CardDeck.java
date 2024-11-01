package bjack;

import java.util.*;

public class CardDeck {

    private List<Card> deck = new ArrayList<>();
    private String[] SUIT = { "HEARTS", "SPADE", "DIAMOND", "CLUB" };
    private String[] NUMBER = { "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN",
            "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING" };

    enum S {
        HEARTS, SPADE, DIAMOND, CLUB
    };

    enum NUM {
        ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10),
        KING(10);

        private int num;

        private NUM(int n) {
            this.num = n;
        }

        public int getNum() {
            return this.num;
        }
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

    public List<List<Card>> distributeCards(List<Card> deck, List<Card> banker, int numPlayers) {
        List<List<Card>> players = new ArrayList<>();
        for (int n = 0; n < numPlayers; n++) {
            players.add(new ArrayList<>());
        }
        for (int i = 0; i < 2; i++) {
            Card topCard = deck.get(0);
            banker.add(topCard);
            deck.remove(topCard);
            for (int j = 0; j < numPlayers; j++) {
                List<Card> player = players.get(j);
                topCard = deck.get(0);
                player.add(topCard);
                deck.remove(topCard);
            }
        }
        return players;
    }

    public void openCard(List<List<Card>> players, List<Card> banker, int idx) {
        List<Card> player = players.get(idx);
        player.forEach(System.out::println);
        String winner = checkHigher(banker, player);
        if (winner.equals("B")) {
            System.out.println("Banker has a higher value");
        } else if (winner.equals("P")) {
            System.out.println("Player " + players.indexOf(player) + 1 + " has a higher value");
        } else {
            System.out.println("Player has gotten away");
        }
        players.remove(player);
    }

    public void openAll(List<List<Card>> players, List<Card> banker) {
        for (List<Card> player : players) {
            System.out.println("Player " + players.indexOf(player) + 1 + ":");
            player.forEach(System.out::println);
            String winner = checkHigher(banker, player);
            if (winner.equals("B")) {
                System.out.println("Banker has a higher value");
            } else if (winner.equals("P")) {
                System.out.println("Player " + players.indexOf(player) + 1 + " has a higher value");
            } else {
                System.out.println("Player has gotten away");
            }
        }
    }

    public String showCard(List<List<Card>> players, List<Card> player) {
        String cards = "Player " + players.indexOf(player) + 1 + ":";
        for (Card c : player) {
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
        for (Card c : player) {
            if (c.getN().getNum() == 1 && player.size() == 2)
                value += 11;
            else
                value += c.getN().getNum();
        }
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

    public String checkHigher(List<Card> banker, List<Card> player) {
        int bankerScore = cardValue(banker);
        int playerScore = cardValue(player);
        if (bankerScore > playerScore)
            return "B";
        else if (playerScore > bankerScore)
            return "P";
        else
            return "S";
    }

    public boolean isBlackJack(List<Card> player) {
        if (player.size() == 2 && cardValue(player) == 21)
            return true;
        return false;
    }
}
