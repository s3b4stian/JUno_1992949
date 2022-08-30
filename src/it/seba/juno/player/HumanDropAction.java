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
     * @param index the index of one card in hand.
     * 
     * @return the card at index passed as argument.
     */
    UnoCard dropCard(int index);
    
    UnoCard dropCard(UnoCard card);
}
