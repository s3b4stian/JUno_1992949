package it.seba.juno.view.component;

import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import it.seba.juno.card.UnoCard;

/**
 * The button to represent cards for the human player, provide a way for the
 * human player to interact with owned cards during a match.
 * 
 * @author Sebastian Rapetti
 *
 */
public class PlayerCardButton extends JButton {

    private static final long serialVersionUID = 6752631190852368100L;

    /**
     * Button image icon.
     */
    private final ImageIcon icon;

    /**
     * The card of the button.
     */
    private UnoCard card;

    /**
     * Class Constructor.
     * 
     * @param card The card represented from the button.
     */
    public PlayerCardButton(UnoCard card) {
        super();

        this.card = card;

        String filename = "card_" + card.getValue();

        if (card.hasColor()) {
            filename = "card_" + card.getColor() + "_" + card.getValue();
        }

        ImageIcon cardImage = new ImageIcon(getClass().getResource("/images/cards/" + filename + ".png"));
        icon = new ImageIcon(cardImage.getImage().getScaledInstance(85, 128, java.awt.Image.SCALE_SMOOTH));

        // button properties
        setFocusPainted(false);
        setBorderPainted(false);

        setContentAreaFilled(false);
        setMargin(new Insets(0, 0, 0, 0));
        setIcon(icon);
    }

    /**
     * Returns the card of the button.
     * 
     * @return Card reference.
     */
    public UnoCard getCard() {
        return card;
    }
}
