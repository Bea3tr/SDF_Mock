// Package bjack
1. Card.java
    - Card (String suit, String num)
    - Card (S suit, NUM num)
    - enum NUM {NAME (VALUE)}
        - get int value of card with Card.getN().getNum();

2. CardDeck.java (Methods)
    a. createDeck (List<Card> deck)
        - creates shuffled deck of cards (using String[] of suit and number)

    b. createDeckEnum (List<Card> deck) 
        - creates shuffled deck of cards (using enum)

    c. draw (List<Card> deck, List<Card> player)
        - add top card (idx 0) to player
        - remove top card from deck
    
    d. List<List<Card>> distributeCards(List<Card> deck, List<Card> banker, int numPlayer)
        - distribute 2 cards to each participant
        - remove cards from deck
        - return a List of players with their cards

    e. openCard(List<List<Card>> players, int idx)
        - choose a player to see their cards
        - only by banker (server)

    f. int cardVal(List<Card> player)
        - returns value of player's cards
        - if ace and player has 2 cards -> ace = 11

    g. String checkHigher(List<Card> banker, List<Card> player)
        - compare card values of bank and player
        - output string B / P / S

    h. boolean isBlackJack(List<Card> player)
        - called after distributing cards
        - check if banker / player won

=============================================================


