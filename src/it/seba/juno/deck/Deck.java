package it.seba.juno.deck;

import it.seba.juno.card.Card;

public interface Deck {

    public void shuffle();

    Card dealCard();
}
