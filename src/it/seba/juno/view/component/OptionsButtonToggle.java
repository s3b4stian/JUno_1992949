package it.seba.juno.view.component;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

import it.seba.juno.manger.AudioManager;

public class OptionsButtonToggle extends JToggleButton {

    private static final long serialVersionUID = 1L;

    private final OptionButtonType type;

    public enum OptionButtonType {
        FULLSCREEN("fullscreen"), SOUND("sound");

        private final String stringIcon;

        OptionButtonType(String s) {
            stringIcon = s;
        }

        public String getType() {
            return stringIcon;
        }
    }

    public OptionsButtonToggle(OptionButtonType t) {
        super();

        type = t;

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setIcon(new ImageIcon(getClass().getResource("/images/icons/" + type.getType() + "-off.png")));
        setSelectedIcon(new ImageIcon(getClass().getResource("/images/icons/" + type.getType() + "-on.png")));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                AudioManager.getInstance().playSoundEffect("click");
            }
        });
    }
}
