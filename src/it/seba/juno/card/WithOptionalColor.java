package it.seba.juno.card;

/**
 * Interface WithOptionalColor
 *
 * <p>
 * Used to say that a card can have a color, use this for decks where not all
 * cards have a color.
 * </p>
 * 
 * @author Sebastian Rapetti
 *
 */
public interface WithOptionalColor {

    /**
     * Return if card has color or not
     * 
     * @return true if card has color, false otherwise
     */
    boolean hasColor();
}
