package it.seba.juno.view.component;

import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import it.seba.juno.card.UnoCard;

public class PlayerCardButton extends JButton {

    private static final long serialVersionUID = 6752631190852368100L;

    /**
     * Button image icon.
     */
    private final ImageIcon icon;

    private UnoCard card;

    public UnoCard getCard() {
        return card;
    }

    /**
     * Class Constructor.
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

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {

            }

            @Override
            public void mouseExited(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
                // AudioManager.getInstance().playSoundEffect("card");
            }
        });
    }
}
