package it.seba.juno.card;

/**
 * Abstraction of a Card Suit, implemented as generic to have the possibility to
 * choose the type of the value to represent the suit.
 * 
 * @author Sebastian Rapetti
 *
 * @param <T> The type of the value that represents the suit.
 */
public interface Suit<T> {

    /**
     * Return the suit code.
     * 
     * @return The suit code, type as implemented.
     */
    T getSuitCode();
}