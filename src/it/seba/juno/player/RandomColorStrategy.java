package it.seba.juno.player;

import it.seba.juno.card.UnoColor;

public class RandomColorStrategy extends AbstractColorStrategy {

    @Override
    public UnoColor changeColor() {

        return UnoColor.getRandom();
    }
}
