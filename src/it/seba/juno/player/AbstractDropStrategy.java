package it.seba.juno.player;

import java.util.List;

import it.seba.juno.card.UnoCard;
import it.seba.juno.deck.DiscardPile;

/**
 * Abstract implementation for the drop strategy used by Npc players in game,
 * using the drop strategy the Npc player decide which card drop to discard
 * pile.
 * 
 * @author Sebastian Rapetti
 *
 */
public abstract class AbstractDropStrategy implements DropStrategy {

    protected List<UnoCard> cards;
    protected DiscardPile discardPile;

    /**
     * Class Constructor.
     * 
     * @param dPile reference to discard pile, the place where to drop cards.
     */
    public AbstractDropStrategy(DiscardPile dPile) {
        discardPile = dPile;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract UnoCard dropCard();

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayerCards(List<UnoCard> cards) {
        this.cards = cards;
    }

    /**
     * Search in player card for a card that match the current discard pile color,
     * this is a service method used by subclasses aka concrete implementation of a
     * drop strategy.
     * 
     * @return the card that match color.
     */
    protected UnoCard searchColor() {
        for (UnoCard c : cards) {
            if (discardPile.cardMatchColor(c)) {
                cards.remove(c);
                return c;
            }
        }

        return null;
    }

    /**
     * Search in player card for a card that match the current top card value in
     * discard pile, this is a service method used by subclasses aka concrete
     * implementation of a drop strategy.
     * 
     * @return the card that match the value.
     */
    protected UnoCard searchValue() {

        for (UnoCard c : cards) {
            if (discardPile.cardMatchValue(c)) {
                cards.remove(c);
                return c;
            }
        }

        return null;
    }
}
