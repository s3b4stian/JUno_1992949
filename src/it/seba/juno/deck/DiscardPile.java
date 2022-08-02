package it.seba.juno.deck;

import java.util.ArrayDeque;
import java.util.List;
import java.util.stream.Collectors;

import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoColor;
import it.seba.juno.card.UnoValue;

public class DiscardPile {

    private ArrayDeque<UnoCard> discardPile;
    private UnoCard topCard;
    private UnoColor currentColor;

    public DiscardPile() {
        discardPile = new ArrayDeque<UnoCard>();
    }

    public void dropToPile(UnoCard card) {

        if (card.hasColor()) {
            currentColor = card.getColor();
        }

        discardPile.push(card);
        topCard = card;
    }

    public boolean cardMatch(UnoCard card) {

        // System.out.println("colors: " + topCard.getColor()+ " " + currentColor + " "
        // + card.getColor());

        // same color and value
        if (topCard.equals(card)) {
            return true;
        }

        // special case when the top card is a wild
        if (card.hasColor() && currentColor != null && currentColor.equals(card.getColor())) {
            return true;
        }

        // both have a color
        if (!(topCard.hasColor() && card.hasColor())) {
            return false;
        }

        // same color
        if (topCard.getColor().equals(card.getColor()) || currentColor.equals(card.getColor())) {
            return true;
        }
        // if (currentColor.equals(card.getColor())) {
        // return true;
        // }

        // same value
        if (topCard.getValue().equals(card.getValue())) {
            return true;
        }

        // card not match
        return false;
    }

    public boolean cardMatchColor(UnoCard card) {

        // System.out.println("colors: " + topCard.getColor()+ " " + currentColor + " "
        // + card.getColor());

        // special case when the top card is a wild
        if (card.hasColor() && currentColor != null && currentColor.equals(card.getColor())) {
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

        return false;
    }

    public boolean cardMatchValue(UnoCard card) {
        // same value
        if (topCard.getValue().equals(card.getValue())) {
            return true;
        }

        if (card.getValue().equals(UnoValue.WILD) || card.getValue().equals(UnoValue.WILD_DRAW_FOUR)) {
            return true;
        }

        return false;
    }

    public void setCurrentColor(UnoColor c) {
        currentColor = c;
    }

    public UnoColor getCurrentColor() {
        return currentColor;
    }

    public UnoCard getTopCard() {
        return discardPile.getFirst();
    }

    public UnoCard removeTopCard() {
        return discardPile.pop();
    }
    
    public List<UnoCard> reset() {

        UnoCard topCard = discardPile.pop();

        List<UnoCard> toDeck = discardPile.stream().collect(Collectors.toList());

        discardPile.clear();
        discardPile.push(topCard);

        return toDeck;
    }
}
