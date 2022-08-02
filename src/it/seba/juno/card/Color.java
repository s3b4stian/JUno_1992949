package it.seba.juno.card;

/**
 * Interface Color
 * <p>
 * Identify a color of a card
 * </p>
 * 
 * @author Sebastian Rapetti
 *
 * @param <T> The type of the value that represents the color.
 */
public interface Color<T> {

    T getColorCode();
}
