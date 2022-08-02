package it.seba.juno.card;

/**
 * Interface WithValue
 * <p>
 * Used to say that a card has a value
 * </p>
 * 
 * @author Sebastian Rapetti
 *
 * @param <T> The type that identify the value of the card
 */
public interface WithValue<T> {

    /**
     * Returns the value of the card
     *
     * @return the card value
     */
    Value<T> getValue();
}
