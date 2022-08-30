package it.seba.juno.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.seba.juno.event.NextPlayerEvent;
import it.seba.juno.event.ResetGameEvent;
import it.seba.juno.model.GameModel;
import it.seba.juno.player.HumanPlayer;
import it.seba.juno.player.Player;
import it.seba.juno.view.GameView;
import it.seba.juno.view.MainView;
import it.seba.juno.view.MenuView;
import it.seba.juno.view.component.PlayerCardButton;
import it.seba.juno.view.component.listener.EventToHumanCard;

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

        gameView.getButtonNext().addActionListener(e -> nextPlayer());
    }

    private void removeActionListenersToHumanPlayer(PlayerCardButton button) {
        for (ActionListener action : button.getActionListeners()) {
            button.removeActionListener(action);
        }
    }
    
    public void removeEventToHumanPlayer() {
        for (Component comp : gameView.getPanelSouth().getComponents()) {
            removeActionListenersToHumanPlayer((PlayerCardButton) comp);
        }
    }
    
    public void addEventToHumanPlayer() {
        for (Component comp : gameView.getPanelSouth().getComponents()) {
            
            removeActionListenersToHumanPlayer((PlayerCardButton) comp);

            ((PlayerCardButton) comp).addActionListener(f -> dropCardToPileAction(f));
        }
    }

    public void drawCard(ActionEvent e) {
        // gameModel.notifyObservers(e);

        //addEventToHumanPlayer();
    }

    public void restartGame(ResetGameEvent e) {
        gameModel.reset();
        gameModel.notifyObservers(e);
    }

    public void startGame(ActionEvent e) {
        gameModel.dealCardsToPlayers();
        gameModel.dropFirstCardToPileAction();
        gameModel.notifyObservers(e);

        if (gameModel.getCurrentPlayer() instanceof HumanPlayer) {
            (new EventToHumanCard(gameView, this)).startTimer();
        }
    }

    public void nextPlayer() {
        // gameModel.getCurrentPlayer();
        if (gameModel.getNextPlayer() instanceof HumanPlayer) {
            addEventToHumanPlayer();
        }
        
        if (gameModel.currentTopCardWildDrawFour()) {
            gameModel.notifyObservers(new NextPlayerEvent(this));
            gameModel.next();
            System.out.println("next:" + gameModel.getCurrentPlayer().getName());
            return;
        }

        if (gameModel.currentTopCardSkip()) {
            gameModel.notifyObservers(new NextPlayerEvent(this));
            gameModel.next();
            System.out.println("next:" + gameModel.getCurrentPlayer().getName());
            return;
        }

        if (gameModel.currentTopCardDrawTwo()) {
            gameModel.notifyObservers(new NextPlayerEvent(this));
            gameModel.next();
            System.out.println("next:" + gameModel.getCurrentPlayer().getName());
            return;
        }

        // player action
        System.out.println("moving:" + gameModel.getCurrentPlayer().getName());
        Player player = gameModel.getCurrentPlayer();
        if (player.isNpc()) {
            gameModel.currentTopCardWild();
            gameModel.dropCardNpc();
        }

        gameModel.isWinner();

        gameModel.currentTopCardReverse();
        gameModel.notifyObservers(new NextPlayerEvent(this));
        gameModel.setSkippedTrue();
        gameModel.next();

        System.out.println("next:" + gameModel.getCurrentPlayer().getName());
        
        //addEventToHumanPlayer();

        if (gameModel.getCurrentPlayer() instanceof HumanPlayer) {
            addEventToHumanPlayer();
        }
        // if (gameModel.getCurrentPlayer().isNpc()) {
        // nextPlayer();
        // }
    }

    public void dropCardToPileAction(ActionEvent e) {

        System.out.println("Click on human card");
        
        Object t = e.getSource();

        gameModel.dropCardHuman(((PlayerCardButton) t).getCard());
        gameModel.notifyObservers(e);
        gameModel.next();
        System.out.println("next:" + gameModel.getCurrentPlayer().getName());
        
        //nextPlayer();
    }

    public void sayUnoAction(ActionEvent e) {
        // gameModel.next();
        gameModel.notifyObservers(e);
    }

    /**
     * Action for the back button, return to main menu.
     */
    public void goBackAction() {
        mainView.setCurrentView(menuView);
    }
}
