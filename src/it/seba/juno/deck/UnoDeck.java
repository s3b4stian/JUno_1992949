package it.seba.juno.deck;

import java.security.SecureRandom;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import it.seba.juno.card.UnoCard;

public class UnoDeck implements Deck {

    private List<UnoCard> initDeck;
    private Deque<UnoCard> finalDeck;
    private int cardsInDeck;

    public UnoDeck(List<UnoCard> deck) {
        initDeck = deck;
        finalDeck = new ArrayDeque<UnoCard>();
        cardsInDeck = deck.size();
    }

    public void refill(List<UnoCard> cards) {
        Collections.shuffle(cards, new SecureRandom());
        cards.forEach(e -> finalDeck.push(e));
        cardsInDeck = finalDeck.size();
    }

    public void refill(UnoCard card) {
        finalDeck.addLast(card);
        cardsInDeck++;
    }

    @Override
    public void shuffle() {
        Collections.shuffle(initDeck, new SecureRandom());

        finalDeck.clear();
        initDeck.forEach(e -> finalDeck.push(e));
    }

    @Override
    public UnoCard dealCard() {
        cardsInDeck--;
        return finalDeck.pop();
    }

    public boolean isEmpty() {
        return finalDeck.isEmpty();
    }

    public int getCardsInDeck() {
        return cardsInDeck;
    }
}
