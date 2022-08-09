package it.seba.juno.player;

import it.seba.juno.card.UnoCard;
import it.seba.juno.deck.DiscardPile;

public class ColorDropStrategy extends AbstractDropStrategy {

    public ColorDropStrategy(DiscardPile dPile) {
        super(dPile);
    }

    @Override
    public UnoCard dropCard() {

        UnoCard c;

        if ((c = this.searchColor()) != null) {
            return c;
        }

        if ((c = this.searchValue()) != null) {
            return c;
        }

        return null;
    }
}
