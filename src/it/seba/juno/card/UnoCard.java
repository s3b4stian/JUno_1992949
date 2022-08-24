package it.seba.juno.card;

/**
 * UnoCard, card for the game UNO, this type of card has a value and may have a
 * color
 * 
 * @author Sebastian Rapetti
 * 
 */
public class UnoCard implements Card, WithColor<String>, WithValue<Integer>, WithOptionalColor, Comparable<UnoCard> {

    private final UnoValue value;
    private final UnoColor color;

    private boolean hasColor = true;

    /**
     * Class Constructor.
     * 
     * @param value Value of the card from UnoValue Enum type
     */
    public UnoCard(UnoValue value) {
        this(value, null);
        hasColor = false;
    }

    /**
     * Class Constructor.
     * 
     * @param value Value of the card from UnoValue Enum type
     * @param color Color of the card from UnoColor Enum type
     */
    public UnoCard(UnoValue value, UnoColor color) {
        this.value = value;
        this.color = color;
    }

    /**
     * Compare current UNO cards with another and returns true if card are equals,
     * false otherwise
     * 
     * @param card Card to compare
     * 
     * @return True if cards are equals, false otherwise
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public UnoValue getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UnoColor getColor() {
        return color;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasColor() {
        return hasColor;
    }

    /**
     * {@inheritDoc}
     * 
     * <p>
     * <b>Overridden method for UnoCard</b>
     * </p>
     * 
     * <p>
     * This is the specific implementation to compare cards in UNO card game
     * </p>
     */
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

    /**
     * {@inheritDoc}
     * 
     * <p>
     * <b>Overridden method for UnoCard</b>
     * </p>
     * 
     * <p>
     * Return a string representation of the UnoCard
     * </p>
     * 
     */
    @Override
    public String toString() {

        if (hasColor) {
            return value.toString() + " [" + color.toString() + "]";
        }

        return value.toString();
    }

}
