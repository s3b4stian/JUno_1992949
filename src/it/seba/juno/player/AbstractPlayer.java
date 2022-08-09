package it.seba.juno.player;

import java.util.ArrayList;
import java.util.List;

import it.seba.juno.card.UnoCard;

public abstract class AbstractPlayer implements Player {

    private String name;
    // cards in hand
    protected ArrayList<UnoCard> cards;

    public AbstractPlayer(String name) {
        this.name = name;
        cards = new ArrayList<UnoCard>();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isWinner() {
        // no cards in hands
        return cards.size() == 0;
    }

    @Override
    public void takeCard(UnoCard card) {
        cards.add(card);
    }

    @Override
    public int getCardsNumber() {
        return cards.size();
    }

    @Override
    public List<UnoCard> getCards() {
        return cards;
    }

    abstract public boolean isNpc();
}
