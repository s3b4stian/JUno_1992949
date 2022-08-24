package it.seba.juno.view.component;

import java.awt.Color;

import javax.swing.JLabel;

import it.seba.juno.manger.FontManager;

public class MainLabel extends JLabel {

    private static final long serialVersionUID = 1L;

    public MainLabel() {
        this("");
    }

    public MainLabel(String text) {
        super(text);
        setFont(FontManager.getInstance().getCustomFont(72f));
        setForeground(new Color(255, 255, 255));
    }
}
