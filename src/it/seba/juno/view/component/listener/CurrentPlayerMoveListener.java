package it.seba.juno.view.component.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import it.seba.juno.controller.GameController;
import it.seba.juno.view.GameView;

/**
 * The action listener used when need to switch to the next player in game, used
 * by the GameController for the recursive timed method call.
 * 
 * @author Sebastian Rapetti
 *
 */
public class CurrentPlayerMoveListener implements ActionListener {

    /**
     * The GameController reference.
     */
    private GameController controller;

    /**
     * The timer object.
     */
    private Timer timer;

    /**
     * Class Constructor.
     * 
     * @param controller The GameController object reference.
     */
    public CurrentPlayerMoveListener(GameController controller) {
        this.controller = controller;
    }

    /**
     * The action performed from the action listener.
     * 
     * @param e The event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        controller.currentPlayerMove();

        GameView.resetTimer();
        timer.stop();
    }

    /**
     * Starts the timer to trigger the action performed automatically.
     */
    public void startTimer() {
        timer = new Timer(GameView.getTimeTurn(), this);
        timer.setRepeats(false);
        timer.start();
    }
}
