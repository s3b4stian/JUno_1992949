package it.seba.metodologie.juno.card;

import java.util.ArrayDeque;

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
