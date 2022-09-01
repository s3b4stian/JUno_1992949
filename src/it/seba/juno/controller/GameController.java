package it.seba.juno.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoColor;
import it.seba.juno.card.UnoValue;
import it.seba.juno.event.ChangeOrderOfPlayEvent;
import it.seba.juno.event.CurrentPlayerChangeColorEvent;
import it.seba.juno.event.CurrentPlayerDrawFourCardEvent;
import it.seba.juno.event.CurrentPlayerDrawOneCardAndDropEvent;
import it.seba.juno.event.CurrentPlayerDrawOneCardEvent;
import it.seba.juno.event.CurrentPlayerDrawTwoCardEvent;
import it.seba.juno.event.CurrentPlayerDropEvent;
import it.seba.juno.event.CurrentPlayerHaveOneCardEvent;
import it.seba.juno.event.CurrentPlayerMoveEvent;
import it.seba.juno.event.CurrentPlayerSkipEvent;
import it.seba.juno.event.CurrentPlayerWinnerEvent;
import it.seba.juno.event.EnableChoseColorPanelEvent;
import it.seba.juno.event.HumanPlayerChoseColorEvent;
import it.seba.juno.event.HumanPlayerDropEvent;
import it.seba.juno.event.HumanPlayerDrawOneCardEvent;
import it.seba.juno.event.ResetGameEvent;
import it.seba.juno.event.StartGameEvent;
import it.seba.juno.model.GameModel;
import it.seba.juno.player.NpcChangeColorAction;
import it.seba.juno.view.GameView;
import it.seba.juno.view.MainView;
import it.seba.juno.view.MenuView;
import it.seba.juno.view.component.PlayerCardButton;
import it.seba.juno.view.component.listener.CurrentPlayerMoveListener;
import it.seba.juno.view.component.listener.EventToHumanCardListener;

public class GameController {

    private GameView gameView;
    private MainView mainView;
    private MenuView menuView;

    private GameModel gameModel;

    private List<ActionListener> actions;

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

        gameView.getButtonDeck().addActionListener(e -> humanDrawCard(new HumanPlayerDrawOneCardEvent(this)));

        gameView.getButtonStart().addActionListener(e -> startGame(new StartGameEvent(this)));

        gameView.getButtonRestart().addActionListener(e -> restartGame(new ResetGameEvent(this)));

