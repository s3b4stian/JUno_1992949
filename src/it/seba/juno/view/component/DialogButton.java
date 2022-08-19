package it.seba.juno.view.component;

import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import it.seba.juno.manger.AudioManager;

public class DialogButton extends JButton {

    private static final long serialVersionUID = 1L;

    private final DialogButtonType type;
    private final ImageIcon icon;
    private final ImageIcon iconHover;
    
    
    public enum DialogButtonType {
        CANCEL("cancel"), CONFIRM("confirm");

        private final String stringIcon;

        DialogButtonType(String s) {
            stringIcon = s;
        }

        public String getStringIcon() {
            return stringIcon;
        }
    }

    public DialogButton(DialogButtonType t) {
        super();

        this.type = t;
        this.icon = new ImageIcon(getClass().getResource("/images/icons/" + type.getStringIcon() + ".png"));
        this.iconHover = new ImageIcon(getClass().getResource("/images/icons/" + type.getStringIcon() + "-hover.png"));

        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setMargin(new Insets(-1,0,-1,0));
        setIcon(icon);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setIcon(iconHover);
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setIcon(icon);
            }

            @Override
            public void mousePressed(MouseEvent me) {
                AudioManager.getInstance().playSoundEffect("click");
            }
        });
    }
}
