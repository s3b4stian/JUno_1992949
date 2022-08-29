package it.seba.juno.model;

import java.security.SecureRandom;

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

    boolean skipped = true;
    boolean first = true;

    UnoDeck deck;
    DiscardPile discardPile;
    UnoPlayers players;
    Player dealer;

    public GameModel(int numberOfPlayer) {

        players = new UnoPlayers();
        deck = new UnoDeckSimpleFactory().makeUnoDeck();
        discardPile = new DiscardPile();

        setNumberOfPlayers(numberOfPlayer);
    }

    public void setNumberOfPlayers(int numberOfPlayer) {

        // default 2 players, minimum to play
        players.add(new HumanPlayer(""));
        players.add(new NpcPlayer("NPC-East", getDropStrategy(), getColorStrategy()));

        // add third player
        if (numberOfPlayer == 3) {
            players.add(new NpcPlayer("NPC-North", getDropStrategy(), getColorStrategy()));
        }

        // add fourth player
        if (numberOfPlayer == 4) {
            players.add(new NpcPlayer("NPC-West", getDropStrategy(), getColorStrategy()));
        }
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
