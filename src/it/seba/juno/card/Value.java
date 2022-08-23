package it.seba.juno.card;

/**
 * Abstraction of a Card Value, implemented as generic to have the possibility
 * to choose the type of the value to represent the card value.
 * 
 * @author Sebastian Rapetti
 *
 * @param <T> The type of the value that represents the card value.
 */
public interface Value<T> {

    /**
     * Return the value of a card.
     * 
     * @return The value of a card, type as implemented.
     */
    T getValueCode();
}
