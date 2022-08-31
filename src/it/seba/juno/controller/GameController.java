package it.seba.juno.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.seba.juno.card.UnoCard;
import it.seba.juno.event.CurrentPlayerChangeColorEvent;
import it.seba.juno.event.CurrentPlayerDrawFourCardEvent;
import it.seba.juno.event.CurrentPlayerDrawOneCardEvent;
import it.seba.juno.event.CurrentPlayerDrawTwoCardEvent;
import it.seba.juno.event.CurrentPlayerDropEvent;
import it.seba.juno.event.CurrentPlayerHaveOneCardEvent;
import it.seba.juno.event.CurrentPlayerMoveEvent;
import it.seba.juno.event.CurrentPlayerSkipEvent;
import it.seba.juno.event.CurrentPlayerWinnerEvent;
import it.seba.juno.event.HumanDropEvent;
import it.seba.juno.event.HumanPlayerDrawOneCardEvent;
import it.seba.juno.event.ResetGameEvent;
import it.seba.juno.event.StartGameEvent;
import it.seba.juno.model.GameModel;
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

        gameView.getButtonDeck().addActionListener(e -> drawCard(new HumanPlayerDrawOneCardEvent(this)));

        gameView.getButtonStart().addActionListener(e -> startGame(new StartGameEvent(this)));

        gameView.getButtonRestart().addActionListener(e -> restartGame(new ResetGameEvent(this)));

        gameView.getButtonNext().addActionListener(e -> currentPlayerMove());
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

            UnoCard card = ((PlayerCardButton) comp).getCard();
            if (gameModel.droppable(card)) {
                ((PlayerCardButton) comp).addActionListener(f -> dropCardToPileAction(f));
            }
        }
    }

    public void drawCard(HumanPlayerDrawOneCardEvent e) {
        gameModel.drawOneCard();
        gameModel.notifyObservers(e);
        gameModel.notifyObservers(new CurrentPlayerDrawOneCardEvent(this));

        // valid only for the new card
        addEventToHumanPlayer();
        // if new card isn't droppable player move
        if (gameModel.currentPlayerCannotDrop()) {
            currentPlayerMove();
        }
    }

    public void restartGame(ResetGameEvent e) {
        gameModel.reset();
        gameModel.notifyObservers(e);
    }

    public void startGame(StartGameEvent e) {
        gameModel.dealCardsToPlayers();
        gameModel.dropFirstCardToPileAction();
        gameModel.notifyObservers(e);

        if (gameModel.getCurrentPlayer().isHuman()) {
            (new EventToHumanCard(gameView, this)).startTimer();
        }
    }

    public void currentPlayerMove() {

        if (gameModel.getNextPlayer().isHuman()) {
            addEventToHumanPlayer();
        }

        if (gameModel.currentTopCardSkip()) {
            removeEventToHumanPlayer();
            gameModel.notifyObservers(new CurrentPlayerSkipEvent(this));
            gameModel.next();
            return;
        }

        if (gameModel.currentTopCardDrawTwo()) {
            removeEventToHumanPlayer();
            gameModel.notifyObservers(new CurrentPlayerDrawTwoCardEvent(this));
            gameModel.notifyObservers(new CurrentPlayerSkipEvent(this));
            gameModel.next();
            return;
        }

        if (gameModel.currentTopCardWildDrawFour()) {
            removeEventToHumanPlayer();
            gameModel.notifyObservers(new CurrentPlayerDrawFourCardEvent(this));
            gameModel.notifyObservers(new CurrentPlayerSkipEvent(this));
            gameModel.next();;
            return;
        }

        // npc player action
        if (gameModel.getCurrentPlayer().isNpc()) {
            // if first card is wile, player choose color
            gameModel.currentTopCardWild();
            
            gameModel.dropCardNpc();

            if (gameModel.isDrawn()) {
                gameModel.notifyObservers(new CurrentPlayerDrawOneCardEvent(this));
            }

            if (gameModel.isDropped()) {
                gameModel.notifyObservers(new CurrentPlayerDropEvent(this));
            }

            if (gameModel.isChangeColor()) {
                gameModel.notifyObservers(new CurrentPlayerChangeColorEvent(this));
            }
        }

        if (gameModel.isOneCard()) {
            gameModel.notifyObservers(new CurrentPlayerHaveOneCardEvent(this));
        }

        if (gameModel.isWinner()) {
            gameModel.notifyObservers(new CurrentPlayerWinnerEvent(this));
            removeEventToHumanPlayer();
            return;
        }

        gameModel.currentTopCardReverse();

        gameModel.notifyObservers(new CurrentPlayerMoveEvent(this));

        gameModel.next();

        if (gameModel.getCurrentPlayer().isHuman()) {
            addEventToHumanPlayer();
        }

        if (gameModel.currentTopCardSkip()) {
            gameModel.notifyObservers(new CurrentPlayerSkipEvent(this));
            gameModel.next();
            if (gameModel.getCurrentPlayer().isNpc()) {
                removeEventToHumanPlayer();
            }
        }

        if (gameModel.currentTopCardDrawTwo()) {
            gameModel.notifyObservers(new CurrentPlayerDrawTwoCardEvent(this));
            gameModel.notifyObservers(new CurrentPlayerSkipEvent(this));
            gameModel.next();
            if (gameModel.getCurrentPlayer().isNpc()) {
                removeEventToHumanPlayer();
            }
        }

        if (gameModel.currentTopCardWildDrawFour()) {
            gameModel.notifyObservers(new CurrentPlayerDrawFourCardEvent(this));
            gameModel.notifyObservers(new CurrentPlayerSkipEvent(this));
            gameModel.next();
            if (gameModel.getCurrentPlayer().isNpc()) {
                removeEventToHumanPlayer();
            }
        }

    }

    public void dropCardToPileAction(ActionEvent e) {

        Object t = e.getSource();

        UnoCard card = ((PlayerCardButton) t).getCard();

        if (gameModel.droppable(card)) {
            removeEventToHumanPlayer();
            gameModel.dropCardHuman(card);
            // gameModel.notifyObservers(new CurrentPlayerDropEvent(this));
            gameModel.notifyObservers(new HumanDropEvent(t));
            gameModel.next();

            currentPlayerMove();
        }
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
