package it.seba.juno.view.component;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import it.seba.juno.manger.AudioManager;

public class OptionsButtonSound extends JToggleButton {

    private static final long serialVersionUID = 1L;
    
    public OptionsButtonSound() {
        super();

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setIcon(new ImageIcon(getClass().getResource("/images/icons/sound-off.png")));
        setSelectedIcon(new ImageIcon(getClass().getResource("/images/icons/sound-on.png")));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                AudioManager.getInstance().playSoundEffect("click");
            }
        });
    }
}
