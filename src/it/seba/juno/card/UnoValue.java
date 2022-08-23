package it.seba.juno.card;

/**
 * The values in Uno Game, implements the interface Value using a Integer for
 * the card value.
 * 
 * @author Sebastian Rapetti
 *
 */
public enum UnoValue implements Value<Integer> {

    ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGTH(8), NINE(9), SKIP(10), REVERSE(20),
    DRAW_TWO(40), WILD(100), WILD_DRAW_FOUR(200);

    private final Integer value;

    /**
     * Class Constructor.
     * 
     * @param value Integer value of the card.
     */
    UnoValue(int value) {
        this.value = value;
    }

    /**
     * Return the value of the card as Integer.
     * 
     * @return Integer Card value.
     */
    @Override
    public Integer getValueCode() {
        return value;
    }
}
