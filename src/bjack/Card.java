package bjack;

import bjack.CardDeck.S;
import bjack.CardDeck.NUM;

public class Card {

    private String suit;
    private String number;
    private S s;
    private NUM n;
    
    public Card(String suit, String num) {
        this.suit = suit;
        this.number = num;
    }

    public Card(S suit, NUM num) {
        this.s = suit;
        this.n = num;
    }

    public String getSuit() {return suit;}
    public String getNumber() {return number;}
    public S getS() {return s;}
    public NUM getN() {return n;}

    @Override
    // public String toString() {
    //     return "Card{%s of %s}".formatted(number, suit);
    // }

    public String toString() {
        return "Card{%s of %s}".formatted(n.getName(), s.getName());
    }
}
