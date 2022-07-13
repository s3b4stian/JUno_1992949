package it.seba.metodologie.player;

import java.util.ArrayList;
import java.util.List;

import it.seba.metodologie.juno.card.UnoCard;
//import it.seba.metodologie.juno.card.UnoCardComparator;

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

    public boolean isWinner() {
        // no cards in hands
        return cards.size() == 0;
    }

    public void takeCard(UnoCard card) {
        cards.add(card);
        // cards.sort(new UnoCardComparator());
    }

    public List<UnoCard> getCards() {
        return cards;
    }

    abstract public boolean isNpc();
}
