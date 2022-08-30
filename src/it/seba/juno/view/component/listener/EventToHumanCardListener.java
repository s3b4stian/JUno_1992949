package it.seba.juno.view.component.listener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import it.seba.juno.controller.GameController;
import it.seba.juno.view.GameView;
import it.seba.juno.view.component.PlayerCardButton;

public class EventToHumanCardListener implements ActionListener {

    private GameView gameView;
    private GameController gameController;
    private Timer timer;

    public EventToHumanCardListener(GameView gameView, GameController gameController) {
        this.gameView = gameView;
        this.gameController = gameController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Component comp : gameView.getPanelSouth().getComponents()) {
            ((PlayerCardButton) comp).addActionListener(f -> gameController.dropCardToPileAction(f));
        }

        timer.stop();
    }

    public void startTimer() {
        timer = new Timer(GameView.getTime() + 500, this);
        timer.setRepeats(false);
        timer.start();
    }
}
