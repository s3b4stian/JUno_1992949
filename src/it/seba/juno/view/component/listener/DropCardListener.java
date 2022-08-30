package it.seba.juno.view.component.listener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import it.seba.juno.card.UnoCard;
import it.seba.juno.view.GameView;
import it.seba.juno.view.component.PlayerCardLabel;
import it.seba.juno.view.component.PlayerPanel;

public class DropCardListener implements ActionListener {

    private Timer timer;
    private Component card;
    private PlayerPanel panel;
    private GameView view;

    public DropCardListener(GameView view, PlayerPanel panel, Component card) {
        this.view = view;
        this.panel = panel;
        this.card = card;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.remove(this.card);

        UnoCard card = ((PlayerCardLabel) this.card).getCard();
        view.setDiscardPile(card);
        if (card.hasColor()) {
            view.setDiscardPileColor(card.getColor());
        }
        timer.stop();
    }

    public void startTimer() {
        timer = new Timer(GameView.getTime(), this);
        timer.setRepeats(false);
        timer.start();
    }
}
