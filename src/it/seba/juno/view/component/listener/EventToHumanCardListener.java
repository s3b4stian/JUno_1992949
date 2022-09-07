package it.seba.juno.view.component.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import it.seba.juno.controller.GameController;
import it.seba.juno.view.GameView;

/**
 * Action listener to add events (delayed mode) to human player cards, actually
 * not used to animate cards inside the game.
 * 
 * @author Sebastian Rapetti
 *
 */
public class EventToHumanCardListener implements ActionListener {

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
    public EventToHumanCardListener(GameController controller) {
        this.controller = controller;
    }

    /**
     * The action performed from the action listener.
     * 
     * @param e The event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        controller.addEventToHumanPlayer();
        timer.stop();
    }

    /**
     * Starts the timer to trigger the action performed automatically.
     */
    public void startTimer() {
        timer = new Timer(GameView.getTimeNormal() + 500, this);
        timer.setRepeats(false);
        timer.start();
    }
}
