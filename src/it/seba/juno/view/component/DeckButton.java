package it.seba.juno.view.component;

import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import it.seba.juno.manger.AudioManager;

/**
 * Button for deck, draw a card from deck.
 * 
 * @author Sebastian Rapetti
 *
 */
public class DeckButton extends JButton {

    private static final long serialVersionUID = 6752631190852368100L;

    /**
     * Button image icon.
     */
    private final ImageIcon icon;

    /**
     * Button image icon on mouse over.
     */
    private final ImageIcon iconHover;

    /**
     * Class Constructor.
     */
    public DeckButton() {
        super();

        ImageIcon card = new ImageIcon(getClass().getResource("/images/cards/deck_3.png"));
        ImageIcon cardHover = new ImageIcon(getClass().getResource("/images/cards/deck_1.png"));
        ImageIcon cardDisabled = new ImageIcon(getClass().getResource("/images/cards/deck_2.png"));

        icon = new ImageIcon(card.getImage().getScaledInstance(128, 191, java.awt.Image.SCALE_SMOOTH));
        iconHover = new ImageIcon(cardHover.getImage().getScaledInstance(128, 191, java.awt.Image.SCALE_SMOOTH));

        ImageIcon iconDisabled = new ImageIcon(
                cardDisabled.getImage().getScaledInstance(128, 191, java.awt.Image.SCALE_SMOOTH));
        setDisabledIcon(iconDisabled);

        // button properties
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setMargin(new Insets(0, 0, 0, 0));
        setIcon(icon);
        setEnabled(false);

        // adding behavior for interaction with mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setIcon(iconHover);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setIcon(icon);
            }

            /*
             * @Override public void mousePressed(MouseEvent me) {
             * 
             * }
             */
        });
    }
}
