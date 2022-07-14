package it.seba.juno.player;

import java.util.List;

import it.seba.juno.card.UnoCard;

public interface DropStrategy {

    UnoCard dropCard();

    void setPlayerCards(List<UnoCard> cards);
}
