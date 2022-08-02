package it.seba.juno.player;

import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoValue;
import it.seba.juno.deck.DiscardPile;

public class ValueDropStrategy extends AbstractDropStrategy {

    public ValueDropStrategy(DiscardPile dPile) {
        super(dPile);
    }

    @Override
    public UnoCard dropCard() {

        UnoCard c;
        
        if ((c = this.searchValue()) != null) {
            
            // drop is forbidden if there is a card that has the color of discard pile
            if (c.getValue().equals(UnoValue.WILD_DRAW_FOUR)) { 
                // 
                if ((c = this.searchColor()) != null) {
                    return c;
                }
            }
            
            return c;
        }
        
        if ((c = this.searchColor()) != null) {
            return c;
        }
        
        /*
        
        for (UnoCard c : cards) {
            
            if (c.getValue().equals(UnoValue.WILD_DRAW_FOUR)) {
                // horror but necessary
                for (UnoCard cc : cards) {
                    if (discardPile.cardMatchColor(cc)) {
                        cards.remove(cc);
                        return cc;
                    }
                }
            }

            if (discardPile.cardMatchValue(c)) {
                cards.remove(c);
                return c;
            }
        }

        for (UnoCard c : cards) {
            if (discardPile.cardMatchColor(c)) {
                cards.remove(c);
                return c;
            }
        }*/

        return null;
    }
    
    /*
    private UnoCard tryColor(UnoCard card) {
        return null;
    }
    
    private UnoCard tryValue(UnoCard card) {
        return null;
    }*/
}
