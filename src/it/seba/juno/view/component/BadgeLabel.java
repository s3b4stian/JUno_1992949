package it.seba.juno.view.component;


import javax.swing.ImageIcon;
import javax.swing.JLabel;

import it.seba.juno.player.BadgeWon;
import it.seba.juno.player.BadgePlayed;

public class BadgeLabel extends JLabel {

    private static final long serialVersionUID = 1L;

    public BadgeLabel(BadgeWon badge) {
        super();
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/badges/" + badge));
        ImageIcon disabledIcon = new ImageIcon(getClass().getResource("/images/badges/" + BadgeWon.NONE));
        initIcons(icon, disabledIcon);
    }

    public BadgeLabel(BadgePlayed badge) {
        super();
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/badges/" + badge));
        ImageIcon disabledIcon = new ImageIcon(getClass().getResource("/images/badges/" + BadgePlayed.NONE));
        
        initIcons(icon, disabledIcon);
    }

    public void initIcons(ImageIcon icon, ImageIcon disabledIcon) {

        setIcon(new ImageIcon(icon.getImage().getScaledInstance(96, 72, java.awt.Image.SCALE_SMOOTH)));
        setDisabledIcon(new ImageIcon(disabledIcon.getImage().getScaledInstance(96, 72, java.awt.Image.SCALE_SMOOTH)));
        setEnabled(false);
    }
}
