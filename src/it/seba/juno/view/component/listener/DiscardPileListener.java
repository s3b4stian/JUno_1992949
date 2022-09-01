package it.seba.juno.view.component.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import it.seba.juno.card.UnoCard;
import it.seba.juno.view.GameView;

public class DiscardPileListener implements ActionListener {

    private Timer timer;
    private UnoCard card;
    private GameView view;

    public DiscardPileListener(GameView view, UnoCard card) {
        this.view = view;
        this.card = card;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.setDiscardPile(card);
        if (card.hasColor()) {
            view.setDiscardPileColor(card.getColor());
        }

        timer.stop();
    }

    public void startTimer() {
        timer = new Timer(GameView.getTimeFast(), this);
        // System.out.println("DiscardPileListener in: " + GameView.getTime());
        timer.setRepeats(false);
        timer.start();
    }
}
