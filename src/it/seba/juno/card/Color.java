package it.seba.juno.card;

/**
 * Abstraction of a Card Color, implemented as generic to have the possibility
 * to choose the type of the value to represent the color.
 * 
 * @author Sebastian Rapetti
 *
 * @param <T> The type of the value that represents the color.
 */
public interface Color<T> {

    /**
     * Return the color code.
     * 
     * @return The color code, type as implemented.
     */
    T getColorCode();
}
