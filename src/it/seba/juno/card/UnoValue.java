package it.seba.juno.card;

/**
 * The values in Uno Game, implements the interface Value using a Integer for
 * the card value.
 * 
 * @author Sebastian Rapetti
 *
 */
public enum UnoValue implements Value<Integer> {

    /**
     * Zero card.
     */
    ZERO(0),

    /**
     * One card.
     */
    ONE(1),

    /**
     * Two card.
     */
    TWO(2),

    /**
     * Three card.
     */
    THREE(3),

    /**
     * Four card.
     */
    FOUR(4),

    /**
     * Five card.
     */
    FIVE(5),

    /**
     * Six card.
     */
    SIX(6),

    /**
     * Seven card.
     */
    SEVEN(7),

    /**
     * Eigth card.
     */
    EIGTH(8),

    /**
     * Nine card.
     */
    NINE(9),

    /**
     * Skip card, when dropped next player skip the turn.
     */
    SKIP(10),

    /**
     * Reverse card, when dropped change the order of play.
     */
    REVERSE(20),

    /**
     * Draw Two card, when dropped force the next player to draw two cards from
     * deck.
     */
    DRAW_TWO(40),

    /**
     * Wild card, when dropped the player choose the new color for the drop pile.
     */
    WILD(100),

    /**
     * Wild card, when dropped the player choose the new color for the drop pile and
     * force the next players to draw four cards from deck.
     */
    WILD_DRAW_FOUR(200);

    /**
     * The value of the card.
     */
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
