package it.seba.juno.view.component;

import java.awt.Color;

import javax.swing.JLabel;

import it.seba.juno.manger.FontManager;

public class SectionLabel extends JLabel {

    private static final long serialVersionUID = 1L;

    public SectionLabel() {
        this("");
    }

    public SectionLabel(String text) {
        super(text);
        setFont(FontManager.getInstance().getCustomFont(36f));
        setForeground(new Color(255, 255, 255));
    }
}
