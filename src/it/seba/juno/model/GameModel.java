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

    private boolean drawn = false;
    private boolean dropped = false;
    private boolean changeColor = false;
    
    private int skipped = 2;
    
    public int getSkipped() {
        return skipped;
    }

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
        
        first = true;
        skipped = 2;
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

    public boolean nextPlayerCannotDrop() {
        
        for (UnoCard card : nextPlayer.getCards()) {
            if (discardPile.cardMatch(card)) {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean currentPlayerCannotDrop() {
        
        for (UnoCard card : currentPlayer.getCards()) {
            if (discardPile.cardMatch(card)) {
                return false;
            }
        }
        
        return true;
    }
    
    public void drawOneCard() {
        currentPlayer.takeCard(deck.dealCard());
    }
    
    public boolean currentTopCardDrawTwo() {
        if (skipped > 1 && discardPile.getTopCard().getValue().equals(UnoValue.DRAW_TWO)) {
            drawOneCard();
            drawOneCard();

            // skip turn
            skipped = 0;
            return true;
        }

        return false;
    }

    public boolean currentTopCardSkip() {
        if (skipped > 1 && discardPile.getTopCard().getValue().equals(UnoValue.SKIP)) {

            // skip turn
            skipped = 0;
            return true;
        }

        return false;
    }
    
    
    
    

    public boolean currentTopCardWildDrawFour() {

        if (skipped > 1 && discardPile.getTopCard().getValue().equals(UnoValue.WILD_DRAW_FOUR)) {
            drawOneCard();
            drawOneCard();
            drawOneCard();
            drawOneCard();

            // skip turn
            skipped = 0;
            return true;
        }

        return false;
    }

    public void currentTopCardReverse() {
        if (discardPile.getTopCard().getValue().equals(UnoValue.REVERSE)) {
            // players.switchDirection();
        }
    }

    public void currentTopCardWild() {
        // if first card in discard is WILD at the first round, player choose the color
        if (first && discardPile.getTopCard().getValue().equals(UnoValue.WILD)) {

            if (currentPlayer instanceof NpcPlayer) {

                UnoColor curentColor = ((NpcChangeColorAction) currentPlayer).changeColor();

                discardPile.setCurrentColor(curentColor);
            }
        }
    }

    public void dropCardHuman(UnoCard card) {
        skipped++;

        if (currentPlayer instanceof HumanPlayer) {
            discardPile.dropToPile(((HumanDropAction) currentPlayer).dropCard(card));
        }
    }

    public void dropCardNpc() {
        skipped++;
        
        // discardPile.
        UnoCard dropped = ((NpcDropAction) currentPlayer).dropCard();

        if (dropped == null) {
            
            this.drawn = true;
            currentPlayer.takeCard(deck.dealCard());

            // try to drop the drawn card
            dropped = ((NpcDropAction) currentPlayer).dropCard();

            if (dropped instanceof UnoCard) {
                discardPile.dropToPile(dropped);
            }

        } else {

            discardPile.dropToPile(dropped);
        }

        if (dropped instanceof UnoCard) {
            
            this.dropped = true;
            
            if (discardPile.getTopCard().getValue().equals(UnoValue.WILD)
                    || discardPile.getTopCard().getValue().equals(UnoValue.WILD_DRAW_FOUR)) {

                this.changeColor = true;
                discardPile.setCurrentColor(((NpcChangeColorAction) currentPlayer).changeColor());
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
        nextPlayer = players.nextPlayer();

        first = false;
        drawn = false;
        dropped = false;
        changeColor = false;
        
        skipped++;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public boolean isFirst() {
        return first;
    }
    
    /*public void resetStatus() {
        drawn = false;
        dropped = false;
        changeColor = false;
    }*/
    
    public void resetSkipped() {
        //skipped = 0;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public UnoCard dealCard() {
        return deck.dealCard();
    }

    public boolean isOneCard() {
        if (currentPlayer.getCardsNumber() == 1) {
            return true;
        }
        return false;

    }

    public boolean isWinner() {
        if (currentPlayer.isWinner()) {
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

    public boolean isDropped() {
        return dropped;
    }

    public boolean isChangeColor() {
        return changeColor;
    }

    public boolean isDrawn() {
        return drawn;
    }
}
