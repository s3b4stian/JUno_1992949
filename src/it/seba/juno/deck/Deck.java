package it.seba.juno.deck;

import it.seba.juno.card.Card;

/**
 * Abstraction of a deck of cards.
 * 
 * @author Sebastian Rapetti
 *
 */
public interface Deck {

    /**
     * Deal a card from deck.
     * 
     * @return Top UnoCard in the deck.
     */
    Card dealCard();

    /**
     * Shuffle the deck.
     */
    public void shuffle();
}
