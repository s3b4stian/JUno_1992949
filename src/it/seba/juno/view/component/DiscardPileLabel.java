package it.seba.juno.view.component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import it.seba.juno.card.UnoCard;

public class DiscardPileLabel extends JLabel {

    private static final long serialVersionUID = 6696885680569907358L;

    public void setCard(UnoCard card) {

        String filename = "card_" + card.getValue();

        if (card.hasColor()) {
            filename = "card_" + card.getColor() + "_" + card.getValue();
        }

        ImageIcon cardImage = new ImageIcon(getClass().getResource("/images/cards/" + filename + ".png"));

        ImageIcon icon = new ImageIcon(cardImage.getImage().getScaledInstance(128, 191, java.awt.Image.SCALE_SMOOTH));

        setIcon(icon);
    }
}
