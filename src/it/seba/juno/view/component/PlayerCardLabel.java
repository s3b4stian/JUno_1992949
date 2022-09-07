package it.seba.juno.view.component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import it.seba.juno.card.UnoCard;

/**
 * The label to represent cards for the npc players.
 * 
 * @author Sebastian Rapetti
 *
 */
public class PlayerCardLabel extends JLabel {

    /**
     * The type of the card, where the card will be drawn.
     * 
     * @author Sebastian Rapetti
     *
     */
    public enum PlayerCardLabelType {

        /**
         * For cards in the north panel.
         */
        NORTH("north", 85, 128),

        /**
         * For cards in the east panel.
         */
        EAST("east", 128, 85),

        /**
         * For cards in the west panel.
         */
        WEST("west", 128, 85);

        /**
         * Button icon.
         */
        private final String stringIcon;

        /**
         * Card height.
         */
        private final int height;

        /**
         * Card width.
         */
        private final int width;

        /**
         * Enum Constructor.
         * 
         * @param s the file name of the button icon without extension.
         */
        PlayerCardLabelType(String s, int w, int h) {
            stringIcon = s;
            width = w;
            height = h;
        }

        /**
         * Returns the height of the card
         * 
         * @return Card height.
         */
        public int getHeight() {
            return height;
        }

        /**
         * Returns the file name of the icon without extension.
         * 
         * @return The file name.
         */
        public String getStringIcon() {
            return stringIcon;
        }

        /**
         * Return the width of the card.
         * 
         * @return Card width.
         */
        public int getWidth() {
            return width;
        }
    }

    private static final long serialVersionUID = -7440244117469011026L;

    /**
     * Label image icon.
     */
    private final ImageIcon icon;

    /**
     * Label image icon.
     */
    private final UnoCard card;

    /**
     * Label card type.
     */
    private final PlayerCardLabelType type;

    /**
     * Class Constructor.
     * 
     * @param card The card for the label, not shown.
     * @param t    The orientation of the card.
     */
    public PlayerCardLabel(UnoCard card, PlayerCardLabelType t) {
        super();

        this.card = card;
        this.type = t;

        ImageIcon cardImage = new ImageIcon(
                getClass().getResource("/images/cards/deck_" + type.getStringIcon() + ".png"));
        icon = new ImageIcon(
                cardImage.getImage().getScaledInstance(type.getWidth(), type.getHeight(), java.awt.Image.SCALE_SMOOTH));

        setIcon(icon);
    }

    /**
     * Return the card shown by the button.
     * 
     * @return The card reference.
     */
    public UnoCard getCard() {
        return card;
    }
}
