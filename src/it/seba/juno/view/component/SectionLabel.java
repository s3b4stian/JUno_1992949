package it.seba.juno.view.component;

import java.awt.Color;

import javax.swing.JLabel;

import it.seba.juno.manger.FontManager;

/**
 * Label to title a section in a view.
 * 
 * @author Sebastian Rapetti
 *
 */
public class SectionLabel extends JLabel {

    private static final long serialVersionUID = -188287675781778913L;

    /**
     * Class Constructor, default text of the label is void.
     */
    public SectionLabel() {
        this("");
    }

    /**
     * Class Constructor.
     * 
     * @param text the default text of the label.
     */
    public SectionLabel(String text) {
        super(text);
        setFont(FontManager.getInstance().getCustomFont(36f));
        setForeground(new Color(255, 255, 255));
    }
}
