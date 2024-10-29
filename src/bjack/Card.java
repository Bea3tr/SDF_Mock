package bjack;

import bjack.CardDeck.SUITE;
import bjack.CardDeck.NUMBERE;

public class Card {

    private String suit;
    private String number;
    private SUITE s;
    private NUMBERE n;
    
    public Card(String suit, String num) {
        this.suit = suit;
        this.number = num;
    }

    public Card(SUITE suit, NUMBERE num) {
        this.s = suit;
        this.n = num;
    }

    public String getSuit() {return suit;}
    public String getNumber() {return number;}
    public SUITE getS() {return s;}
    public NUMBERE getN() {return n;}

    @Override
    public String toString() {
        return "Card{%s of %s}".formatted(number, suit);
    }

    public String toStringEnum() {
        return "Card{%s of %s}".formatted(n, s);
    }
}
