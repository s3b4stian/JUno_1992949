package it.seba.juno.view.component;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import it.seba.juno.sound.AudioManager;

public class OptionsButtonFullScreen extends JToggleButton {

    private static final long serialVersionUID = 1L;

    //private AudioManager audioManager;
    
    public OptionsButtonFullScreen() {
        super();

        //audioManager = AudioManager.getInstance();
        
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setIcon(new ImageIcon(getClass().getResource("/it/seba/juno/resources/images/icons/window.png")));
        setSelectedIcon(new ImageIcon(getClass().getResource("/it/seba/juno/resources/images/icons/full-screen.png")));
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                AudioManager.getInstance().playSoundEffect("click");
            }
        });
    }
}
