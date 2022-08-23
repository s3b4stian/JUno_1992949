package it.seba.juno.manger;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

/**
 * Provide a way to manage custom fonts, implemented using singleton pattern, it
 * is possible to use only a font at time.
 * 
 * <p>
 * All fonts, ttf format, files should be placed in resources/font folder
 * </p>
 * 
 * @author Sebastian Rapetti
 *
 */
public class FontManager {

    private static FontManager instance;

    private Font customFont;

    /**
     * Returns the only one instance of the FontManager.
     * 
     * @return the font manager.
     */
    public static FontManager getInstance() {
        if (instance == null)
            instance = new FontManager();
        return instance;
    }

    /**
     * Load custom font from disk.
     * 
     * @param font the name of the font without extension (ex arial and not
     *             arial.ttf).
     */
    public void setCustomFont(String font) {
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/font/" + font + ".ttf"))
                    .deriveFont(22f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return the custom font.
     * 
     * @param size the size of the custom font.
     * 
     * @return the custom font.
     */
    public Font getCustomFont(float size) {
        return customFont.deriveFont(size);
    }
}
