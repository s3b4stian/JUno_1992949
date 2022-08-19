package it.seba.juno.view.component;

import java.awt.Color;
import javax.swing.JTextField;
import it.seba.juno.manger.FontManager;

public class TextFieldNewPlayerModal extends JTextField {

    private static final long serialVersionUID = 1L;

    public TextFieldNewPlayerModal() {
        super("Player Name", 16);
        setFont(FontManager.getInstance().getCustomFont(22f));
        setOpaque(false);
        setBorder(null);
        setForeground(new Color(255, 255, 255));
    }
}
