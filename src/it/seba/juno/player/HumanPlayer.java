package it.seba.juno.player;

import it.seba.juno.card.UnoCard;

public class HumanPlayer extends AbstractPlayer implements HumanDropAction {

    public HumanPlayer(String name) {
        super(name);
    }

    public UnoCard dropCard(int index) {

        if (cards.size() <= index) {
            return null;
        }

        UnoCard removed = cards.get(index);
        cards.remove(index);

        return removed;
    }

    public boolean isNpc() {
        return false;
    }
}
