package it.seba.juno.player;

import java.util.List;

import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoColor;

/**
 * Color strategy used by Npc players in game, using the color strategy, the Npc
 * player decide which color choose when drop a card that permit to switch to a
 * new color for the discard pile.
 * 
 * @author Sebastian Rapetti
 *
 */
public interface ColorStrategy {

    /**
     * Return the new color for the discard pile, implemented in concrete
     * strategies.
     * 
     * @return The new color.
     */
    UnoColor changeColor();

    /**
     * Set player card, a strategy use cards to decide what to do.
     * 
     * @param cards The list of player cards.
     */
    void setPlayerCards(List<UnoCard> cards);
}
