package it.seba.juno.player;

import it.seba.juno.card.UnoColor;

/**
 * Change color action for npc players.
 * 
 * @author Sebastian Rapetti
 *
 */
public interface NpcChangeColorAction extends PlayerAction {

    /**
     * Returns a new color for the discard pile.
     * 
     * @return A color choose using the color strategy.
     */
    UnoColor changeColor();
}
