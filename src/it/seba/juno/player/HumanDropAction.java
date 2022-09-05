package it.seba.juno.player;

import it.seba.juno.card.UnoCard;

/**
 * Drop action for human players.
 * 
 * @author Sebastian Rapetti
 *
 */
public interface HumanDropAction extends PlayerAction {

    /**
     * Drop a card from cards in hand.
     * 
     * @param index The index of one card will be dropped.
     * 
     * @return The card at index passed as argument.
     */
    UnoCard dropCard(int index);

    /**
     * Drop a card from cards in hand.
     * 
     * @param card The card the player has and will be dropped.
     * 
     * @return The card passed as argument.
     */
    UnoCard dropCard(UnoCard card);
}
