package it.seba.juno.view.component.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import it.seba.juno.controller.GameController;
import it.seba.juno.view.GameView;

public class CurrentPlayerMoveListener implements ActionListener {

    /* private GameView gameView; */
    private GameController gameController;
    private Timer timer;

    public CurrentPlayerMoveListener(/* GameView gameView, */GameController gameController) {
        /* this.gameView = gameView; */
        this.gameController = gameController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameController.currentPlayerMove();

        GameView.resetTimer();
        timer.stop();
    }

    public void startTimer() {
        timer = new Timer(GameView.getTimeTurn(), this);
        System.out.println("CurrentPlayerMoveListener in: " + GameView.getTime());
        timer.setRepeats(false);
        timer.start();
    }
}
