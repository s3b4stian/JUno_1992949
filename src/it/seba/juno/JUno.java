package it.seba.juno;

//import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFrame;

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
import it.seba.juno.sound.AudioManager;
import it.seba.juno.view.Main;

public class JUno extends JFrame {

    public static void main(String[] args) {

        JFrame mainFrame = new Main(AudioManager.getInstance());
          
        System.out.println("Hello JUno");

        UnoDeck deck = new UnoDeckSimpleFactory().makeUnoDeck();

        DiscardPile discardPile = new DiscardPile();
        
        UnoPlayers ps = new UnoPlayers();

        //ps.add(new HumanPlayer("Sebastian"));
        ps.add(new NpcPlayer("NPC0", new ColorDropStrategy(discardPile), new MostColorStrategy()));
        ps.add(new NpcPlayer("NPC1", new ValueDropStrategy(discardPile), new RandomColorStrategy()));
        ps.add(new NpcPlayer("NPC2", new ColorDropStrategy(discardPile), new MostColorStrategy()));
        ps.add(new NpcPlayer("NPC3", new ValueDropStrategy(discardPile), new RandomColorStrategy()));

        UnoGame game = new UnoGame(ps, deck, discardPile);
        
        game.dealCardsToPlayers();
        game.start();
    }
}