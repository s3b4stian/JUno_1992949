package it.seba.juno.player;

import java.util.List;

import it.seba.juno.card.UnoCard;

/**
 * Drop strategy used by Npc players in game, using the drop strategy the Npc
 * player decide which card drop to discard pile.
 * 
 * @author Sebastian Rapetti
 *
 */
public interface DropStrategy {

    /**
     * Return the card to drop to the discard pile, implemented in concrete
     * strategies.
     * 
     * @return the card to drop.
     */
    UnoCard dropCard();

    /**
     * Set player card, a strategy use cards to decide what to do.
     * 
     * @param cards the list of player cards.
     */
    void setPlayerCards(List<UnoCard> cards);
}
