package it.seba.juno.player;

import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoValue;
import it.seba.juno.deck.DiscardPile;

/**
 * It is the concrete implementation of a drop strategy where the card dropped
 * have the same value of the top card in discard pile. Value has the priority
 * over the color.
 * 
 * @author Sebastian Rapetti
 *
 */
public class ValueDropStrategy extends AbstractDropStrategy {

    /**
     * Class Constructor.
     * 
     * @param dPile Eeference to discard pile, the place where to drop cards.
     */
    public ValueDropStrategy(DiscardPile dPile) {
        super(dPile);
    }

    /**
     * Drop a card checking first the value, if the npc player has no cards of the
     * same value of the top card in discard pile, checks for the same color.
     * 
     * @return The card to drop.
     */
    @Override
    public UnoCard dropCard() {

        UnoCard c;

        if ((c = this.searchValue()) != null) {
            // official rule
            // drop is forbidden if there is a card that has the color of discard pile
            if (c.getValue().equals(UnoValue.WILD_DRAW_FOUR)) {
                // search for color
                if ((c = this.searchColor()) != null) {
                    return c;
                }
            }

            return c;
        }

        if ((c = this.searchColor()) != null) {
            return c;
        }

        return null;
    }
}
