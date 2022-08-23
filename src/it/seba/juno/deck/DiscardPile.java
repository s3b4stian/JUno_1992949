package it.seba.juno.deck;

import java.util.ArrayDeque;
import java.util.List;
import java.util.stream.Collectors;

import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoColor;
import it.seba.juno.card.UnoValue;

/**
 * Discard pile for the Uno game, a place where to put played cards.
 *
 * @author Sebastian Rapetti
 *
 */
public class DiscardPile {

    private ArrayDeque<UnoCard> discardPile;
    private UnoCard topCard;
    private UnoColor currentColor;

    /**
     * Class Constructor.
     */
    public DiscardPile() {
        discardPile = new ArrayDeque<UnoCard>();
    }

    /**
     * Permit to drop a card on top of the DiscardPile.
     * 
     * @param card card to drop on top of the pile.
     */
    public void dropToPile(UnoCard card) {

        if (card.hasColor()) {
            currentColor = card.getColor();
        }

        discardPile.push(card);
        topCard = card;
    }

    /**
     * Check if a card can be dropped to the discard pile, check for both value and
     * color.
     * 
     * @param card the card to check.
     * 
     * @return true if card can be dropped, false otherwise.
     */
    public boolean cardMatch(UnoCard card) {

        // System.out.println("colors: " + topCard.getColor()+ " " + currentColor + " "
        // + card.getColor());

        // same color and value
        // same object
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

        // same value
        if (topCard.getValue().equals(card.getValue())) {
            return true;
        }

        // card not match
        return false;
    }

    /**
     * Check if a card can be dropped to the discard pile, check for color.
     * 
     * @param card the card to check.
     * 
     * @return true if card can be dropped, false otherwise.
     */
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

    /**
     * Check if a card can be dropped to the discard pile, check for value.
     * 
     * @param card the card to check.
     * 
     * @return true if card can be dropped, false otherwise.
     */
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

    /**
     * Set the current color in drop pile, used to keeping track of the current
     * color to check before card drop.
     * 
     * @param color the new current color.
     */
    public void setCurrentColor(UnoColor color) {
        currentColor = color;
    }

    /**
     * Return the current color of the discard pile.
     * 
     * @return the current color.
     */
    public UnoColor getCurrentColor() {
        return currentColor;
    }

    /**
     * Return the top card of the discard pile but dosn't remove it from the pile.
     * 
     * @return the current top card.
     */
    public UnoCard getTopCard() {
        return discardPile.getFirst();
    }

    /**
     * Return the top card of the discard pile and remove it from the pile.
     * 
     * @return the current top card.
     */
    public UnoCard removeTopCard() {
        return discardPile.pop();
    }

    /**
     * Reset the discard pile, all cards except the top card are removed, use this
     * to refill the deck when it is empty.
     * 
     * @return a list of all cards in discard pile.
     */
    public List<UnoCard> reset() {

        UnoCard topCard = discardPile.pop();

        List<UnoCard> toDeck = discardPile.stream().collect(Collectors.toList());

        discardPile.clear();
        discardPile.push(topCard);

        return toDeck;
    }
}
