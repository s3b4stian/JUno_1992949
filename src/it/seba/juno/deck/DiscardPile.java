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

    /**
     * The internal store for the discard pile cards.
     */
    private ArrayDeque<UnoCard> discardPile;

    /**
     * The top card of the discard pile.
     */
    private UnoCard topCard;

    /**
     * The current color of the discard pile.
     */
    private UnoColor currentColor;

    /**
     * Class Constructor.
     */
    public DiscardPile() {
        discardPile = new ArrayDeque<UnoCard>();
    }

    /**
     * Check if a card can be dropped to the discard pile, check for both value and
     * color.
     * 
     * @param card The card to check.
     * 
     * @return True if card can be dropped, false otherwise.
     */
    public boolean cardMatch(UnoCard card) {
        return cardMatchColor(card) || cardMatchValue(card);
    }

    /**
     * Check if a card can be dropped to the discard pile, check for color.
     * 
     * @param card The card to check.
     * 
     * @return True if card can be dropped, false otherwise.
     */
    public boolean cardMatchColor(UnoCard card) {

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
     * @param card The card to check.
     * 
     * @return True if card can be dropped, false otherwise.
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
     * Permit to drop a card on top of the DiscardPile.
     * 
     * @param card Card to drop on top of the pile.
     */
    public void dropToPile(UnoCard card) {

        if (card.hasColor()) {
            currentColor = card.getColor();
        }

        discardPile.push(card);
        topCard = card;
    }

    /**
     * Return the current color of the discard pile.
     * 
     * @return The current color.
     */
    public UnoColor getCurrentColor() {
        return currentColor;
    }

    /**
     * Return the top card of the discard pile but dosn't remove it from the pile.
     * 
     * @return The current top card.
     */
    public UnoCard getTopCard() {
        return discardPile.getFirst();
    }

    /**
     * Return the top card of the discard pile and remove it from the pile.
     * 
     * @return The current top card.
     */
    public UnoCard removeTopCard() {
        return discardPile.pop();
    }

    /**
     * Reset the discard pile, all cards except the top card are removed, use this
     * to refill the deck when it is empty.
     * 
     * @return A list of all cards in discard pile.
     */
    public List<UnoCard> reset() {

        UnoCard topCard = discardPile.pop();

        List<UnoCard> toDeck = discardPile.stream().collect(Collectors.toList());

        discardPile.clear();
        discardPile.push(topCard);

        return toDeck;
    }

    /**
     * Set the current color in drop pile, used to keeping track of the current
     * color to check before card drop.
     * 
     * @param color The new current color.
     */
    public void setCurrentColor(UnoColor color) {
        currentColor = color;
    }
}
