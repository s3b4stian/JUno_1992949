package it.seba.juno.card;

/**
 * Used to say that a card has a suit, not used for Uno Cards because them
 * doen's have a suit, only value and color.
 * 
 * @author Sebastian Rapetti
 *
 * @param <T> The type that identify the value of the suit.
 */
public interface WithSuit<T> {

    /**
     * Returns the suit of the card
     *
     * @return the card suit
     */
    Suit<T> getSuit();
}
