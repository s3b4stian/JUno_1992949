package it.seba.juno.view.component.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import it.seba.juno.controller.GameController;
import it.seba.juno.view.GameView;

public class EventToHumanCardListener implements ActionListener {

    private GameController gameController;
    private Timer timer;

    public EventToHumanCardListener(/* GameView gameView, */ GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameController.addEventToHumanPlayer();
        timer.stop();
    }

    public void startTimer() {
        timer = new Timer(GameView.getTimeNormal() + 500, this);
        System.out.println("EventToHumanCardListener in: " + GameView.getTime());
        timer.setRepeats(false);
        timer.start();
    }
}
