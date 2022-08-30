package it.seba.juno.view.component.listener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import it.seba.juno.controller.GameController;
import it.seba.juno.view.GameView;
import it.seba.juno.view.component.PlayerCardButton;

public class EventToHumanCard implements ActionListener {

    private GameView gameView;
    private GameController gameController;
    private Timer timer;
    
    public EventToHumanCard(GameView gameView, GameController gameController) {
        this.gameView = gameView;
        this.gameController = gameController;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Events");
        for (Component comp : gameView.getPanelSouth().getComponents()) {
            ((PlayerCardButton) comp).addActionListener(f -> gameController.dropCardToPileAction(f));
        }
        
        timer.stop();
        //System.out.println(DealCardListener.getTime());
    }

    public void startTimer() {
        //System.out.println(DealCardListener.getTime());
        timer = new Timer(3000, this);
        timer.setRepeats(false);
        timer.start();
    }
}
