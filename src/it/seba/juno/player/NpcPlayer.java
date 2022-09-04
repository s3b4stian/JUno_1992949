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

    /**
     * The drop strategy for the npc.
     */
    private DropStrategy dropStrategy;

    /**
     * The color strategy for the npc.
     */
    private ColorStrategy changeColorStrategy;

    /**
     * Class Constructor.
     * 
     * @param name      The name of the player.
     * @param dStrategy The drop strategy used by npc.
     * @param cStrategy The color strategy used by npc.
     */
    public NpcPlayer(String name, DropStrategy dStrategy, ColorStrategy cStrategy) {
        super(name);
        dropStrategy = dStrategy;
        dropStrategy.setPlayerCards(cards);
        changeColorStrategy = cStrategy;
        changeColorStrategy.setPlayerCards(cards);
    }

    @Override
    public UnoColor changeColor() {
        return changeColorStrategy.changeColor();
    }

    @Override
    public UnoCard dropCard() {
        return dropStrategy.dropCard();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isHuman() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isNpc() {
        return true;
    }
}
