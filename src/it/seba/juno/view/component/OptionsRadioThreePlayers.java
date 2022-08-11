package it.seba.juno.view.component;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

import it.seba.juno.manger.AudioManager;

public class OptionsRadioThreePlayers extends JRadioButton {

    private static final long serialVersionUID = 1L;

    public OptionsRadioThreePlayers() {
        super();
        
        setOpaque(false);
        setFocusPainted(false);
        setIcon(new ImageIcon(getClass().getResource("/it/seba/juno/resources/images/icons/p3.png")));
        setSelectedIcon(new ImageIcon(getClass().getResource("/it/seba/juno/resources/images/icons/p3s.png")));
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                AudioManager.getInstance().playSoundEffect("click");
            }
        });
    }
}
