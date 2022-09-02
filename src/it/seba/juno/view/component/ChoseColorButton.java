package it.seba.juno.view.component;

import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ChoseColorButton extends JButton {

    private static final long serialVersionUID = -158374129169260192L;

    /**
     * Button type.
     */
    private final ChoseButtonType type;

    /**
     * Button image icon.
     */
    private final ImageIcon icon;

    private ImageIcon iconDisabled;

    /**
     * The type of the button.
     * 
     * @author Sebastian Rapetti
     *
     */
    public enum ChoseButtonType {

        BLUE("blue"), GREEN("green"), RED("red"), YELLOW("yellow");

        /**
         * Button icon.
         */
        private final String stringIcon;

        /**
         * Enum Constructor.
         * 
         * @param s the file name of the button icon without extension.
         */
        ChoseButtonType(String s) {
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
    public ChoseColorButton(ChoseButtonType t) {
        super();

        type = t;
        icon = new ImageIcon(getClass().getResource("/images/icons/select-color-" + type.getStringIcon() + ".png"));
        iconDisabled = new ImageIcon(getClass().getResource("/images/icons/select-color-disabled.png"));

        // button properties
        setEnabled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setMargin(new Insets(0, 0, 0, 0));
        setIcon(icon);
        setDisabledIcon(iconDisabled);
    }
}
