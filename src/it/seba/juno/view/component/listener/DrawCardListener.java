package it.seba.juno.view.component.listener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import it.seba.juno.view.GameView;
import it.seba.juno.view.component.PlayerPanel;

/**
 * Action listener for the draw card player action, actually not used to animate
 * cards inside the game.
 * 
 * @author Sebastian Rapetti
 *
 */
public class DrawCardListener implements ActionListener {

    /**
     * The card to be added to the panel.
     */
    private Component card;

    /**
     * The panel where the card will be added.
     */
    private PlayerPanel panel;

    /**
     * The timer object.
     */
    private Timer timer;

    /**
     * Class Constructor.
     * 
     * @param panel The panel where the card will be added.
     * @param card  The card to be added to the panel.
     */
    public DrawCardListener(PlayerPanel panel, Component card) {
        this.panel = panel;
        this.card = card;
    }

    /**
     * The action performed from the action listener.
     * 
     * @param e The event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        panel.add(card);
        timer.stop();
    }

    /**
     * Starts the timer to trigger the action performed automatically.
     */
    public void startTimer() {
        timer = new Timer(GameView.getTimeFast(), this);
        timer.setRepeats(false);
        timer.start();
    }
}
