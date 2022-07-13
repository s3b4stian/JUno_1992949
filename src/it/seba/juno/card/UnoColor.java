package it.seba.juno.card;

public enum UnoColor implements Color<String> {
    BLUE("#0000ff"), RED("#ff0000"), GREEN("#00ff00"), YELLOW("#ffff00");

    private final String value;

    UnoColor(String value) {
        this.value = value;
    }

    @Override
    public String getColorCode() {
        return value;
    }
}
