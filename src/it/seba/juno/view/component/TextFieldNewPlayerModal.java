package it.seba.juno.view.component;

import java.awt.Color;

import javax.swing.JTextField;

import it.seba.juno.manger.FontManager;

/**
 * The text file for new player modal dialog, the place where insert the new
 * player name.
 * 
 * @author Sebastian Rapetti
 *
 */
public class TextFieldNewPlayerModal extends JTextField {

    private static final long serialVersionUID = -2933300469612609745L;

    /**
     * Class Constructor.
     */
    public TextFieldNewPlayerModal() {
        super("Player Name", 16);
        // set text field properties
        setFont(FontManager.getInstance().getCustomFont(22f));
        setOpaque(false);
        setBorder(null);
        setForeground(Color.WHITE);
    }
}
