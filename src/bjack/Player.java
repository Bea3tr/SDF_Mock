package bjack;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Card> cards;
    private String name;
    private int balance;
    private int bet;
    private int cardValue;

    public List<Card> getCards() {return cards;}
    public String getName() {return name;}

    public int getBalance() {return balance;}
    public void setBalance(int balance) {this.balance = balance;}
    public int getBet() {return bet;}
    public void setBet(int bet) {this.bet = bet;}
    public int getCardValue() {return cardValue;}
    public void setCardValue(int cardValue) {this.cardValue = cardValue;}

    public Player(String name, int balance, int bet) {
        this.cards = new ArrayList<>();
        this.name = name;
        this.balance = balance;
        this.bet = bet;
        this.cardValue = 0;
    }
   

    
    
}
