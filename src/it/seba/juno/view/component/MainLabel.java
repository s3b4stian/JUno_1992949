package it.seba.juno.view.component;

import java.awt.Color;

import javax.swing.JLabel;

import it.seba.juno.manger.FontManager;

/**
 * Label to title a view.
 * 
 * @author Sebastian Rapetti
 *
 */
public class MainLabel extends JLabel {

    private static final long serialVersionUID = -6258378281134430686L;

    /**
     * Class Constructor, default text of the label is void.
     */
    public MainLabel() {
        this("");
    }

    /**
     * Class Constructor.
     * 
     * @param text the default text of the label.
     */
    public MainLabel(String text) {
        super(text);
        setFont(FontManager.getInstance().getCustomFont(72f));
        setForeground(new Color(255, 255, 255));
    }
}
