package it.seba.juno.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoColor;
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
import it.seba.juno.event.FirstEnableChoseColorPanelEvent;
import it.seba.juno.event.FirstHumanPlayerChoseColorEvent;
import it.seba.juno.event.HumanPlayerChoseColorEvent;
import it.seba.juno.event.HumanPlayerDrawOneCardEvent;
import it.seba.juno.event.HumanPlayerDropEvent;
import it.seba.juno.event.ResetGameEvent;
import it.seba.juno.event.StartGameEvent;
import it.seba.juno.model.GameModel;
import it.seba.juno.model.PlayersModel;
import it.seba.juno.view.GameView;
import it.seba.juno.view.MainView;
import it.seba.juno.view.MenuView;
import it.seba.juno.view.component.PlayerCardButton;
import it.seba.juno.view.component.listener.CurrentPlayerMoveListener;

/**
 * Controls both the {@link it.seba.juno.view.GameView} and the
 * {@link it.seba.juno.model.GameModel} of the program, it assigns actions to
 * the {@link it.seba.juno.view.GameView} components.
 * 
 * @author Sebastian Rapetti
 *
 */
public class GameController {

    private GameView gameView;
    private MainView mainView;
    private MenuView menuView;

    private GameModel gameModel;
    private PlayersModel playersModel;

