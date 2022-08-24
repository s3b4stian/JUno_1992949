package it.seba.juno.card;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;

/**
 * The colors in Uno Game, implements the interface Color using a String for the
 * color code.
 * 
 * @author Sebastian Rapetti
 *
 */
public enum UnoColor implements Color<String> {
    BLUE("#0000ff"), RED("#ff0000"), GREEN("#00ff00"), YELLOW("#ffff00");

    private final String value;

    /**
     * Class Constructor.
     * 
     * @param value Hex code of the color.
     */
    UnoColor(String value) {
        this.value = value;
    }

    /**
     * Return the hex code of the color as string.
     * 
     * @return String Hex code.
     */
    @Override
    public String getColorCode() {
        return value;
    }

    /**
     * Return a random color.
     * 
     * @return UnoColor Random color from the Enumeration.
     */
    public static UnoColor getRandom() {
        // return a random color
        // convert the enum in a list and get a random index using secure random
        return Collections.unmodifiableList(Arrays.asList(UnoColor.values()))
                .get((new SecureRandom()).nextInt(UnoColor.values().length));
    }
}
