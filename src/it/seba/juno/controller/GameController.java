package it.seba.juno.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import it.seba.juno.JUno;
import it.seba.juno.model.GameModel;
import it.seba.juno.util.FirstLoadEvent;
import it.seba.juno.view.GameView;
import it.seba.juno.view.MainView;
import it.seba.juno.view.MenuView;
import it.seba.juno.view.component.PlayerCardButton;

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
        // dealCardsToPlayers();
        dropFirstCardToPileAction();
    }

    /**
     * Init actions in view.
     */
    private void initActions() {
        // button back
        gameView.getButtonBack().addActionListener(e -> goBackAction());

        // button say uno
        gameView.getButtonUno().addActionListener(e -> sayUnoAction(e));

        gameView.getButtonDeck().addActionListener(e -> drawCard(e));
    }

    public void drawCard(ActionEvent e) {
        gameModel.notifyObservers(e);

        // gameView.getPanelSouth().getComponents();

        for (Component comp : gameView.getPanelSouth().getComponents()) {
            ((PlayerCardButton) comp).addActionListener(f -> dropCardToPileAction(f));
        }
    }

    public void dropCardToPileAction(ActionEvent e) {
        // gameModel.dropPlayerCardToPileAction();
        // System.out.println("click card");
        gameModel.notifyObservers(e);
    }

    public void dropFirstCardToPileAction() {
        gameModel.dropFirstCardToPileAction();
        gameModel.notifyObservers(new FirstLoadEvent(this));
    }

    public void dealCardsToPlayers() {
        gameModel.dealCardsToPlayers();
        gameModel.notifyObservers(new FirstLoadEvent(this));
    }

    public void sayUnoAction(ActionEvent e) {
        gameModel.notifyObservers(e);
    }

    /**
     * Action for the back button, return to main menu.
     */
    public void goBackAction() {
        mainView.setCurrentView(menuView);
    }
}
