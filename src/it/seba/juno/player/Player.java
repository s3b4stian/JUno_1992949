package it.seba.juno.player;

import java.util.List;

import it.seba.juno.card.UnoCard;

/**
 * Uno card game player.
 *
 * @author Sebastian Rapetti
 *
 */
public interface Player {

    /**
     * Returns the list of cards owned by the player.
     * 
     * @return The list of cards.
     */
    public List<UnoCard> getCards();

    /**
     * Returns the number of cards the player have in hand.
     * 
     * @return The number of cards.
     */
    public int getCardsNumber();

    /**
     * Returns the name of the player.
     * 
     * @return Name of the player.
     */
    public String getName();

    /**
     * Returns the type of the player, human.
     * 
     * @return TSrue if human, false if npc.
     */
    public boolean isHuman();

    /**
     * Returns the type of the player, npc.
     * 
     * @return True if npc, false if human.
     */
    public boolean isNpc();

    /**
     * Returns if the player is the winner of the match.
     * 
     * @return True if the player have no card in hand, false otherwise.
     */
    public boolean isWinner();

    /**
     * Returns if the player have one card in hand and must say UNO!
     * 
     * @return True if have only one card in hand, false otherwise.
     */
    public boolean mustSayUno();

    /**
     * Used to give one card from deck to player.
     * 
     * @param card The card from the deck.
     */
    public void takeCard(UnoCard card);
}
