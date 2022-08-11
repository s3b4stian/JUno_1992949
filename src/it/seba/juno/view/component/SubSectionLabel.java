package it.seba.juno.view.component;

import java.awt.Color;

import javax.swing.JLabel;

import it.seba.juno.manger.FontManager;

public class SubSectionLabel extends JLabel{


    private static final long serialVersionUID = 1L;

    public SubSectionLabel() {
        this("");
    }

    public SubSectionLabel(String text) {
        super(text);
        setFont(FontManager.getInstance().getCustomFont(24f));
        setForeground(new Color(255, 255, 255));
    }
}