        gameView.getChooseColorPanel().getButtonBlue()
                .addActionListener(e -> humanChoseColor(new HumanPlayerChoseColorEvent(e.getSource()), UnoColor.BLUE));
        gameView.getChooseColorPanel().getButtonRed()
                .addActionListener(e -> humanChoseColor(new HumanPlayerChoseColorEvent(e.getSource()), UnoColor.RED));
        gameView.getChooseColorPanel().getButtonYellow().addActionListener(
                e -> humanChoseColor(new HumanPlayerChoseColorEvent(e.getSource()), UnoColor.YELLOW));
        gameView.getChooseColorPanel().getButtonGreen()
                .addActionListener(e -> humanChoseColor(new HumanPlayerChoseColorEvent(e.getSource()), UnoColor.GREEN));
    }

    private void removeActionListenersToHumanPlayer(PlayerCardButton button) {
        for (ActionListener action : button.getActionListeners()) {
            button.removeActionListener(action);
        }
    }

    public void removeEventToHumanPlayer() {

        // gameView.getButtonDeck().setEnabled(true);

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

        /*
         * if (gameModel.currentPlayerCannotDrop()) {
         * gameView.getButtonDeck().setEnabled(true); }
         */
    }

    public void humanDrawCard(HumanPlayerDrawOneCardEvent e) {
        gameModel.drawOneCard();
        gameModel.notifyObservers(e);
        // gameModel.notifyObservers(new CurrentPlayerDrawOneCardEvent(this));

        // valid only for the new card
        addEventToHumanPlayer();

        // if new card isn't droppable player move
        if (gameModel.currentPlayerCannotDrop()) {
            // next player

            // gameModel.notifyObservers(new CurrentPlayerDrawOneCardEvent(this));
            gameModel.next();

            // reset timer
            GameView.resetTimer();
            (new CurrentPlayerMoveListener(/* gameView, */this)).startTimer();
        }
    }

    public void restartGame(ResetGameEvent e) {
        gameModel.reset();
        gameModel.notifyObservers(e);
    }

    public void startGame(StartGameEvent e) {
        // reset time
        GameView.resetTimer();

        gameModel.dealCardsToPlayers();
        gameModel.dropFirstCardToPileAction();
        gameModel.notifyObservers(e);

        if (gameModel.getCurrentPlayer().isHuman()) {

            (new EventToHumanCardListener(/* gameView, */this)).startTimer();
        } else {
            // GameView.resetTimer();
            (new CurrentPlayerMoveListener(/* gameView, */this)).startTimer();
            GameView.resetTimer();
        }
    }

    public void currentPlayerMove() {

        if (gameModel.currentTopCardSkip()) {
            removeEventToHumanPlayer();

            gameModel.notifyObservers(new CurrentPlayerSkipEvent(this));

            gameModel.next();

            GameView.resetTimer();
            (new CurrentPlayerMoveListener(this)).startTimer();
            return;
        }

        if (gameModel.currentTopCardDrawTwo()) {
            removeEventToHumanPlayer();

            gameModel.notifyObservers(new CurrentPlayerDrawTwoCardEvent(this));
            gameModel.notifyObservers(new CurrentPlayerSkipEvent(this));

            gameModel.next();

            GameView.resetTimer();
            (new CurrentPlayerMoveListener(this)).startTimer();
            return;
        }

        if (gameModel.currentTopCardWildDrawFour()) {
            removeEventToHumanPlayer();

            gameModel.notifyObservers(new CurrentPlayerDrawFourCardEvent(this));
            gameModel.notifyObservers(new CurrentPlayerSkipEvent(this));

            gameModel.next();

            GameView.resetTimer();
            (new CurrentPlayerMoveListener(this)).startTimer();
            return;
        }

        if (gameModel.getCurrentPlayer().isHuman()) {
            addEventToHumanPlayer();
            return;
        }

        // npc player action
        if (gameModel.getCurrentPlayer().isNpc()) {
            // if first card is wile, player choose color
            gameModel.currentTopCardWild();

            gameModel.dropCardNpc();

            // System.out.println("\n" + gameModel.getCurrentPlayer().getName());

            if (gameModel.isDrawn() && gameModel.isDropped()) {
                gameModel.notifyObservers(new CurrentPlayerDrawOneCardAndDropEvent(this));

                // System.out.println("Draw and drop");

            } else {

                if (gameModel.isDrawn()) {
                    gameModel.notifyObservers(new CurrentPlayerDrawOneCardEvent(this));
                    // System.out.println("Draw");
                }

                if (gameModel.isDropped()) {
                    gameModel.notifyObservers(new CurrentPlayerDropEvent(this));
                    // System.out.println("Drop");
                }

            }

            if (gameModel.isChangeColor()) {
                gameModel.notifyObservers(new CurrentPlayerChangeColorEvent(this));
            }
        }

        if (gameModel.isOneCard()) {
            gameModel.notifyObservers(new CurrentPlayerHaveOneCardEvent(this));
        }

        // npc is the winner
        if (gameModel.isWinner()) {
            gameModel.notifyObservers(new CurrentPlayerWinnerEvent(this));
            removeEventToHumanPlayer();
            return;
        }

        // if (gameModel.currentTopCardReverse()) {
        // gameModel.notifyObservers(new ChangeOrderOfPlayEvent(this));
        // }

        gameModel.notifyObservers(new CurrentPlayerMoveEvent(this));
        gameModel.next();

        GameView.resetTimer();
        (new CurrentPlayerMoveListener(/* gameView, */this)).startTimer();
    }

    public void humanChoseColor(HumanPlayerChoseColorEvent e, UnoColor color) {
        // TODO Auto-generated method stub
        gameModel.setDiscardPileColor(color);
        gameModel.notifyObservers(e);

        gameModel.next();

        // reset time for the case when the player move as first
        GameView.resetTimer();
        (new CurrentPlayerMoveListener(/* gameView, */this)).startTimer();
    }

    public void dropCardToPileAction(ActionEvent e) {

        Object t = e.getSource();

        UnoCard card = ((PlayerCardButton) t).getCard();

        if (gameModel.droppable(card)) {

            // remove events from human card
            removeEventToHumanPlayer();
            gameModel.dropCardHuman(card);

            if (gameModel.humanDroppedChangeColorCard()) {
                gameModel.notifyObservers(new EnableChoseColorPanelEvent(t));
                removeEventToHumanPlayer();
                return;
            }

            // if human dropped a reverse card change the order of play
            // if (gameModel.currentTopCardReverse()) {
            // gameModel.notifyObservers(new ChangeOrderOfPlayEvent(this));
            // }

            // notify an human drop event
            gameModel.notifyObservers(new HumanPlayerDropEvent(t));

            // human is the winner
            if (gameModel.isWinner()) {
                gameModel.notifyObservers(new CurrentPlayerWinnerEvent(this));
                removeEventToHumanPlayer();
                return;
            }

            gameModel.next();

            // reset time for the case when the player move as first
            GameView.resetTimer();

            (new CurrentPlayerMoveListener(/* gameView, */this)).startTimer();
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
