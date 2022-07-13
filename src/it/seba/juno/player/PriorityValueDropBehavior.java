package it.seba.juno.player;

import java.util.List;

import it.seba.juno.card.UnoCard;
import it.seba.juno.deck.DiscardPile;

public class PriorityValueDropBehavior implements DropBehavior {

    private List<UnoCard> cards;
    private DiscardPile discardPile;

    public PriorityValueDropBehavior(DiscardPile discardPile) {
        this.discardPile = discardPile;
    }

    @Override
    public UnoCard dropCard() {

        for (UnoCard c : cards) {
            if (discardPile.cardMatchValue(c)) {
                return c;
            }
        }

        for (UnoCard c : cards) {
            if (discardPile.cardMatchColor(c)) {
                return c;
            }
        }

        return null;
    }

    @Override
    public void setPlayerCards(List<UnoCard> cards) {
        this.cards = cards;
    }
}
