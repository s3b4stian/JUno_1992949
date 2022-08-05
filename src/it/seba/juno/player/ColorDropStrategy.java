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

        /*
         * for (UnoCard c : cards) { if (discardPile.cardMatchColor(c)) {
         * cards.remove(c); return c; } }
         * 
         * for (UnoCard c : cards) { if (discardPile.cardMatchValue(c)) {
         * cards.remove(c); return c; } }
         */

        return null;
    }
}
