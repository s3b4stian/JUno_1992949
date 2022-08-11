package it.seba.juno.view.component;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

import it.seba.juno.manger.AudioManager;

public class OptionsRadioFourPlayers extends JRadioButton {

    private static final long serialVersionUID = 1L;

    public OptionsRadioFourPlayers() {
        super();
        
        setOpaque(false);
        setFocusPainted(false);
        setIcon(new ImageIcon(getClass().getResource("/images/icons/p4.png")));
        setSelectedIcon(new ImageIcon(getClass().getResource("/images/icons/p4s.png")));
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                AudioManager.getInstance().playSoundEffect("click");
            }
        });
    }
}
