package it.seba.juno.view.component.listener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import it.seba.juno.view.component.PlayerPanel;

public class DealCardListener implements ActionListener {

    private Timer timer;
    private static int time;
    private Component card;
    private PlayerPanel panel;

    public DealCardListener(PlayerPanel panel, Component card) {
        DealCardListener.time += 100;
        this.panel = panel;
        this.card = card;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.add(card);
        timer.stop();
    }

    public void startTimer() {
        timer = new Timer(time, this);
        timer.setRepeats(false);
        timer.start();
    }

    public static void resetTimer() {
        DealCardListener.time = 0;
    }

    public static int getTime() {
        return DealCardListener.time;
    }
}
