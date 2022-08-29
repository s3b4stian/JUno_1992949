package it.seba.juno.view.component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DiscardPileColorLabel extends JLabel {

    private static final long serialVersionUID = 504283123188634724L;

    private final ImageIcon iconRed;
    private final ImageIcon iconBlue;
    private final ImageIcon iconYellow;
    private final ImageIcon iconGreen;

    public DiscardPileColorLabel() {
        iconRed = new ImageIcon(getClass().getResource("/images/icons/discard-red.png"));
        iconBlue = new ImageIcon(getClass().getResource("/images/icons/discard-blue.png"));
        iconYellow = new ImageIcon(getClass().getResource("/images/icons/discard-yellow.png"));
        iconGreen = new ImageIcon(getClass().getResource("/images/icons/discard-green.png"));

        setDisabledIcon(new ImageIcon(getClass().getResource("/images/icons/discard-disabled.png")));
        setEnabled(false);
    }

    public void setRed() {
        setIcon(iconRed);
    }

    public void setBlue() {
        setIcon(iconBlue);
    }

    public void setYellow() {
        setIcon(iconYellow);
    }

    public void setGreen() {
        setIcon(iconGreen);
    }
}
