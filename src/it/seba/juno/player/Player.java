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
     * Returns the name of the player.
     * 
     * @return name of the player.
     */
    public String getName();

    /**
     * Returns if the player is the winner of the match.
     * 
     * @return true if the player have no card in hand, false otherwise.
     */
    public boolean isWinner();

    /**
     * Returns if the player have one card in hand and must say UNO!
     * 
     * @return true if have only one card in hand, false otherwise.
     */
    public boolean mustSayUno();

    /**
     * Used to give one card from deck to player.
     * 
     * @param card the card from the deck.
     */
    public void takeCard(UnoCard card);

    /**
     * Returns the number of cards the player have in hand.
     * 
     * @return the number of cards.
     */
    public int getCardsNumber();

    /**
     * Returns the list of cards owned by the player.
     * 
     * @return the list of cards.
     */
    public List<UnoCard> getCards();

    /**
     * Returns the type of the player, npc.
     * 
     * @return true if npc, false if human.
     */
    public boolean isNpc();
    
    /**
     * Returns the type of the player, human.
     * 
     * @return true if human, false if npc.
     */
    public boolean isHuman();
}
