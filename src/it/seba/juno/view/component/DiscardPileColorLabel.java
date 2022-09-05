package it.seba.juno.view.component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * The label to indicate the color of the discard pile.
 * 
 * @author Sebastian Rapetti
 *
 */
public class DiscardPileColorLabel extends JLabel {

    private static final long serialVersionUID = 504283123188634724L;

    /**
     * Icon for the color red.
     */
    private final ImageIcon iconRed;

    /**
     * Icon for the color blue.
     */
    private final ImageIcon iconBlue;

    /**
     * Icon for the color yellow.
     */
    private final ImageIcon iconYellow;

    /**
     * Icon for the color green.
     */
    private final ImageIcon iconGreen;

    /**
     * Class Constructor.
     */
    public DiscardPileColorLabel() {
        iconRed = new ImageIcon(getClass().getResource("/images/icons/discard-red.png"));
        iconBlue = new ImageIcon(getClass().getResource("/images/icons/discard-blue.png"));
        iconYellow = new ImageIcon(getClass().getResource("/images/icons/discard-yellow.png"));
        iconGreen = new ImageIcon(getClass().getResource("/images/icons/discard-green.png"));

        setDisabledIcon(new ImageIcon(getClass().getResource("/images/icons/discard-disabled.png")));
        setEnabled(false);
    }

    /**
     * Set the icon color as blue.
     */
    public void setBlue() {
        setIcon(iconBlue);
    }

    /**
     * Set the icon color as green.
     */
    public void setGreen() {
        setIcon(iconGreen);
    }

    /**
     * Set the icon color as red.
     */
    public void setRed() {
        setIcon(iconRed);
    }

    /**
     * Set the icon color as yellow.
     */
    public void setYellow() {
        setIcon(iconYellow);
    }
}
