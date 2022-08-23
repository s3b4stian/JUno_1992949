package it.seba.juno.deck;

import java.security.SecureRandom;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import it.seba.juno.card.UnoCard;

/**
 * Deck of cards for the Uno game.
 *
 * @author Sebastian Rapetti
 *
 */
public class UnoDeck implements Deck {

    private List<UnoCard> initDeck;
    private Deque<UnoCard> finalDeck;
    private int cardsInDeck;

    /**
     * Class constructor.
     * 
     * @param deck the list of cards to create the deck, all Uno valid cards.
     */
    public UnoDeck(List<UnoCard> deck) {
        initDeck = deck;
        finalDeck = new ArrayDeque<UnoCard>();
        cardsInDeck = deck.size();
    }

    /**
     * Permit to refill the deck with dealt cards, accept a list of cards.
     *
     * @param cards list of cards to refill the deck.
     */
    public void refill(List<UnoCard> cards) {
        Collections.shuffle(cards, new SecureRandom());
        cards.forEach(e -> finalDeck.push(e));
        cardsInDeck = finalDeck.size();
    }

    /**
     * Permit to refill the deck with a dealt card, accept a card and put it at the
     * end of the deck.
     *
     * @param card card to refill the deck.
     */
    public void refill(UnoCard card) {
        finalDeck.addLast(card);
        cardsInDeck++;
    }

    /**
     * Re-initialize the deck.
     */
    @Override
    public void shuffle() {
        Collections.shuffle(initDeck, new SecureRandom());

        finalDeck.clear();
        initDeck.forEach(e -> finalDeck.push(e));
    }

    /**
     * Remove the top card from the deck.
     */
    @Override
    public UnoCard dealCard() {
        cardsInDeck--;
        return finalDeck.pop();
    }

    /**
     * Check if the deck is empty.
     * 
     * @return True if empty, false otherwise.
     */
    public boolean isEmpty() {
        return finalDeck.isEmpty();
    }

    /**
     * Return the number of the cards remaining into the deck.
     * 
     * @return number of cards in deck.
     */
    public int getCardsInDeck() {
        return cardsInDeck;
    }
}
