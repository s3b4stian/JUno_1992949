package it.seba.juno;

import it.seba.juno.controller.MainController;
import it.seba.juno.controller.MenuController;
import it.seba.juno.controller.OptionsController;
import it.seba.juno.controller.PlayersController;
import it.seba.juno.manger.AudioManager;
import it.seba.juno.manger.FontManager;
import it.seba.juno.manger.SerializationManager;
import it.seba.juno.model.OptionsModel;
import it.seba.juno.model.PlayersModel;
import it.seba.juno.model.PlayersProfileModel;
import it.seba.juno.util.FirstLoadEvent;
import it.seba.juno.view.MainView;
import it.seba.juno.view.MenuView;
import it.seba.juno.view.OptionsView;
import it.seba.juno.view.PlayersView;

public class JUno {

    public static void main(String[] args) {

        // initialize serialization manager
        SerializationManager serializationManager = SerializationManager.getInstance();

        // initialize audio manager
        AudioManager audioManager = AudioManager.getInstance();
        // load sounds
        audioManager.addToPlayList("click", "ding.wav");

        // initialize font manger
        FontManager fontManager = FontManager.getInstance();
        // load custom font
        fontManager.setCustomFont("agency-fb");

        // initialize models
        // option model, if exists load it from disk
        OptionsModel optionsModel = serializationManager.loadOptions();
        // player profiles, if exists load them from disk
        PlayersModel playersModel = serializationManager.loadPlayer();

        // initial audio manager status
        audioManager.setSound(optionsModel.isSound());

        // initialize views
        MainView mainView = new MainView(optionsModel.isFullScreen());
        MenuView menuView = new MenuView();
        OptionsView optionsView = new OptionsView(mainView);
        PlayersView playersView = new PlayersView();

        // add observers
        optionsModel.addObserver(optionsView);
        playersModel.addObserver(playersView);
        // notify initial status
        optionsModel.notifyObservers(new FirstLoadEvent(new JUno()));

        // initialize controllers
        MainController mainController = new MainController(mainView, menuView);
        MenuController menuController = new MenuController(mainView, menuView, optionsView, playersView);
        OptionsController optionsController = new OptionsController(optionsModel, mainView, menuView, optionsView);
        PlayersController playersController = new PlayersController(playersModel, mainView, menuView, playersView);
    }
}