package it.seba.juno.view.component;

import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import it.seba.juno.card.UnoCard;

public class PlayerCardLabel extends JButton {

    private static final long serialVersionUID = -7440244117469011026L;

    /**
     * Button image icon.
     */
    private final ImageIcon icon;

    /**
     * Button image icon on mouse over.
     */
    private UnoCard card;

    /**
     * Button type.
     */
    private final PlayerCardLabelType type;

    /**
     * 
     * @author user
     *
     */
    public enum PlayerCardLabelType {

        NORTH("north", 85, 128),

        EAST("east", 128, 85),

        WEST("west", 128, 85);

        /**
         * Button icon.
         */
        private final String stringIcon;

        private final int height;

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
         * Height of the card
         * 
         * @return
         */
        public int getHeight() {
            return height;
        }

        /**
         * Width of the card.
         * 
         * @return
         */
        public int getWidth() {
            return width;
        }

        /**
         * Returns the file name of the icon without extension.
         * 
         * @return the file name.
         */
        public String getStringIcon() {
            return stringIcon;
        }
    }

    /**
     * Class Constructor.
     */
    public PlayerCardLabel(UnoCard card, PlayerCardLabelType type) {
        super();

        this.card = card;
        this.type = type;

        ImageIcon cardImage = new ImageIcon(
                getClass().getResource("/images/cards/deck_" + type.getStringIcon() + ".png"));
        icon = new ImageIcon(
                cardImage.getImage().getScaledInstance(type.getWidth(), type.getHeight(), java.awt.Image.SCALE_SMOOTH));

        // button properties
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setMargin(new Insets(0, 0, 0, 0));
        setIcon(icon);
    }

    /**
     * Return the card shown by the button.
     * 
     * @return
     */
    public UnoCard getCard() {
        return card;
    }
}
