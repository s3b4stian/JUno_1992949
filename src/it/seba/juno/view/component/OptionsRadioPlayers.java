package it.seba.juno.view.component;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

import it.seba.juno.manger.AudioManager;

public class OptionsRadioPlayers extends JRadioButton {

    private static final long serialVersionUID = 1L;

    private final OptionRadioType type;

    public enum OptionRadioType {
        TWO("p2"), THREE("p3"), FOUR("p4");

        private final String stringIcon;

        OptionRadioType(String s) {
            stringIcon = s;
        }

        public String getType() {
            return stringIcon;
        }
    }

    public OptionsRadioPlayers(OptionRadioType t) {
        super();

        type = t;

        setOpaque(false);
        setFocusPainted(false);
        setIcon(new ImageIcon(getClass().getResource("/images/icons/" + type.getType() + ".png")));
        setSelectedIcon(new ImageIcon(getClass().getResource("/images/icons/" + type.getType() + "s.png")));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                AudioManager.getInstance().playSoundEffect("click");
            }
        });
    }
}
