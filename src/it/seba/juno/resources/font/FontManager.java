package it.seba.juno.resources.font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

public class FontManager {

    private static FontManager instance;

    private Font customFont;

    public static FontManager getInstance() {
        if (instance == null)
            instance = new FontManager();
        return instance;
    }

    public void setCustomFont(String font) {
        try {
            customFont = Font
                    .createFont(Font.TRUETYPE_FONT,
                            getClass().getResourceAsStream("/it/seba/juno/resources/font/" + font + ".ttf"))
                    .deriveFont(22f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public Font getCustomFont(float size) {
        return customFont.deriveFont(size);
    }
}
