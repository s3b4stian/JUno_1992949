package it.seba.juno.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import it.seba.juno.model.GameModel;
import it.seba.juno.view.GameView;
import it.seba.juno.view.MainView;
import it.seba.juno.view.MenuView;

public class GameController {

    private GameView gameView;
    private MainView mainView;
    private MenuView menuView;

    private GameModel gameModel;

    public GameController(GameModel gameModel, MainView mainView, MenuView menuView, GameView gameView) {
        this.gameModel = gameModel;

        this.mainView = mainView;
        this.menuView = menuView;

        this.gameView = gameView;

        initActions();
    }

    /**
     * Init actions in view.
     */
    private void initActions() {
        // button back
        gameView.getButtonBack().addActionListener(e -> goBackAction());

    }

    /**
     * Action for the back button, return to main menu.
     */
    public void goBackAction() {
        mainView.setCurrentView(menuView);
        // optionsModel.save();
    }
}
