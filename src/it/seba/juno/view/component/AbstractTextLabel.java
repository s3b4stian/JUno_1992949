package it.seba.juno.view.component;

import java.awt.Color;

import javax.swing.JLabel;

import it.seba.juno.manger.FontManager;

public class AbstractTextLabel extends JLabel {

    private static final long serialVersionUID = 2432361004376518272L;

    public AbstractTextLabel(String text, float size) {
        super(text);
        setFont(FontManager.getInstance().getCustomFont(size));
        setForeground(new Color(255, 255, 255));
    }
}
