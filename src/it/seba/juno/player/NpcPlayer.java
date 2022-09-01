package it.seba.juno.player;

import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoColor;

/**
 * The npc player in the game, the AI +/-.
 * 
 * @author Sebastian Rapetti
 *
 */
public class NpcPlayer extends AbstractPlayer implements NpcDropAction, NpcChangeColorAction {

    DropStrategy dropStrategy;
    ColorStrategy changeColorStrategy;

    /**
     * Class Constructor.
     * 
     * @param name      the name of the player.
     * @param dStrategy the drop strategy used by npc.
     * @param cStrategy the color strategy used by npc.
     */
    public NpcPlayer(String name, DropStrategy dStrategy, ColorStrategy cStrategy) {
        super(name);
        dropStrategy = dStrategy;
        dropStrategy.setPlayerCards(cards);
        changeColorStrategy = cStrategy;
        changeColorStrategy.setPlayerCards(cards);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isNpc() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isHuman() {
        return false;
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
