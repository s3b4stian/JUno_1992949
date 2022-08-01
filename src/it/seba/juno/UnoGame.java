package it.seba.juno;

//import java.util.Scanner;

import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoColor;
import it.seba.juno.card.UnoValue;
import it.seba.juno.deck.DiscardPile;
import it.seba.juno.deck.UnoDeck;
//import it.seba.juno.player.HumanDropAction;
import it.seba.juno.player.NpcChangeColorAction;
import it.seba.juno.player.NpcDropAction;
import it.seba.juno.player.Player;
import it.seba.juno.player.UnoPlayers;

public class UnoGame {

    UnoDeck deck;
    DiscardPile discardPile;
    //UnoColor curentColor;
    boolean skipped = true;
    UnoPlayers players;
    Player dealer;
    
    public UnoGame(UnoPlayers p, UnoDeck d, DiscardPile dp) {
        players = p;
        deck = d;
        discardPile = dp;
    }

    public void randomDealer() {
        
    }

    public void dealCardsToPlayers() {
        players.forEach(p -> { for (int i = 0; i < 7; i++) { p.takeCard(deck.dealCard()); }});
    }
    
    public void start() {
        
        // drop first card to discard pile
        discardPile.dropToPile(deck.dealCard());

        for (Player p : players) {

            UnoCard currentTopCard = discardPile.getTopCard();

            //if (currentTopCard.hasColor()){
            //    curentColor = currentTopCard.getColor();
            //}

            //if (color == null) {
            //    color = currentTopCard.getColor();
            //}
        
            System.out.println();
            System.out.println("Pile: " + currentTopCard);
            System.out.println("Current color: " + discardPile.getCurrentColor());
            System.out.println("Current player: " + p.getName());

            if (players.getOrderOfPlay()) {
                System.out.println("Play: clockwise");
            } else {
                System.out.println("Play: anticlockwise");
            }

            //if (deck.getCardsInDeck() < 5) {
            //    System.out.println("need refill");
                // deck.refill(discardPile.reset());
            //}


            if (currentTopCard.getValue().equals(UnoValue.WILD_DRAW_FOUR)) {
                // take 4 cards
                p.takeCard(deck.dealCard());
                p.takeCard(deck.dealCard());
                p.takeCard(deck.dealCard());
                p.takeCard(deck.dealCard());
                
                System.out.println(p.getName() + " take 4 cards");
            }

            if (skipped && currentTopCard.getValue().equals(UnoValue.SKIP)) {

                System.out.println(p.getName() + " skip turn");

                // skip turn
                skipped = false;
                continue;

            }

            if (currentTopCard.getValue().equals(UnoValue.DRAW_TWO)) {
                // take 2 cards
                p.takeCard(deck.dealCard());
                p.takeCard(deck.dealCard());
                
                System.out.println(p.getName() + " take 2 cards");
            }

            System.out.println(p.getName() + " cards:");

            for (UnoCard card : p.getCards()) {
                System.out.println(card);
            }

            if (p.isNpc()) {
                // discardPile.
                UnoCard dropped = ((NpcDropAction) p).dropCard();

                if (dropped == null) {
                    System.out.println(p.getName() + " take one card ");

                    p.takeCard(deck.dealCard());

                    dropped = ((NpcDropAction) p).dropCard();

                    if (dropped instanceof UnoCard) {
                        discardPile.dropToPile(dropped);

                        System.out.println(p.getName() + " dropped " + dropped);
                    }

                } else {

                    discardPile.dropToPile(dropped);

                    System.out.println(p.getName() + " dropped " + dropped);
                
                    
                }

                if (dropped instanceof UnoCard) {
                    if (discardPile.getTopCard().getValue().equals(UnoValue.WILD)
                            || discardPile.getTopCard().getValue().equals(UnoValue.WILD_DRAW_FOUR)) {
                        
                            UnoColor curentColor = ((NpcChangeColorAction) p).changeColor();
                            
                            discardPile.setCurrentColor(curentColor);
                            
                            System.out.println(p.getName() + " change color to: " + curentColor);
                        }
                
                }
                
            } else {
                /*boolean next = true;

                while (next) {

                    int icard = 0;

                    Scanner sn = new Scanner(System.in);
                    icard = Integer.parseInt(sn.nextLine());

                    if (icard == -1) {
                        p.takeCard(deck.dealCard());
                        System.out.println(p.getName() + " take one card ");

                    } else {
                        // discardPile((HumanDropAction) p).dropCard(icard);

                        discardPile.cardMatch(currentTopCard);

                        UnoCard dropped = ((HumanDropAction) p).dropCard(icard);

                        if (discardPile.cardMatch(dropped)) {
                            discardPile.dropToPile(dropped);
                            System.out.println(p.getName() + " dropped " + dropped);
                            next = false;
                        } else {
                            p.takeCard(dropped);
                        }
                    }
                }*/
            }

            // switch direction, from the next player
            if (discardPile.getTopCard().getValue().equals(UnoValue.REVERSE)) {
                players.switchDirection();
            }

            skipped = true;

            System.out.println();

            if (p.isWinner()) {
                break;
            }
            
        }
    }
    
    /*public void pCards() {
        
        players.forEach(p -> {  
            System.out.println(p.getName());
            for (UnoCard card : p.getCards()) {
            System.out.println(card);
        }});
        
    }*/
    
}
