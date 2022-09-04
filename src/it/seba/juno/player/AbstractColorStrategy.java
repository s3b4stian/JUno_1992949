package it.seba.juno.player;

import java.util.List;

import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoColor;

/**
 * Abstract implementation for the color strategy used by Npc players in game,
 * using the color strategy, the Npc player decide which color choose when drop
 * a card that permit to switch to a new color for the discard pile.
 * 
 * @author Sebastian Rapetti
 *
 */
public abstract class AbstractColorStrategy implements ColorStrategy {

    /**
     * The player cards.
     */
    protected List<UnoCard> cards;

    /**
     * Class Constructor.
     */
    public AbstractColorStrategy() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    abstract public UnoColor changeColor();

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayerCards(List<UnoCard> cards) {
        this.cards = cards;
    }
}
