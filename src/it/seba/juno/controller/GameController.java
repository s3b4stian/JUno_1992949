package it.seba.juno.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;

import it.seba.juno.event.ResetGameEvent;
import it.seba.juno.model.GameModel;
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

        gameView.getButtonStart().addActionListener(e -> startGame(e));

        gameView.getButtonRestart().addActionListener(e -> restartGame(new ResetGameEvent(this)));
    }

    public void drawCard(ActionEvent e) {
        gameModel.notifyObservers(e);

        for (Component comp : gameView.getPanelSouth().getComponents()) {
            ((PlayerCardButton) comp).addActionListener(f -> dropCardToPileAction(f));
        }
    }

    public void restartGame(ResetGameEvent e) {
        gameModel.reset();
        gameModel.notifyObservers(e);
        // gameModel.dealCardsToPlayers();
        // gameModel.dropFirstCardToPileAction();
        // gameModel.notifyObservers(e);
    }

    public void startGame(ActionEvent e) {
        gameModel.dealCardsToPlayers();
        gameModel.dropFirstCardToPileAction();
        gameModel.notifyObservers(e);
    }

    public void dropCardToPileAction(ActionEvent e) {
        gameModel.notifyObservers(e);
    }

    public void sayUnoAction(ActionEvent e) {
        gameModel.next();
        gameModel.notifyObservers(e);
    }

    /**
     * Action for the back button, return to main menu.
     */
    public void goBackAction() {
        mainView.setCurrentView(menuView);
    }
}
