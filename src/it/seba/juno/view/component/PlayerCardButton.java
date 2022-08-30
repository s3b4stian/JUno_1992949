package it.seba.juno.view.component;

import java.awt.Component;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import it.seba.juno.card.UnoCard;
import it.seba.juno.manger.AudioManager;

public class PlayerCardButton extends JButton {

    private static final long serialVersionUID = 6752631190852368100L;

    /**
     * Button image icon.
     */
    private final ImageIcon icon;


    //private PlayerPanel parentPanel;
    
    private UnoCard card;

    //private int zOrder;
    
    public UnoCard getCard() {
        return card;
    }

    /**
     * Class Constructor.
     */
    public PlayerCardButton(UnoCard card/*, PlayerPanel parentPanel*/) {
        super();

        
        this.card = card;
        //this.parentPanel = parentPanel;
        
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

        // adding behavior for interaction with mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                //moveToFront();
                //parentPanel.moveToFront(this);
                // setBounds(origin.x, origin.y - 4, 85, 128);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                //moveBack();
            }

            @Override
            public void mousePressed(MouseEvent me) {
                // AudioManager.getInstance().playSoundEffect("click");
            }
        });
    }
    
    /*private void moveToFront() {
        zOrder = parentPanel.getComponentZOrder(this);
        parentPanel.moveToFront(this);
    }

    private void moveBack() {
        parentPanel.setComponentZOrder(this, zOrder);
    }*/
}
