package it.seba.juno.deck;

import java.util.ArrayDeque;

import it.seba.juno.card.UnoCard;

public class DiscardPile {

    private ArrayDeque<UnoCard> discardPile;
    private UnoCard topCard;

    public DiscardPile() {
        discardPile = new ArrayDeque<UnoCard>();
    }

    public void dropToPile(UnoCard card) {
        discardPile.push(card);
        topCard = card;
    }

    public boolean cardMatch(UnoCard card) {

        // same color and value
        if (topCard.equals(card)) {
            return true;
        }

        // both have a color
        if (!(topCard.hasColor() && card.hasColor())) {
            return false;
        }

        // same color
        if (topCard.getColor().equals(card.getColor())) {
            return true;
        }

        // same value
        if (topCard.getValue().equals(card.getValue())) {
            return true;
        }

        // card not match
        return false;
    }

    public boolean cardMatchColor(UnoCard card) {

        // both have a color
        if (!(topCard.hasColor() && card.hasColor())) {
            return false;
        }

        // same color
        if (topCard.getColor().equals(card.getColor())) {
            return true;
        }

        return false;
    }

    public boolean cardMatchValue(UnoCard card) {
        // same value
        if (topCard.getValue().equals(card.getValue())) {
            return true;
        }

        return false;
    }

    public UnoCard getTopCard() {
        return discardPile.getFirst();
    }
}
