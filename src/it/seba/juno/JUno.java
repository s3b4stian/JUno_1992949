package it.seba.juno;

import java.util.ArrayDeque;

import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoValue;
import it.seba.juno.deck.DiscardPile;
import it.seba.juno.deck.UnoDeck;
import it.seba.juno.deck.UnoDeckSimpleFactory;
import it.seba.juno.player.HumanDrop;
import it.seba.juno.player.HumanPlayer;
import it.seba.juno.player.NonPlayerCharacter;
import it.seba.juno.player.NonPlayerCharacterDrop;
import it.seba.juno.player.Player;
import it.seba.juno.player.PriorityColorDropBehavior;
import it.seba.juno.player.UnoPlayers;

public class JUno {

    public static void printPlayerCards(Player... players) {
        for (Player p : players) {
            System.out.println("\n" + p.getName() + " cards:");
            for (UnoCard card : p.getCards()) {

                System.out.println(card.getColor() + " " + card.getValue());
            }
        }
    }

    /*
     * public static void switchDirection(ArrayDeque<Player> p, int n) {
     * 
     * ArrayDeque<Player> tmp = new ArrayDeque<Player>();
     * 
     * for (int i = 1; i < n; i++) { p.offer(p.poll()); }
     * 
     * while (!p.isEmpty()) { tmp.push(p.poll()); }
     * 
     * while (!tmp.isEmpty()) { p.offer(tmp.pop()); }
     * 
     * tmp = null; }
     */

    public static void main(String[] args) {

        System.out.println("Hello JUno");

        // uno deck
        UnoDeck deck = new UnoDeckSimpleFactory().makeUnoDeck();
        // deck discard pile
        DiscardPile discardPile = new DiscardPile();
        // ArrayDeque<Player> players = new ArrayDeque<Player>();

        // players
        HumanPlayer player1 = new HumanPlayer("Sebastian");
        NonPlayerCharacter npc1 = new NonPlayerCharacter("NPC1", new PriorityColorDropBehavior(discardPile));
        NonPlayerCharacter npc2 = new NonPlayerCharacter("NPC2", new PriorityColorDropBehavior(discardPile));
        //NonPlayerCharacter npc3 = new NonPlayerCharacter("NPC3", new PriorityColorDropBehavior(discardPile));

        UnoPlayers ps = new UnoPlayers();

        ps.add(player1);
        ps.add(npc1);
        ps.add(npc2);
        // ps.add(npc3);

        // cards to players
        for (int i = 0; i < 7; i++) {
            player1.takeCard(deck.dealCard());
        }

        for (int i = 0; i < 7; i++) {
            npc1.takeCard(deck.dealCard());
        }

        for (int i = 0; i < 7; i++) {
            npc2.takeCard(deck.dealCard());
        }

        // drop first card to discard pile
        discardPile.dropToPile(deck.dealCard());

        int z = 10;

        System.out.println(player1);
        System.out.println(npc1);
        System.out.println(npc2);

        System.out.println();

        for (Player p : ps) {

            UnoCard currentTopCard = discardPile.getTopCard();
            
            // switch direction, from the next player
            if (currentTopCard.getValue().equals(UnoValue.REVERSE)) {
                ps.switchDirection();
            }
            
            if (currentTopCard.getValue().equals(UnoValue.WILD)) {
               // change current color
            }
            
            if (currentTopCard.getValue().equals(UnoValue.WILD_DRAW_FOUR)) {
                // change current color
                
                // take 4 cards
                p.takeCard(deck.dealCard());
                p.takeCard(deck.dealCard());
                p.takeCard(deck.dealCard());
                p.takeCard(deck.dealCard());
            }
            
            if (currentTopCard.getValue().equals(UnoValue.SKIP)) {
                // skip turn,
                continue;
            }
            
            if (currentTopCard.getValue().equals(UnoValue.DRAW_TWO)) {
                // take 2 cards
                p.takeCard(deck.dealCard());
                p.takeCard(deck.dealCard());

            }

            if (p.isNpc())
                ((NonPlayerCharacterDrop) p).dropCard();
            else
                ((HumanDrop) p).dropCard(0);

            System.out.println(p + " " + ps.getOrderOfPlay());

            if (--z == 5) {
                System.out.println("Switching direction");
                ps.switchDirection();
            }

            if (--z == 0) {

            }
        }

        /*
         * players.offer(player1); players.offer(npc1); players.offer(npc2);
         * players.offer(npc3);
         * 
         * printPlayerCards(player1, npc1, npc2);
         * 
         * for (int i = 0; i < 10; i++) { PlayerInterface currentPlayer =
         * players.poll();
         * 
         * System.out.println(currentPlayer.getName());
         * 
         * players.offer(currentPlayer);
         * 
         * if (i == 4) { System.out.println("Switch Directions");
         * switchDirection(players, players.size()); } }
         */

        // for (UnoCard cc : discardPile) {

        // }

       /* for (int i = 0; i < 10; i++) {

            discardPile.dropToPile(deck.dealCard());
            // System.out.println();

            // UnoCard cardInDiscardCard = discardPile.topCard();

            // System.out.println(cardInDiscardCard.getColor() + " " +
            // cardInDiscardCard.getValue());

        }*/

        /*
         * Player test = new Player("Sebastian"); test.takeCard(deck.card());
         * test.takeCard(deck.card()); test.takeCard(deck.card());
         * test.takeCard(deck.card()); test.takeCard(deck.card());
         * test.takeCard(deck.card()); test.takeCard(deck.card());
         * test.takeCard(deck.card()); test.takeCard(deck.card());
         * test.takeCard(deck.card()); test.takeCard(deck.card());
         * test.takeCard(deck.card());
         * 
         * for (UnoCard card : test.getCards()) {
         * 
         * System.out.println(card.getColor() + " " + card.getValue()); }
         */

        // test.dropCard(5);
        // test.dropCard(0);
        // test.dropCard(0);

        // System.out.println();

        // for (UnoCard card : test.getCards()) {

        // System.out.println(card.getColor() + " " + card.getValue());
        // }

    }

}