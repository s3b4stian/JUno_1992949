package it.seba.juno;

import java.io.File;
import it.seba.juno.controller.MainController;
import it.seba.juno.controller.MenuController;
import it.seba.juno.controller.OptionsController;
import it.seba.juno.controller.PlayersController;
import it.seba.juno.model.OptionsModel;
import it.seba.juno.resources.font.FontManager;
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
import it.seba.juno.util.FirstLoadEvent;
import it.seba.juno.view.MainView;
import it.seba.juno.view.MenuView;
import it.seba.juno.view.OptionsView;
import it.seba.juno.view.PlayersView;

public class JUno {

    public static void main(String[] args) {

        // initialize audio manager
        AudioManager audioManager = AudioManager.getInstance();
        // load sounds
        audioManager.addToPlayList("click", (new File("")).getAbsolutePath() + "/src/it/seba/juno/sound/ding.wav");

        // initialize font manger
        FontManager fontManager = FontManager.getInstance();
        // load custom font
        fontManager.setCustomFont("agency-fb");

        // initialize models
        OptionsModel optionsModel = new OptionsModel();

        // initial audio manager status
        audioManager.setSound(optionsModel.isSound());

        // initialize views
        MainView mainView = new MainView(optionsModel.isFullScreen());
        MenuView menuView = new MenuView(/*audioManager*/);
        OptionsView optionsView = new OptionsView(/*audioManager, */mainView);
        PlayersView playersView = new PlayersView(/*audioManager*/);

        // add observers
        optionsModel.addObserver(optionsView);
        // notify initial status
        optionsModel.notifyObservers(new FirstLoadEvent(new JUno()));

        // initialize controllers
        MainController mainController = new MainController(mainView, menuView);
        MenuController menuController = new MenuController(mainView, menuView, optionsView, playersView);
        OptionsController optionsController = new OptionsController(optionsModel, mainView, menuView, optionsView);
        PlayersController playersController = new PlayersController(mainView, menuView, playersView);

        // System.out.println("Hello JUno");

        // UnoDeck deck = new UnoDeckSimpleFactory().makeUnoDeck();

        // DiscardPile discardPile = new DiscardPile();

        // UnoPlayers ps = new UnoPlayers();

        // ps.add(new HumanPlayer("Sebastian")); ps.add(new NpcPlayer("NPC0", new
        // ColorDropStrategy(discardPile), new MostColorStrategy())); ps.add(new
        // NpcPlayer("NPC1", new ValueDropStrategy(discardPile), new
        // RandomColorStrategy())); ps.add(new NpcPlayer("NPC2", new
        // ColorDropStrategy(discardPile), new MostColorStrategy())); ps.add(new
        // NpcPlayer("NPC3", new ValueDropStrategy(discardPile), new
        // RandomColorStrategy()));

        // UnoGame game = new UnoGame(ps, deck, discardPile);

        // game.dealCardsToPlayers(); game.start();

    }
}