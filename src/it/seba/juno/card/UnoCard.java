package it.seba.juno.card;

public class UnoCard implements Card, WithColor<String>, WithValue<Integer>, WithOptionalColor, Comparable<UnoCard> {

    private final UnoValue value;
    private final UnoColor color;

    private boolean hasColor = true;

    public UnoCard(UnoValue value) {
        this(value, null);
        hasColor = false;
    }

    public UnoCard(UnoValue value, UnoColor color) {
        this.value = value;
        this.color = color;
    }

    public boolean equals(UnoCard card) {
        // check for the same object in memory
        if (card == this)
            return true;

        // check for color presence
        if (card.hasColor() != this.hasColor)
            return false;

        // check for equal value
        if (!card.getValue().equals(value))
            return false;

        // check for equal color
        if (!card.getColor().equals(color))
            return false;

        return true;
    }

    @Override
    public UnoValue getValue() {
        return value;
    }

    @Override
    public UnoColor getColor() {
        return color;
    }

    @Override
    public boolean hasColor() {
        return hasColor;
    }

    @Override
    public int compareTo(UnoCard o) {

        if (hasColor && o.hasColor() == this.hasColor) {

            int colorCompare = o.getColor().compareTo(getColor());

            if (colorCompare != 0) {
                return colorCompare;
            }
            return -o.getValue().compareTo(getValue());
        }

        return -o.getValue().compareTo(getValue());
    }

}
