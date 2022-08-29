package it.seba.juno.view.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class SaidUnoLabel extends JLabel implements ActionListener {

    private static final long serialVersionUID = 8395298848749917664L;

    private final ImageIcon saidUnoNorth;
    private final ImageIcon saidUnoSouth;
    private final ImageIcon saidUnoEast;
    private final ImageIcon saidUnoWest;
    private final ImageIcon saidUnoDisabled;

    private Timer timer;

    public SaidUnoLabel() {
        saidUnoNorth = new ImageIcon(getClass().getResource("/images/icons/said-uno-north.png"));
        saidUnoSouth = new ImageIcon(getClass().getResource("/images/icons/said-uno-south.png"));
        saidUnoEast = new ImageIcon(getClass().getResource("/images/icons/said-uno-east.png"));
        saidUnoWest = new ImageIcon(getClass().getResource("/images/icons/said-uno-west.png"));
        saidUnoDisabled = new ImageIcon(getClass().getResource("/images/icons/said-uno-disabled.png"));

        setSaidDisabled();
    }

    public void actionPerformed(ActionEvent e) {
        setSaidDisabled();
        timer.stop();
    }

    private void startTimer() {
        timer = new Timer(500, this);
        timer.setRepeats(false);
        timer.start();
    }

    public void setSaidNorth() {
        setIcon(saidUnoNorth);
        startTimer();
    }

    public void setSaidSouth() {
        setIcon(saidUnoSouth);
        startTimer();
    }

    public void setSaidEast() {
        setIcon(saidUnoEast);
        startTimer();
    }

    public void setSaidWest() {
        setIcon(saidUnoWest);
        startTimer();
    }

    public void setSaidDisabled() {
        setIcon(saidUnoDisabled);
    }
}
