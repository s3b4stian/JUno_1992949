package it.seba.juno.model;

import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoColor;
import it.seba.juno.card.UnoValue;
import it.seba.juno.deck.DiscardPile;
import it.seba.juno.deck.UnoDeck;
import it.seba.juno.deck.UnoDeckSimpleFactory;
import it.seba.juno.player.ColorDropStrategy;
import it.seba.juno.player.ColorStrategy;
import it.seba.juno.player.DropStrategy;
import it.seba.juno.player.HumanDropAction;
import it.seba.juno.player.HumanPlayer;
import it.seba.juno.player.MostColorStrategy;
import it.seba.juno.player.NpcChangeColorAction;
import it.seba.juno.player.NpcDropAction;
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
    private Player nextPlayer;

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

        currentPlayer = players.iterator().next();
        nextPlayer = players.nextPlayer();

        System.out.println(currentPlayer.getName() + " first move");

    }

    public boolean needReset() {
        return options.getNumberOfPlayer() != numberOfPlayer;
    }

    public void setNumberOfPlayers() {

        numberOfPlayer = options.getNumberOfPlayer();

        players = new UnoPlayers();

        // default 2 players, minimum to play
        players.add(new HumanPlayer("Human"));
        players.add(new NpcPlayer("NPC-West", getDropStrategy(), getColorStrategy()));

        // add third player
        if (numberOfPlayer == 3) {
            players.add(new NpcPlayer("NPC-North", getDropStrategy(), getColorStrategy()));
        }
        // add fourth player
        if (numberOfPlayer == 4) {
            players.add(new NpcPlayer("NPC-North", getDropStrategy(), getColorStrategy()));
            players.add(new NpcPlayer("NPC-East", getDropStrategy(), getColorStrategy()));
        }
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

    public boolean currentTopCardDrawTwo() {
        if (skipped && discardPile.getTopCard().getValue().equals(UnoValue.DRAW_TWO)) {
            currentPlayer.takeCard(deck.dealCard());
            currentPlayer.takeCard(deck.dealCard());

            System.out.println(currentPlayer.getName() + " take 2 cards and skip turn");

            // skip turn
            skipped = false;
            return true;
        }

        return false;
    }

    public boolean currentTopCardSkip() {
        if (skipped && discardPile.getTopCard().getValue().equals(UnoValue.SKIP)) {

            System.out.println(currentPlayer.getName() + " skip turn");

            // skip turn
            skipped = false;
            return true;
        }

        return false;
    }

    public boolean currentTopCardWildDrawFour() {

        // System.out.println("skipped:" + skipped);

        if (skipped && discardPile.getTopCard().getValue().equals(UnoValue.WILD_DRAW_FOUR)) {
            currentPlayer.takeCard(deck.dealCard());
            currentPlayer.takeCard(deck.dealCard());
            currentPlayer.takeCard(deck.dealCard());
            currentPlayer.takeCard(deck.dealCard());

            System.out.println(currentPlayer.getName() + " take 4 cards and skip turn");

            // skip turn
            skipped = false;
            return true;
        }

        return false;
    }

    public void currentTopCardReverse() {

        /*
         * players.forEach(p -> { System.out.println("order before: " + p.getName());
         * });
         */

        if (discardPile.getTopCard().getValue().equals(UnoValue.REVERSE)) {
            // players.switchDirection();
        }

        /*
         * players.forEach(p -> { System.out.println("order after: " + p.getName()); });
         */
    }

    public void currentTopCardWild() {
        // if first card in discard is WILD at the first round, player choose the color
        if (first && discardPile.getTopCard().getValue().equals(UnoValue.WILD)) {

            UnoColor curentColor = ((NpcChangeColorAction) currentPlayer).changeColor();

            discardPile.setCurrentColor(curentColor);

            System.out.println("First card WILD " + currentPlayer.getName() + " change color to: " + curentColor);
        }
    }

    public void dropCardHuman(UnoCard card) {
        System.out.println(currentPlayer);
        System.out.println(currentPlayer.getCardsNumber());

        if (currentPlayer instanceof HumanPlayer) {
            System.out.println(card);
            discardPile.dropToPile(((HumanDropAction) currentPlayer).dropCard(card));
        }

        System.out.println(currentPlayer.getCardsNumber());
    }

    public void dropCardNpc() {
        // discardPile.
        UnoCard dropped = ((NpcDropAction) currentPlayer).dropCard();

        if (dropped == null) {
            System.out.println(currentPlayer.getName() + " take one card ");

            currentPlayer.takeCard(deck.dealCard());

            dropped = ((NpcDropAction) currentPlayer).dropCard();

            if (dropped instanceof UnoCard) {
                discardPile.dropToPile(dropped);

                System.out.println(currentPlayer.getName() + " dropped " + dropped);
            }

        } else {

            discardPile.dropToPile(dropped);

            System.out.println(currentPlayer.getName() + " dropped " + dropped);

        }

        if (dropped instanceof UnoCard) {
            if (discardPile.getTopCard().getValue().equals(UnoValue.WILD)
                    || discardPile.getTopCard().getValue().equals(UnoValue.WILD_DRAW_FOUR)) {

                UnoColor curentColor = ((NpcChangeColorAction) currentPlayer).changeColor();

                discardPile.setCurrentColor(curentColor);

                System.out.println(currentPlayer.getName() + " change color to: " + curentColor);
            }
        }
    }

    public void dropFirstCardToPileAction() {
        // drop first card to discard pile
        discardPile.dropToPile(deck.dealCard());

        // if first cards is wild draw four
        // return card to the deck, restart discard pile with another card
        if (discardPile.getTopCard().getValue().equals(UnoValue.WILD_DRAW_FOUR)) {
            deck.refill(discardPile.removeTopCard());
            discardPile.dropToPile(deck.dealCard());
        }
    }

    public boolean droppable(UnoCard card) {

        if (discardPile.cardMatch(card)) {
            return true;
        }

        return false;

    }

    public UnoColor discardPileColor() {
        return discardPile.getCurrentColor();
    }

    public UnoCard discardPileTopCard() {
        return discardPile.getTopCard();
    }

    public boolean getOrderOfPlay() {
        return players.getOrderOfPlay();
    }

    public int getDealer() {
        return dealer;
    }

    public void next() {
        currentPlayer = players.iterator().next();
        // currentPlayer = players.iterator().next();
        nextPlayer = players.nextPlayer();

        first = false;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public void setSkippedTrue() {
        skipped = true;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public UnoCard dealCard() {
        return deck.dealCard();
    }

    public boolean isOneCard() {
        if (currentPlayer.getCardsNumber() == 1) {
            System.out.println(currentPlayer.getName() + " have one card, say UNO");
            return true;
        }
        return false;

    }

    public boolean isWinner() {
        if (currentPlayer.isWinner()) {
            System.out.println(currentPlayer.getName() + " is the Winner");
            return true;
        }
        return false;
    }

    public void dealCardsToPlayers() {
        players.forEach(p -> {
            for (int i = 0; i < 7; i++) {
                p.takeCard(deck.dealCard());
            }
        });
    }
}
