package it.seba.juno.card;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;

public enum UnoColor implements Color<String> {
    BLUE("#0000ff"), RED("#ff0000"), GREEN("#00ff00"), YELLOW("#ffff00");

    private final String value;
    //private final SecureRandom random = new SecureRandom();
    
    UnoColor(String value) {
        this.value = value;
    }

    @Override
    public String getColorCode() {
        return value;
    }

    public static UnoColor getRandom() {
        // return a random color
        // convert the enum in a list and get a random index using secure random
        return Collections.unmodifiableList(Arrays.asList(UnoColor.values())).get((new SecureRandom()).nextInt(UnoColor.values().length));
    }
}
