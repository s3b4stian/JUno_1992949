package it.seba.juno.manger;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.HashMap;

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

    /**
     * The font manager instance, static for singleton pattern.
     */
    private static FontManager instance;

    /**
     * Returns the only one instance of the FontManager.
     * 
     * @return The font manager.
     */
    public static FontManager getInstance() {
        if (instance == null)
            instance = new FontManager();
        return instance;
    }

    /**
     * Class Constructor.
     */
    private FontManager() {

    }

    /**
     * The custom font managed by this class.
     */
    private Font customFont;

    /**
     * Return the custom font.
     * 
     * @param size the size of the custom font.
     * 
     * @return The custom font.
     */
    public Font getCustomFont(float size) {
        return customFont.deriveFont(size);
    }

    /**
     * Load custom font from disk.
     * 
     * @param font The name of the font without extension (ex. arial and not
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
}
