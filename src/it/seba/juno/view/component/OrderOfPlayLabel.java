package it.seba.juno.view.component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class OrderOfPlayLabel extends JLabel {

    private static final long serialVersionUID = -6868552528856929696L;

    private final ImageIcon iconClockwise;
    private final ImageIcon iconAnticlockwise;

    public OrderOfPlayLabel() {
        iconClockwise = new ImageIcon(getClass().getResource("/images/icons/player-order-clockwise.png"));
        iconAnticlockwise = new ImageIcon(getClass().getResource("/images/icons/player-order-anticlockwise.png"));

        setClockwise();
    }

    public void setClockwise() {
        setIcon(iconClockwise);
    }

    public void setAnticlockwise() {
        setIcon(iconAnticlockwise);
    }
}
