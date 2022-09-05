package it.seba.juno.view.component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * The label to indicate which player is the dealer in a match.
 * 
 * @author Sebastian Rapetti
 *
 */
public class DealerLabel extends JLabel {

    private static final long serialVersionUID = 7938336807900288417L;

    /**
     * The icon for the north player as dealer.
     */
    private final ImageIcon dealerNorth;

    /**
     * The icon for the south player as dealer.
     */
    private final ImageIcon dealerSouth;

    /**
     * The icon for the east player as dealer.
     */
    private final ImageIcon dealerEast;

    /**
     * The icon for the west player as dealer.
     */
    private final ImageIcon dealerWest;

    /**
     * The icon when the dealer is disabled.
     */
    private final ImageIcon dealerDisabled;

    /**
     * Class Constructor.
     */
    public DealerLabel() {
        dealerNorth = new ImageIcon(getClass().getResource("/images/icons/dealer-north.png"));
        dealerSouth = new ImageIcon(getClass().getResource("/images/icons/dealer-south.png"));
        dealerEast = new ImageIcon(getClass().getResource("/images/icons/dealer-east.png"));
        dealerWest = new ImageIcon(getClass().getResource("/images/icons/dealer-west.png"));
        dealerDisabled = new ImageIcon(getClass().getResource("/images/icons/dealer-disabled.png"));

        setDealerDisabled();
    }

    /**
     * Set the dealer icon as disabled.
     */
    public void setDealerDisabled() {
        setIcon(dealerDisabled);
    }

    /**
     * Set the dealer icon as player east.
     */
    public void setDealerEast() {
        setIcon(dealerEast);
    }

    /**
     * Set the dealer icon as player north.
     */
    public void setDealerNorth() {
        setIcon(dealerNorth);
    }

    /**
     * Set the dealer icon as player south.
     */
    public void setDealerSouth() {
        setIcon(dealerSouth);
    }

    /**
     * Set the dealer icon as player west.
     */
    public void setDealerWest() {
        setIcon(dealerWest);
    }
}
