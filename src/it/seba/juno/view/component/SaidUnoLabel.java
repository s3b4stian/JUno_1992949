package it.seba.juno.view.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * The label that shows which player said Uno, implemented action listener.
 * 
 * @author Sebastian Rapetti
 *
 */
public class SaidUnoLabel extends JLabel implements ActionListener {

    private static final long serialVersionUID = 8395298848749917664L;

    /**
     * The icon for the north player.
     */
    private final ImageIcon saidUnoNorth;

    /**
     * The icon for the south player, the human player.
     */
    private final ImageIcon saidUnoSouth;

    /**
     * The icon for the east player.
     */
    private final ImageIcon saidUnoEast;

    /**
     * The icon for the west player.
     */
    private final ImageIcon saidUnoWest;

    /**
     * The icon disabled, the default state.
     */
    private final ImageIcon saidUnoDisabled;

    /**
     * The timer for the action listener.
     */
    private Timer timer;

    /**
     * Class Constructor.
     */
    public SaidUnoLabel() {
        saidUnoNorth = new ImageIcon(getClass().getResource("/images/icons/said-uno-north.png"));
        saidUnoSouth = new ImageIcon(getClass().getResource("/images/icons/said-uno-south.png"));
        saidUnoEast = new ImageIcon(getClass().getResource("/images/icons/said-uno-east.png"));
        saidUnoWest = new ImageIcon(getClass().getResource("/images/icons/said-uno-west.png"));
        saidUnoDisabled = new ImageIcon(getClass().getResource("/images/icons/said-uno-disabled.png"));

        setSaidDisabled();
    }

    /**
     * The action performed from the action listener.
     * 
     * @param e The event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        setSaidDisabled();
        timer.stop();
    }

    /**
     * Disable the icon.
     */
    public void setSaidDisabled() {
        setIcon(saidUnoDisabled);
    }

    /**
     * Activate the icon for the east player.
     */
    public void setSaidEast() {
        setIcon(saidUnoEast);
        startTimer();
    }

    /**
     * Activate the icon for the north player.
     */
    public void setSaidNorth() {
        setIcon(saidUnoNorth);
        startTimer();
    }

    /**
     * Activate the icon for the south player.
     */
    public void setSaidSouth() {
        setIcon(saidUnoSouth);
        startTimer();
    }

    /**
     * Activate the icon for the west player.
     */
    public void setSaidWest() {
        setIcon(saidUnoWest);
        startTimer();
    }

    /**
     * Start the timer for the action listener.
     */
    private void startTimer() {
        timer = new Timer(1000, this);
        timer.setRepeats(false);
        timer.start();
    }
}
