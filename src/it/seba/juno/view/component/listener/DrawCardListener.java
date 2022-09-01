package it.seba.juno.view.component.listener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import it.seba.juno.view.GameView;
import it.seba.juno.view.component.PlayerPanel;

public class DrawCardListener implements ActionListener {

    private Timer timer;
    private Component card;
    private PlayerPanel panel;

    public DrawCardListener(PlayerPanel panel, Component card) {
        this.panel = panel;
        this.card = card;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.add(card);
        timer.stop();
    }

    public void startTimer() {
        timer = new Timer(GameView.getTimeFast(), this);
        System.out.println("DrawCardListener in: " + GameView.getTime());
        timer.setRepeats(false);
        timer.start();
    }
}
