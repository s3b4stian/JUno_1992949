package it.seba.juno.player;

import it.seba.juno.card.UnoCard;

/**
 * Drop action for npc players.
 * 
 * @author Sebastian Rapetti
 *
 */
public interface NpcDropAction extends PlayerAction {

    /**
     * Drop a card from cards in hand.
     * 
     * @return A card choose using the drop strategy.
     */
    UnoCard dropCard();
}
