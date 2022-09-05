package it.seba.juno.player;

import it.seba.juno.card.UnoCard;
import it.seba.juno.deck.DiscardPile;

/**
 * It is the concrete implementation of a drop strategy where the card dropped
 * have the same color of the discard pile. Color has the priority over the
 * value.
 * 
 * @author Sebastian Rapetti
 *
 */
public class ColorDropStrategy extends AbstractDropStrategy {

    /**
     * Class Constructor.
     * 
     * @param dPile Reference to discard pile, the place where to drop cards.
     */
    public ColorDropStrategy(DiscardPile dPile) {
        super(dPile);
    }

    /**
     * Drop a card checking first the color, if the npc player has no cards of the
     * same color of the discard pile, checks for the same value.
     * 
     * @return The card to drop.
     */
    @Override
    public UnoCard dropCard() {

        UnoCard c;

        // drop first a card that match the color
        if ((c = this.searchColor()) != null) {
            return c;
        }

        // then if no color a card that match the value
        if ((c = this.searchValue()) != null) {
            return c;
        }

        return null;
    }
}
