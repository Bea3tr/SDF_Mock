package bjack;

import java.util.*;

public class CardDeck {

    private String[] SUIT = {"HEARTS", "SPADE", "DIAMOND", "CLUB"};
    private String[] NUMBER = {"ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN",
                                "EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING"};

    enum SUITE {HEARTS, SPADE, DIAMOND, CLUB};
    enum NUMBERE {ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10);
                private int num;
                private NUMBERE(int n) {
                    this.num = n;
                }
                public int getNum() {return this.num;}
            };

    public void createDeck(List<Card> deck) {
        for(String suit : SUIT) {
            for(String num : NUMBER) {
                Card card = new Card(suit, num);
                deck.add(card);
            }
        }
        Collections.shuffle(deck);
    }

    public void createDeckEnum(List<Card> deck) {
        for(SUITE s : SUITE.values()) {
            for(NUMBERE n : NUMBERE.values()) {
                Card card = new Card(s, n);
                deck.add(card);
            }
        }
        Collections.shuffle(deck);
    }
    
}
