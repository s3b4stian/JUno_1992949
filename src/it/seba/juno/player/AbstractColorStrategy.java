package it.seba.juno.player;

import java.util.List;

import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoColor;

public abstract class AbstractColorStrategy implements ColorStrategy {

    protected List<UnoCard> cards;
    
    public AbstractColorStrategy() {}

    @Override
    abstract public UnoColor changeColor();
    
    @Override
    public void setPlayerCards(List<UnoCard> cards) {
        this.cards = cards;
    }
}
