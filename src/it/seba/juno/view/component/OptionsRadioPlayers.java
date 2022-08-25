package it.seba.juno.view.component;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

import it.seba.juno.manger.AudioManager;

/**
 * Option button to select the number of players.
 * 
 * @author Sebastian Rapetti
 *
 */
public class OptionsRadioPlayers extends JRadioButton {

    private static final long serialVersionUID = -1104993535973891068L;

    /**
     * the type of the button, which icon the button must show.
     */
    private final OptionRadioType type;

    /**
     * The type of the button.
     * 
     * @author Sebastian Rapetti
     *
     */
    public enum OptionRadioType {

        /**
         * Two players button.
         */
        TWO("p2"),

        /**
         * Three players button.
         */
        THREE("p3"),

        /**
         * Four players button.
         */
        FOUR("p4");

        private final String stringIcon;

        /**
         * Class Constructor.
         * 
         * @param icon the file name without extension.
         */
        OptionRadioType(String icon) {
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
    public OptionsRadioPlayers(OptionRadioType t) {
        super();

        type = t;

        // button properties
        setOpaque(false);
        setFocusPainted(false);
        setIcon(new ImageIcon(getClass().getResource("/images/icons/" + type.getType() + ".png")));
        setSelectedIcon(new ImageIcon(getClass().getResource("/images/icons/" + type.getType() + "s.png")));

        // adding behavior for interaction with mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                AudioManager.getInstance().playSoundEffect("click");
            }
        });
    }
}
