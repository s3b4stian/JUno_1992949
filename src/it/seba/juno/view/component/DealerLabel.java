package it.seba.juno.view.component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DealerLabel extends JLabel {

    private static final long serialVersionUID = 7938336807900288417L;

    private final ImageIcon dealerNorth;
    private final ImageIcon dealerSouth;
    private final ImageIcon dealerEast;
    private final ImageIcon dealerWest;
    private final ImageIcon dealerDisabled;

    public DealerLabel() {
        dealerNorth = new ImageIcon(getClass().getResource("/images/icons/dealer-north.png"));
        dealerSouth = new ImageIcon(getClass().getResource("/images/icons/dealer-south.png"));
        dealerEast = new ImageIcon(getClass().getResource("/images/icons/dealer-east.png"));
        dealerWest = new ImageIcon(getClass().getResource("/images/icons/dealer-west.png"));
        dealerDisabled = new ImageIcon(getClass().getResource("/images/icons/dealer-disabled.png"));

        setDealerDisabled();
    }

    public void setDealerNorth() {
        setIcon(dealerNorth);
    }

    public void setDealerSouth() {
        setIcon(dealerSouth);
    }

    public void setDealerEast() {
        setIcon(dealerEast);
    }

    public void setDealerWest() {
        setIcon(dealerWest);
    }

    public void setDealerDisabled() {
        setIcon(dealerDisabled);
    }
}
