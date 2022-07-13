package it.seba.juno.player;

import java.util.List;

import it.seba.juno.card.UnoCard;

public interface Player {

    public String getName();

    public boolean isWinner();

    public void takeCard(UnoCard card);

    public List<UnoCard> getCards();

    public boolean isNpc();
}
