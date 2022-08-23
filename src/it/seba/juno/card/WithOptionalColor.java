package it.seba.juno.card;

/**
 * Used to say that a card can have a color, use this for decks where not all
 * cards have a color.
 * 
 * @author Sebastian Rapetti
 *
 */
public interface WithOptionalColor {

    /**
     * Return if card has color or not
     * 
     * @return True if card has color, false otherwise
     */
    boolean hasColor();
}
