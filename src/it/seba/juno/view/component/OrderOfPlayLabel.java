package it.seba.juno.view.component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * The label that shows the order of play into the game.
 * 
 * @author Sebastian Rapetti
 *
 */
public class OrderOfPlayLabel extends JLabel {

    private static final long serialVersionUID = -6868552528856929696L;

    /**
     * Icon for the clockwise order of play.
     */
    private final ImageIcon iconClockwise;

    /**
     * Icon for the anticlockwise order of play.
     */
    private final ImageIcon iconAnticlockwise;

    /**
     * Class Constructor.
     */
    public OrderOfPlayLabel() {
        iconClockwise = new ImageIcon(getClass().getResource("/images/icons/player-order-clockwise.png"));
        iconAnticlockwise = new ImageIcon(getClass().getResource("/images/icons/player-order-anticlockwise.png"));

        setClockwise();
    }

    /**
     * Set the label to show anticlockwise icon.
     */
    public void setAnticlockwise() {
        setIcon(iconAnticlockwise);
    }

    /**
     * Set the label to show clockwise icon.
     */
    public void setClockwise() {
        setIcon(iconClockwise);
    }
}
