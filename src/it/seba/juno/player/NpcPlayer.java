package it.seba.juno.player;

import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoColor;

public class NpcPlayer extends AbstractPlayer implements NpcDropAction, NpcChangeColorAction {

    DropStrategy dropStrategy;
    ColorStrategy changeColorStrategy;

    public NpcPlayer(String name, DropStrategy dStrategy, ColorStrategy cStrategy) {
        super(name);
        dropStrategy = dStrategy;
        dropStrategy.setPlayerCards(cards);
        changeColorStrategy = cStrategy;
        changeColorStrategy.setPlayerCards(cards);
    }

    public boolean isNpc() {
        return true;
    }

    @Override
    public UnoCard dropCard() {
        return dropStrategy.dropCard();
    }

    @Override
    public UnoColor changeColor() {
        return changeColorStrategy.changeColor();
    }
}
