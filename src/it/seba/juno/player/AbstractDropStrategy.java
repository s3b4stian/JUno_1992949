package it.seba.juno.player;

import java.util.List;

import it.seba.juno.card.UnoCard;
import it.seba.juno.deck.DiscardPile;

public abstract class AbstractDropStrategy implements DropStrategy {

    protected List<UnoCard> cards;
    protected DiscardPile discardPile;    
    
    public AbstractDropStrategy(DiscardPile dPile) {
        discardPile = dPile;
    }

    public abstract UnoCard dropCard();

    @Override
    public void setPlayerCards(List<UnoCard> cards) {
        this.cards = cards;
    }
    
    protected UnoCard searchColor() {
        for (UnoCard c : cards) {
            if (discardPile.cardMatchColor(c)) {
                cards.remove(c);
                return c;
            }
        }
        
        return null;
    }
    
    protected UnoCard searchValue() {
        
        for (UnoCard c : cards) {
            if (discardPile.cardMatchValue(c)) {
                cards.remove(c);
                return c;
            }
        }
        
        return null;
    }
}
