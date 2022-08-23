package it.seba.juno.card;

/**
 * Used to say that a card has a color.
 * 
 * @author Sebastian Rapetti
 *
 * @param <T> The type that identify the color of the card
 */
public interface WithColor<T> {

    /**
     * Returns the value of the card
     *
     * @return the card color
     */
    Color<T> getColor();
}
