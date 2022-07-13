package it.seba.juno.player;

import java.util.List;

import it.seba.juno.card.UnoCard;

public interface DropBehavior {

    public UnoCard dropCard();

    public void setPlayerCards(List<UnoCard> cards);
}
