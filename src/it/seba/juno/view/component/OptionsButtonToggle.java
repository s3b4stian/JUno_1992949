package it.seba.juno.view.component;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import it.seba.juno.manger.AudioManager;

/**
 * Option button for sound and screen, it can be selected or unselected, act
 * like a switch.
 * 
 * @author Sebastian Rapetti
 *
 */
public class OptionsButtonToggle extends JToggleButton {

    private static final long serialVersionUID = 6180177594204484803L;

    /**
     * the type of the button, which icon the button must show.
     */
    private final OptionButtonType type;

    /**
     * The type of the button.
     * 
     * @author Sebastian Rapetti
     *
     */
    public enum OptionButtonType {
        /**
         * Button to manage screen mode.
         */
        FULLSCREEN("fullscreen"),

        /**
         * Button to manage sound.
         */
        SOUND("sound");

        private final String stringIcon;

        /**
         * Class Constructor.
         * 
         * @param icon the file name without extension.
         */
        OptionButtonType(String icon) {
            stringIcon = icon;
        }

        /**
         * Returns the file name of the icon without extension.
         * 
         * @return the file name.
         */
        public String getType() {
            return stringIcon;
        }
    }

    /**
     * Class Constructor.
     * 
     * @param t the type of the button from the enum.
     */
    public OptionsButtonToggle(OptionButtonType t) {
        super();

        type = t;

        // button properties
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setIcon(new ImageIcon(getClass().getResource("/images/icons/" + type.getType() + "-off.png")));
        setSelectedIcon(new ImageIcon(getClass().getResource("/images/icons/" + type.getType() + "-on.png")));

        // adding behavior for interaction with mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                AudioManager.getInstance().playSoundEffect("click");
            }
        });
    }
}
