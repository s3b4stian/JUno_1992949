package it.seba.juno.view.component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Badge component for views.
 * 
 * @author Sebastian Rapetti
 *
 */
public class BadgeLabel extends JLabel {

    private static final long serialVersionUID = -2880256707890055222L;

    /**
     * Class Constructor.
     * 
     * @param badge instance of badge assigned for victories.
     */
    public BadgeLabel(BadgeWon badge) {
        super();
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/badges/" + badge));
        ImageIcon disabledIcon = new ImageIcon(getClass().getResource("/images/badges/" + BadgeWon.NONE));
        initIcons(icon, disabledIcon);
    }

    /**
     * Class Constructor.
     * 
     * @param badge instance of badge assigned for played matches.
     */
    public BadgeLabel(BadgePlayed badge) {
        super();
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/badges/" + badge));
        ImageIcon disabledIcon = new ImageIcon(getClass().getResource("/images/badges/" + BadgePlayed.NONE));

        initIcons(icon, disabledIcon);
    }

    /**
     * Set icons for the label.
     * 
     * @param icon         the icon for the enabled label.
     * @param disabledIcon the icon for the disabled label.
     */
    private void initIcons(ImageIcon icon, ImageIcon disabledIcon) {

        setIcon(new ImageIcon(icon.getImage().getScaledInstance(96, 72, java.awt.Image.SCALE_SMOOTH)));
        setDisabledIcon(new ImageIcon(disabledIcon.getImage().getScaledInstance(96, 72, java.awt.Image.SCALE_SMOOTH)));
        setEnabled(false);
    }
}
