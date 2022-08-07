package it.seba.juno;

import java.io.File;

import it.seba.juno.controller.MainController;
import it.seba.juno.controller.MenuController;
import it.seba.juno.controller.OptionsController;
import it.seba.juno.controller.PlayersController;
/*import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JFrame;

import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoColor;
import it.seba.juno.card.UnoValue;
import it.seba.juno.controller.MainController;
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
import it.seba.juno.player.ValueDropStrategy;*/
import it.seba.juno.sound.AudioManager;
import it.seba.juno.view.MainView;
import it.seba.juno.view.MenuView;
import it.seba.juno.view.OptionsView;
import it.seba.juno.view.PlayersView;

public class JUno {

    public static void main(String[] args) {

        // initialize audio manager
        AudioManager audioManager = AudioManager.getInstance();
        // load sounds
        audioManager.addToPlayList("click", (new File("")).getAbsolutePath() + "/src/it/seba/juno/sound/click.wav");

        // initialize views
        MainView mainView = new MainView(audioManager);
        MenuView menuView = new MenuView(audioManager);
        OptionsView optionsView = new OptionsView(audioManager);
        PlayersView playersView = new PlayersView(audioManager);

        // initialize controllers
        MainController mainController = new MainController(mainView, menuView);
        MenuController menuController = new MenuController(mainView, menuView, optionsView, playersView);
        OptionsController optionsController = new OptionsController(mainView, menuView, optionsView);
        PlayersController playersController = new PlayersController(mainView, menuView, playersView);

        // mainController.setDefaultView(menuView);

        // Main mainFrame = new Main(AudioManager.getInstance());

        // mainFrame.getMainMenu().getButtonExit().addActionListener(e -> gameExit());

        /*
         * System.out.println("Hello JUno");
         * 
         * UnoDeck deck = new UnoDeckSimpleFactory().makeUnoDeck();
         * 
         * DiscardPile discardPile = new DiscardPile();
         * 
         * UnoPlayers ps = new UnoPlayers();
         * 
         * //ps.add(new HumanPlayer("Sebastian")); ps.add(new NpcPlayer("NPC0", new
         * ColorDropStrategy(discardPile), new MostColorStrategy())); ps.add(new
         * NpcPlayer("NPC1", new ValueDropStrategy(discardPile), new
         * RandomColorStrategy())); ps.add(new NpcPlayer("NPC2", new
         * ColorDropStrategy(discardPile), new MostColorStrategy())); ps.add(new
         * NpcPlayer("NPC3", new ValueDropStrategy(discardPile), new
         * RandomColorStrategy()));
         * 
         * UnoGame game = new UnoGame(ps, deck, discardPile);
         * 
         * game.dealCardsToPlayers(); game.start();
         */
    }
}