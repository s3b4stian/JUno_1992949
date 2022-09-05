package it.seba.juno.player;

import java.util.ArrayList;
import java.util.List;

import it.seba.juno.card.UnoCard;

/**
 * The abstract implementation of the player, contains methods valid both for
 * npc and human players.
 * 
 * @author Sebastian
 *
 */
public abstract class AbstractPlayer implements Player {

    /**
     * Player name.
     */
    private String name;

    /**
     * Player cards.
     */
    protected ArrayList<UnoCard> cards;

    /**
     * Class Constructor.
     * 
     * @param name The name of the player.
     */
    public AbstractPlayer(String name) {
        this.name = name;
        cards = new ArrayList<UnoCard>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UnoCard> getCards() {
        return cards;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCardsNumber() {
        return cards.size();
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    abstract public boolean isHuman();
    
    /**
     * {@inheritDoc}
     */
    abstract public boolean isNpc();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWinner() {
        // no cards in hands
        return cards.size() == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean mustSayUno() {
        // one card in hands
        return cards.size() == 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void takeCard(UnoCard card) {
        cards.add(card);
    }
}
