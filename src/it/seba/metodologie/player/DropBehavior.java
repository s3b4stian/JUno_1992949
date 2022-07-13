package it.seba.metodologie.player;

import java.util.List;

import it.seba.metodologie.juno.card.UnoCard;

public interface DropBehavior {

    public UnoCard dropCard();

    public void setPlayerCards(List<UnoCard> cards);
}
