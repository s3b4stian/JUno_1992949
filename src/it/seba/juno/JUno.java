package it.seba.juno;

//import java.util.NoSuchElementException;
import java.util.Scanner;

import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoColor;
import it.seba.juno.card.UnoValue;
import it.seba.juno.deck.DiscardPile;
import it.seba.juno.deck.UnoDeck;
import it.seba.juno.deck.UnoDeckSimpleFactory;
import it.seba.juno.player.HumanDropAction;
import it.seba.juno.player.HumanPlayer;
import it.seba.juno.player.MostColorStrategy;
import it.seba.juno.player.NpcChangeColorAction;
import it.seba.juno.player.NpcPlayer;
import it.seba.juno.player.NpcDropAction;
import it.seba.juno.player.Player;
import it.seba.juno.player.RandomColorStrategy;
import it.seba.juno.player.ColorDropStrategy;
import it.seba.juno.player.UnoPlayers;
import it.seba.juno.player.ValueDropStrategy;

public class JUno {

    /*
     * public static void printPlayerCards(Player... players) { for (Player p :
     * players) { System.out.println("\n" + p.getName() + " cards:"); for (UnoCard
     * card : p.getCards()) {
     * 
     * System.out.println(card); } } }
     */

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
        // HumanPlayer player1 = new HumanPlayer("Sebastian");
        //NpcPlayer player1 = new NpcPlayer("NPC0", new ColorDropStrategy(discardPile), new MostColorStrategy());
        //NpcPlayer npc1 = new NpcPlayer("NPC1", new ColorDropStrategy(discardPile), new MostColorStrategy());
        //NpcPlayer npc2 = new NpcPlayer("NPC2", new ColorDropStrategy(discardPile), new MostColorStrategy());
        // NonPlayerCharacter npc3 = new NonPlayerCharacter("NPC3", new
        // PriorityColorDropBehavior(discardPile));

        UnoPlayers ps = new UnoPlayers();

        //ps.add(new HumanPlayer("Sebastian"));
        ps.add(new NpcPlayer("NPC0", new ColorDropStrategy(discardPile), new MostColorStrategy()));
        ps.add(new NpcPlayer("NPC1", new ValueDropStrategy(discardPile), new RandomColorStrategy()));
        ps.add(new NpcPlayer("NPC2", new ColorDropStrategy(discardPile), new MostColorStrategy()));
        ps.add(new NpcPlayer("NPC3", new ValueDropStrategy(discardPile), new RandomColorStrategy()));

        UnoGame game = new UnoGame(ps, deck, discardPile);
        
        game.dealCardsToPlayers();
        game.start();
        
        //game.pCards();
        
        
        
        // npc2.changeColor();

        //for (Player p : ps) { 
            
        //}
        
        // cards to players
       /* for (int i = 0; i < 7; i++) {
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

        boolean skipped = true;
        // boolean switched = true;
        UnoColor color = null;

        for (Player p : ps) {

            UnoCard currentTopCard = discardPile.getTopCard();

            if (currentTopCard.hasColor()){
                color = currentTopCard.getColor();
            }

            //if (color == null) {
            //    color = currentTopCard.getColor();
            //}
        
            System.out.println();
            System.out.println("Pile: " + currentTopCard);
            System.out.println("Current color: " + color);
            System.out.println("Current player: " + p.getName());

            if (ps.getOrderOfPlay()) {
                System.out.println("Play: clockwise");
            } else {
                System.out.println("Play: anticlockwise");
            }

            if (deck.getCardsInDeck() < 5) {
                System.out.println("need refill");
                // deck.refill(discardPile.reset());
            }


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
                        
                            color = ((NpcChangeColorAction) p).changeColor();
                            
                            System.out.println(p.getName() + " change color to: " + color);
                        }
                
                }
                
            } else {
                boolean next = true;

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
                }
            }

            // switch direction, from the next player
            if (discardPile.getTopCard().getValue().equals(UnoValue.REVERSE)) {
                ps.switchDirection();
            }

            skipped = true;

            System.out.println();

        }*/

        
        /*for (Player p : ps) {
            if (p.isWinner()) {
                System.out.println(p.getName() + " Win ");
                break;
            }
        }*/
    }
}