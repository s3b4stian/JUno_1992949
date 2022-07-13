package it.seba.metodologie.player;

import it.seba.metodologie.juno.card.UnoCard;

//import javax.swing.Icon;

public class HumanPlayer extends AbstractPlayer implements HumanDrop {

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
