package it.seba.juno.view.component.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import it.seba.juno.card.UnoCard;
import it.seba.juno.view.GameView;

/**
 * The action listener used to add a card to discard pile, actually used only
 * after card deal.
 * 
 * @author Sebastian Rapetti
 *
 */
public class DiscardPileListener implements ActionListener {

    /**
     * The card will be added to the discard pile.
     */
    private UnoCard card;

    /**
     * The GameView reference.
     */
    private GameView view;

    /**
     * The timer object.
     */
    private Timer timer;

    /**
     * Class Constructor.
     * 
     * @param view The GameView reference.
     * @param card The card will be added to the discard pile.
     */
    public DiscardPileListener(GameView view, UnoCard card) {
        this.view = view;
        this.card = card;
    }

    /**
     * The action performed from the action listener.
     * 
     * @param e The event to be processed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        view.setDiscardPile(card);
        if (card.hasColor()) {
            view.setDiscardPileColor(card.getColor());
        }

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
