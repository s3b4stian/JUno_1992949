package it.seba.juno.view.component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import it.seba.juno.player.BadgePlayed;

public class BadgePlayedLabel extends JLabel {

    private static final long serialVersionUID = 1L;

    public BadgePlayedLabel(BadgePlayed badge) {
        super();
        
        Icon icon = new ImageIcon((new ImageIcon(getClass().getResource("/images/badges/" + badge))).getImage().getScaledInstance(96, 72, java.awt.Image.SCALE_SMOOTH));
        setIcon(icon);
        
        Icon disabledIcon = new ImageIcon((new ImageIcon(getClass().getResource("/images/badges/" + BadgePlayed.NONE))).getImage().getScaledInstance(96, 72, java.awt.Image.SCALE_SMOOTH));
        setDisabledIcon(disabledIcon);
        
        setEnabled(false);
    }
}
