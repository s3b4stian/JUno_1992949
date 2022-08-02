package it.seba.juno.card;

/**
 * Interface Suit
 * <p>
 * Identify a suit of a card
 * </p>
 * 
 * @author Sebastian Rapetti
 *
 * @param <T> The type of the value that represents the suit.
 */
public interface Suit<T> {

    T getSeedCode();
}