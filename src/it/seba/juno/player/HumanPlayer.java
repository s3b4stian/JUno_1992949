package it.seba.juno.player;

import it.seba.juno.card.UnoCard;

/**
 * The human player in the game, the user.
 * 
 * @author Sebastian Rapetti
 *
 */
public class HumanPlayer extends AbstractPlayer implements HumanDropAction {

    /**
     * Class Constructor.
     * 
     * @param name The name of the player.
     */
    public HumanPlayer(String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     */
    public UnoCard dropCard(int index) {

        if (cards.size() <= index) {
            return null;
        }

        UnoCard removed = cards.get(index);
        cards.remove(index);

        return removed;
    }

    /**
     * {@inheritDoc}
     */
    public UnoCard dropCard(UnoCard card) {

        cards.remove(card);

        return card;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isHuman() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isNpc() {
        return false;
    }
}
