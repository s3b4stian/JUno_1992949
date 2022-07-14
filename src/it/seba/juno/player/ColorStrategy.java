package it.seba.juno.player;

import java.util.List;

import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoColor;

public interface ColorStrategy {

    UnoColor changeColor();

    void setPlayerCards(List<UnoCard> cards);
}
