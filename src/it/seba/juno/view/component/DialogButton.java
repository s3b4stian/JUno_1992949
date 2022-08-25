package it.seba.juno.view.component;

import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import it.seba.juno.manger.AudioManager;

/**
 * Button for modal dialog.
 * 
 * @author Sebastian Rapetti
 *
 */
public class DialogButton extends JButton {

    private static final long serialVersionUID = -1904119797090591322L;

    /**
     * Button type.
     */
    private final DialogButtonType type;

    /**
     * Button image icon.
     */
    private final ImageIcon icon;

    /**
     * Button image icon on mouse over.
     */
    private final ImageIcon iconHover;

    /**
     * The type of the button.
     * 
     * @author Sebastian Rapetti
     *
     */
    public enum DialogButtonType {

        /**
         * Button cancel, to cancel the action.
         */
        CANCEL("cancel"),

        /**
         * Button confirm, to confirm the action.
         */
        CONFIRM("confirm");

        /**
         * Button icon.
         */
        private final String stringIcon;

        /**
         * Enum Constructor.
         * 
         * @param s the file name of the button icon without extension.
         */
        DialogButtonType(String s) {
            stringIcon = s;
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
     * 
     * @param t the type of the button from the enum.
     */
    public DialogButton(DialogButtonType t) {
        super();

        type = t;
        icon = new ImageIcon(getClass().getResource("/images/icons/" + type.getStringIcon() + ".png"));
        iconHover = new ImageIcon(getClass().getResource("/images/icons/" + type.getStringIcon() + "-hover.png"));

        // button properties
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setMargin(new Insets(-1, 0, -1, 0));
        setIcon(icon);

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

            @Override
            public void mousePressed(MouseEvent me) {
                AudioManager.getInstance().playSoundEffect("click");
            }
        });
    }
}