    /**
     * Class Constructor.
     * 
     * @param gameModel    the option model, the match between players.
     * @param playersModel the player model, contains the data about players.
     * @param mainView     the main view, used to host all others view.
     * @param menuView     the menu view, show the main menu.
     * @param gameView     the game view, show Uno game.
     */
    public GameController(GameModel gameModel, PlayersModel playersModel, MainView mainView, MenuView menuView,
            GameView gameView) {
        this.gameModel = gameModel;
        this.playersModel = playersModel;

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

        // button deck, to draw a card from deck
        gameView.getButtonDeck().addActionListener(e -> humanDrawCardAction(new HumanPlayerDrawOneCardEvent(this)));

        // button start to start the game
        gameView.getButtonStart().addActionListener(e -> startGameAction(new StartGameEvent(this)));

        // button restart, to restart the game
        gameView.getButtonRestart().addActionListener(e -> restartGameAction(new ResetGameEvent(this)));

        // buttons for chose the color
        gameView.getChooseColorPanel().getButtonBlue().addActionListener(
                e -> humanChoseColorAction(new HumanPlayerChoseColorEvent(e.getSource()), UnoColor.BLUE));
        gameView.getChooseColorPanel().getButtonRed().addActionListener(
                e -> humanChoseColorAction(new HumanPlayerChoseColorEvent(e.getSource()), UnoColor.RED));
        gameView.getChooseColorPanel().getButtonYellow().addActionListener(
                e -> humanChoseColorAction(new HumanPlayerChoseColorEvent(e.getSource()), UnoColor.YELLOW));
        gameView.getChooseColorPanel().getButtonGreen().addActionListener(
                e -> humanChoseColorAction(new HumanPlayerChoseColorEvent(e.getSource()), UnoColor.GREEN));
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

    public void humanDrawCardAction(HumanPlayerDrawOneCardEvent e) {
        gameModel.drawOneCard();
        gameModel.notifyObservers(e);

        // valid only for the new card
        addEventToHumanPlayer();

        // if new card isn't droppable player move
        if (gameModel.currentPlayerCannotDrop()) {

            // next player
            gameModel.next();

            // timed call
            GameView.resetTimer();
            (new CurrentPlayerMoveListener(this)).startTimer();
        }
    }

    public void restartGameAction(ResetGameEvent e) {
        gameModel.reset();
        gameModel.notifyObservers(e);
    }

    public void startGameAction(StartGameEvent e) {
        // reset time
        GameView.resetTimer();

        // init actions, deal the cards and first card to discard pile
        gameModel.dealCardsToPlayers();
        gameModel.dropFirstCardToPileAction();

        gameModel.notifyObservers(e);

        // timed call
        (new CurrentPlayerMoveListener(this)).startTimer();
        // reset time for the case when the player move as first
        GameView.resetTimer();
    }

    public void currentPlayerMove() {

        if (gameModel.currentTopCardReverse()) {
            removeEventToHumanPlayer();

            gameModel.notifyObservers(new ChangeOrderOfPlayEvent(this));

            // go to next player
            gameModel.next();

            GameView.resetTimer();
            (new CurrentPlayerMoveListener(this)).startTimer();
            return;
        }

        if (gameModel.currentTopCardSkip()) {
            removeEventToHumanPlayer();

            gameModel.notifyObservers(new CurrentPlayerSkipEvent(this));

            // go to next player
            gameModel.next();

            GameView.resetTimer();
            (new CurrentPlayerMoveListener(this)).startTimer();
            return;
        }

        if (gameModel.currentTopCardDrawTwo()) {
            removeEventToHumanPlayer();

            gameModel.notifyObservers(new CurrentPlayerDrawTwoCardEvent(this));
            gameModel.notifyObservers(new CurrentPlayerSkipEvent(this));

            // go to next player
            gameModel.next();

            GameView.resetTimer();
            (new CurrentPlayerMoveListener(this)).startTimer();
            return;
        }

        if (gameModel.currentTopCardWildDrawFour()) {
            removeEventToHumanPlayer();

            gameModel.notifyObservers(new CurrentPlayerDrawFourCardEvent(this));
            gameModel.notifyObservers(new CurrentPlayerSkipEvent(this));

            // go to next player
            gameModel.next();

            GameView.resetTimer();
            (new CurrentPlayerMoveListener(this)).startTimer();
            return;
        }

        // human player
        if (gameModel.getCurrentPlayer().isHuman()) {

            // check for wild card and human color chose
            if (gameModel.currentFirstTopCardWild()) {
                gameModel.notifyObservers(new FirstEnableChoseColorPanelEvent(this));
            }

            addEventToHumanPlayer();

            return;
        }

        // npc player action
        // check if npc should be useless because there is the ckeck for human player
        // before
        if (gameModel.getCurrentPlayer().isNpc()) {
            // if first card is wile, player choose color
            if (gameModel.currentFirstTopCardWild()) {
                gameModel.notifyObservers(new CurrentPlayerChangeColorEvent(this));
            }

            // npc drop a card
            gameModel.dropCardNpc();

            // if the npc drawn a card the dropped it
            if (gameModel.isDrawn() && gameModel.isDropped()) {
                gameModel.notifyObservers(new CurrentPlayerDrawOneCardAndDropEvent(this));

                // otherwise
            } else {

                // npc drawn a card
                if (gameModel.isDrawn()) {
                    gameModel.notifyObservers(new CurrentPlayerDrawOneCardEvent(this));
                }

                // npc dropped a card
                if (gameModel.isDropped()) {
                    gameModel.notifyObservers(new CurrentPlayerDropEvent(this));
                }

            }

            // color change in discard pile
            if (gameModel.isChangeColor()) {
                gameModel.notifyObservers(new CurrentPlayerChangeColorEvent(this));
            }
        }

        // npc has one card
        if (gameModel.isOneCard()) {
            gameModel.notifyObservers(new CurrentPlayerHaveOneCardEvent(this));
        }

        // npc is the winner
        if (gameModel.isWinner()) {
            gameModel.notifyObservers(new CurrentPlayerWinnerEvent(this));
            removeEventToHumanPlayer();

            // human player didn't win
            // only played
            if (playersModel.isProfileLoaded()) {
                playersModel.getCurrentProfile().incrementPlayed();
            }

            return;
        }

        // npc dropped a reverse card
        // order of play change
        if (gameModel.currentTopCardReverse()) {
            gameModel.notifyObservers(new ChangeOrderOfPlayEvent(this));
        }

        // model update the view
        gameModel.notifyObservers(new CurrentPlayerMoveEvent(this));

        // go to next player
        gameModel.next();

        // recursive timed call
        GameView.resetTimer();
        (new CurrentPlayerMoveListener(this)).startTimer();
    }

    public void humanChoseColorAction(HumanPlayerChoseColorEvent e, UnoColor color) {

        gameModel.setDiscardPileColor(color);

        if (gameModel.currentFirstTopCardWild()) {
            gameModel.notifyObservers(new FirstHumanPlayerChoseColorEvent(this));
            return;
        }

        gameModel.notifyObservers(e);

        // go to next player
        gameModel.next();

        // timed call
        GameView.resetTimer();
        (new CurrentPlayerMoveListener(this)).startTimer();
    }

    public void dropCardToPileAction(ActionEvent e) {

        Object t = e.getSource();

        UnoCard card = ((PlayerCardButton) t).getCard();

        if (gameModel.droppable(card)) {

            // remove events from human card
            removeEventToHumanPlayer();
            gameModel.dropCardHuman(card);

            // human changed color and is not the winner of the match
            if (gameModel.humanDroppedChangeColorCard() && !gameModel.isWinner()) {
                gameModel.notifyObservers(new EnableChoseColorPanelEvent(t));
                removeEventToHumanPlayer();
                return;
            }

            // if human dropped a reverse card change the order of play
            if (gameModel.currentTopCardReverse()) {
                gameModel.notifyObservers(new ChangeOrderOfPlayEvent(this));
            }

            // notify an human drop event
            gameModel.notifyObservers(new HumanPlayerDropEvent(t));

            // human is the winner
            if (gameModel.isWinner()) {

                gameModel.notifyObservers(new CurrentPlayerWinnerEvent(this));
                removeEventToHumanPlayer();

                // human player won the match
                if (playersModel.isProfileLoaded()) {
                    playersModel.getCurrentProfile().incrementWon();
                }

                return;
            }

            // go to next player
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
