package it.seba.juno.view.component;

import java.awt.Color;

import javax.swing.JLabel;

import it.seba.juno.manger.FontManager;

/**
 * Abstract implementation for a text label.
 * 
 * @author Sebastian Rapetti
 *
 */
public class AbstractTextLabel extends JLabel {

    private static final long serialVersionUID = 2432361004376518272L;

    /**
     * Class Constructor.
     * 
     * @param text The text for the label.
     * @param size The size of the font used into the label.
     */
    public AbstractTextLabel(String text, float size) {
        super(text);
        setFont(FontManager.getInstance().getCustomFont(size));
        setForeground(Color.WHITE);
    }
}
