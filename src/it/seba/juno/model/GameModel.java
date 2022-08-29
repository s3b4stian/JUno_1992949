package it.seba.juno.model;

import java.util.Random;

import it.seba.juno.card.UnoCard;
import it.seba.juno.deck.DiscardPile;
import it.seba.juno.deck.UnoDeck;
import it.seba.juno.deck.UnoDeckSimpleFactory;
import it.seba.juno.player.ColorDropStrategy;
import it.seba.juno.player.ColorStrategy;
import it.seba.juno.player.DropStrategy;
import it.seba.juno.player.HumanPlayer;
import it.seba.juno.player.MostColorStrategy;
import it.seba.juno.player.NpcPlayer;
import it.seba.juno.player.Player;
import it.seba.juno.player.RandomColorStrategy;
import it.seba.juno.player.UnoPlayers;
import it.seba.juno.player.ValueDropStrategy;
import it.seba.juno.util.Observable;

public class GameModel extends Observable {

    private boolean skipped = true;
    private boolean first = true;

    private OptionsModel options;

    private UnoDeck deck;
    private DiscardPile discardPile;
    private UnoPlayers players;

    private int dealer;

    private Player currentPlayer;

    private int numberOfPlayer;

    public GameModel(OptionsModel options) {

        this.options = options;
        // init model using reset method
        reset();
    }

    public UnoPlayers getPlayers() {
        return players;
    }

    public void reset() {
        deck = new UnoDeckSimpleFactory().makeUnoDeck();
        discardPile = new DiscardPile();

        setNumberOfPlayers();
        randomDealer();
        next();
    }

    public boolean needReset() {
        return options.getNumberOfPlayer() != numberOfPlayer;
    }

    public void setNumberOfPlayers() {

        numberOfPlayer = options.getNumberOfPlayer();

        players = new UnoPlayers();

        // default 2 players, minimum to play
        players.add(new HumanPlayer("Human"));

        // add fourth player
        if (numberOfPlayer == 4) {
            players.add(new NpcPlayer("NPC-West", getDropStrategy(), getColorStrategy()));
            players.add(new NpcPlayer("NPC-North", getDropStrategy(), getColorStrategy()));

        }

        // add third player
        if (numberOfPlayer == 3) {
            players.add(new NpcPlayer("NPC-North", getDropStrategy(), getColorStrategy()));
        }

        players.add(new NpcPlayer("NPC-East", getDropStrategy(), getColorStrategy()));
    }

    private void randomDealer() {
        dealer = (int) (Math.random() * (numberOfPlayer - 0));
        players.setDealer(dealer);
    }

    private DropStrategy getDropStrategy() {
        // now there are only two strategies to use
        // if wish to add new strategies, change how the random is generated.
        if (Math.random() > 0.5) {
            return new ValueDropStrategy(discardPile);
        }

        return new ColorDropStrategy(discardPile);
    }

    private ColorStrategy getColorStrategy() {
        // now there are only two strategies to use
        // if wish to add new strategies, change how the random is generated.
        if (Math.random() > 0.5) {
            return new MostColorStrategy();
        }

        return new RandomColorStrategy();
    }

    public void dropFirstCardToPileAction() {
        // drop first card to discard pile
        discardPile.dropToPile(deck.dealCard());
    }

    public UnoCard discardPileTopCard() {
        return discardPile.getTopCard();
    }

    public int getDealer() {
        return dealer;
    }

    public void next() {
        currentPlayer = players.iterator().next();
    }

    public Player getCurrentPlayer() {

        return currentPlayer;
    }

    public UnoCard dealCard() {
        return deck.dealCard();
    }

    public void dealCardsToPlayers() {
        players.forEach(p -> {
            for (int i = 0; i < 7; i++) {
                p.takeCard(deck.dealCard());
            }
        });
    }
}
