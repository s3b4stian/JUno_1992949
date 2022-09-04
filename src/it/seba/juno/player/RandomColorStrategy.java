package it.seba.juno.player;

import it.seba.juno.card.UnoColor;

/**
 * It is the concrete implementation of a color strategy where the color is
 * choose random.
 * 
 * @author Sebastian Rapetti
 *
 */
public class RandomColorStrategy extends AbstractColorStrategy {

    /**
     * Returns a random color.
     * 
     * @return The color
     */
    @Override
    public UnoColor changeColor() {
        return UnoColor.getRandom();
    }
}
