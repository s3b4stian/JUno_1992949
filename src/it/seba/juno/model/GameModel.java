package it.seba.juno.model;

import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoColor;
import it.seba.juno.card.UnoValue;
import it.seba.juno.deck.DiscardPile;
import it.seba.juno.deck.UnoDeck;
import it.seba.juno.deck.UnoDeckSimpleFactory;
import it.seba.juno.observer.Observable;
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

/**
 * The model for the game, manage whole match.
 * 
 * @author Sebastian Rapetti
 *
 */
public class GameModel extends Observable {

    /**
     * Does current player drawn a card?
     */
    private boolean drawn = false;

    /**
     * Does current player dropped a card?
     */
    private boolean dropped = false;

    /**
     * Does current player changed discard pile color?
     */
    private boolean changeColor = false;

    /**
     * Is the first turn of the match?
     */
    private boolean firstTurn = true;

    /**
     * The options model.
     */
    private OptionsModel optionsModel;

    /**
     * The Uno deck.
     */
    private UnoDeck deck;

    /**
     * The discard pile
     */
    private DiscardPile discardPile;

    /**
     * The list of players for the current match.
     */
    private UnoPlayers players;

    /**
     * The last special card dropped to discard pile, used to force actions required
     * to the special card only for one player.
     */
    private UnoCard currentSpecialCard;

    /**
     * The match dealer.
     */
    private int dealer;

    /**
     * The current player.
     */
    private Player currentPlayer;

    /**
     * The next player.
     */
    private Player nextPlayer;

    /**
     * The number of players in match.
     */
    private int numberOfPlayer;

    /**
     * Class Constructor.
     * 
     * @param optionsModel The option model, contains the data about options.
     */
    public GameModel(OptionsModel optionsModel) {

        this.currentSpecialCard = new UnoCard(UnoValue.WILD);
        this.optionsModel = optionsModel;

        // init model using reset method
        reset();
    }

    /**
     * Checks if the card in discard pile when the game starts is a wild card. Used
     * to force the current player to chose the color for the discard pile.
     * 
     * @return True if card is a wild card, false otherwise.
     */
    public boolean currentFirstTopCardWild() {
        // if first card in discard is WILD at the first round, player choose the color
        if (firstTurn && discardPile.getTopCard().getValue().equals(UnoValue.WILD)) {

            if (currentPlayer instanceof NpcPlayer) {

                UnoColor curentColor = ((NpcChangeColorAction) currentPlayer).changeColor();

                discardPile.setCurrentColor(curentColor);

                return true;
            }

            return true;
        }

        return false;
    }

    /**
     * Checks if the next player cannot drop a card because he has no compatible
     * cards. Used by the both controller and view to enable the deck button for the
     * human player.
     * 
     * @return True if cannot drop, false otherwise.
     */
    public boolean currentPlayerCannotDrop() {

        for (UnoCard card : currentPlayer.getCards()) {
            if (discardPile.cardMatch(card)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the current card in discard pile is a draw two card. Used to force
     * the current player to draw two cards.
     * 
     * @return True if card is a draw two card, false otherwise.
     */
    public boolean currentTopCardDrawTwo() {

        UnoCard topCard = discardPile.getTopCard();

        if (topCard.getValue().equals(UnoValue.DRAW_TWO) && topCard.hashCode() != currentSpecialCard.hashCode()) {
            drawOneCard();
            drawOneCard();

            this.currentSpecialCard = topCard;
            return true;
        }

        return false;
    }

    /**
     * Checks if the current card in discard pile is a reverse card. Used to change
     * order of play.
     * 
     * @return True if card is a reverse card, false otherwise.
     */
    public boolean currentTopCardReverse() {

        UnoCard topCard = discardPile.getTopCard();

        if (discardPile.getTopCard().getValue().equals(UnoValue.REVERSE)
                && topCard.hashCode() != currentSpecialCard.hashCode()) {

            this.currentSpecialCard = topCard;

            players.switchDirection();

            nextPlayer = players.nextPlayer();
            return true;
        }
        return false;
    }

    /**
     * Checks if the current card in discard pile is a skip card. Used to force the
     * current player to skip the turn.
     * 
     * @return True if card is a skip card, false otherwise.
     */
    public boolean currentTopCardSkip() {

        UnoCard topCard = discardPile.getTopCard();

        if (topCard.getValue().equals(UnoValue.SKIP) && topCard.hashCode() != currentSpecialCard.hashCode()) {
            this.currentSpecialCard = topCard;
            return true;
        }

        return false;
    }

    /**
     * Checks if the current card in discard pile is a draw four card. Used to force
     * the current player to draw two cards.
     * 
     * @return True if card is a draw four card, false otherwise.
     */
    public boolean currentTopCardWildDrawFour() {

        UnoCard topCard = discardPile.getTopCard();

        if (topCard.getValue().equals(UnoValue.WILD_DRAW_FOUR) && topCard.hashCode() != currentSpecialCard.hashCode()) {
            drawOneCard();
            drawOneCard();
            drawOneCard();
            drawOneCard();

            this.currentSpecialCard = topCard;

            // skip turn
            // skipped = false;
            return true;
        }

        return false;
    }

    /**
     * Deal a card from the deck.
     * 
     * @return The card as UnoCard object.
     */
    public UnoCard dealCard() {
        return deck.dealCard();
    }

    /**
     * Deal cards to players, used when the game starts.
     */
    public void dealCardsToPlayers() {
        players.forEach(p -> {
            for (int i = 0; i < 7; i++) {
                p.takeCard(deck.dealCard());
            }
        });
    }

    /**
     * Returns the discard pile color.
     * 
     * @return The color as UnoColor object.
     */
    public UnoColor discardPileColor() {
        return discardPile.getCurrentColor();
    }

    /**
     * Return the discard pile top card.
     * 
     * @return The card as UnoCard object.
     */
    public UnoCard discardPileTopCard() {
        return discardPile.getTopCard();
    }

    /**
     * Move a card from the deck to the current player.
     */
    public void drawOneCard() {
        
        // if cards in deck are low, refill the deck
        if (deck.getCardsInDeck() < 5) {
            deck.refill(discardPile.reset());
        }

        currentPlayer.takeCard(deck.dealCard());
    }

    /**
     * Drop card action performed by Human player.
     * 
     * @param card The card dropped by the human player.
     */
    public void dropCardHuman(UnoCard card) {

        if (currentPlayer instanceof HumanPlayer) {
            discardPile.dropToPile(((HumanDropAction) currentPlayer).dropCard(card));
        }
    }

    /**
     * Drop card action performed by npc player, the card is chose using strategies.
     */
    public void dropCardNpc() {

        // discardPile.
        UnoCard dropped = ((NpcDropAction) currentPlayer).dropCard();

        // if player has no card to drop
        if (dropped == null) {

            // draw a card
            this.drawn = true;
            currentPlayer.takeCard(dealCard());

            // try to drop the drawn card
            dropped = ((NpcDropAction) currentPlayer).dropCard();

            // add dropped card to discard pile if valid card
            if (dropped instanceof UnoCard) {
                discardPile.dropToPile(dropped);
            }

        } else {
            discardPile.dropToPile(dropped);
        }

        // change the color of the discard pile if dropped card is a wild card
        if (dropped instanceof UnoCard) {

            this.dropped = true;

            if (discardPile.getTopCard().getValue().equals(UnoValue.WILD)
                    || discardPile.getTopCard().getValue().equals(UnoValue.WILD_DRAW_FOUR)) {

                this.changeColor = true;
                discardPile.setCurrentColor(((NpcChangeColorAction) currentPlayer).changeColor());
            }
        }
    }

    /**
     * The action of drop to discard pile the first card after the dealer dealt
     * cards to players. If the first card is a wild draw four, the card returns to
     * the deck.
     */
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

    /**
     * Checks if a card is droppable to discard pile.
     * 
     * @param card The card to be checked.
     * 
     * @return True if card is compatible with discard pile, false otherwise.
     */
    public boolean droppable(UnoCard card) {

        if (discardPile.cardMatch(card)) {
            return true;
        }

        return false;

    }

    /**
     * Returns a random color strategy for a non human player.
     * 
     * @return The strategy object.
     */
    private ColorStrategy getColorStrategy() {
        // now there are only two strategies to use
        // if wish to add new strategies, change how the random is generated.
        if (Math.random() > 0.5) {
            return new MostColorStrategy();
        }

        return new RandomColorStrategy();
    }

    /**
     * Returns the current player of the match.
     * 
     * @return current Player as Player object.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns the current dealer.
     * 
     * @return The dealer as player index.
     */
    public int getDealer() {
        return dealer;
    }

    /**
     * Returns a random drop strategy for a non human player.
     * 
     * @return The strategy object.
     */
    private DropStrategy getDropStrategy() {
        // now there are only two strategies to use
        // if wish to add new strategies, change how the random is generated.
        if (Math.random() > 0.5) {
            return new ValueDropStrategy(discardPile);
        }

        return new ColorDropStrategy(discardPile);
    }

    /**
     * Returns the next player of the match.
     * 
     * @return Next player as Player object.
     */
    public Player getNextPlayer() {
        return nextPlayer;
    }

    /**
     * Returns the order of play for the match.
     * 
     * @return True if clockwise, false anticlockwise.
     */
    public boolean getOrderOfPlay() {
        return players.getOrderOfPlay();
    }

    /**
     * Returns the players of the match.
     * 
     * @return Players as UnoPlayers object
     */
    public UnoPlayers getPlayers() {
        return players;
    }

    /**
     * Checks if the human player dropped a card that permit to change the discard
     * pile color.
     * 
     * @return True if yes, false otherwise.
     */
    public boolean humanDroppedChangeColorCard() {
        if (discardPile.getTopCard().getValue().equals(UnoValue.WILD)
                || discardPile.getTopCard().getValue().equals(UnoValue.WILD_DRAW_FOUR)) {

            return true;
        }
        return false;
    }

    /**
     * Check if there is a change in discard pile color.
     * 
     * @return True if yes, false otherwise.
     */
    public boolean isChangeColor() {
        return changeColor;
    }

    /**
     * Check if the current user drawn a card from the deck.
     * 
     * @return True if yes, false otherwise.
     */
    public boolean isDrawn() {
        return drawn;
    }

    /**
     * Check if the current player dropped a card.
     * 
     * @return True if yes, false otherwise.
     */
    public boolean isDropped() {
        return dropped;
    }

    /**
     * Returns if the match turn is the first of the match, the turn after card
     * dealing.
     * 
     * @return True if yes, false otherwise.
     */
    public boolean isFirst() {
        return firstTurn;
    }

    /**
     * Check if the player has only one card.
     * 
     * @return True if the player has only one card, false otherwise.
     */
    public boolean isOneCard() {
        if (currentPlayer.getCardsNumber() == 1) {
            return true;
        }
        return false;

    }

    /**
     * Check if the player is the winner.
     * 
     * @return True if the player is the winner, false otherwise.
     */
    public boolean isWinner() {
        if (currentPlayer.isWinner()) {
            return true;
        }
        return false;
    }

    /**
     * Check if the number of players in game options changed.
     * 
     * @return True if yes, false otherwise.
     */
    public boolean needReset() {
        return optionsModel.getNumberOfPlayer() != numberOfPlayer;
    }

    /**
     * Set the model to the next player that have to move.
     */
    public void next() {
        currentPlayer = players.iterator().next();
        nextPlayer = players.nextPlayer();

        firstTurn = false;
        drawn = false;
        dropped = false;
        changeColor = false;
    }

    /**
     * Checks if the next player cannot drop a card because he has no compatible
     * cards. Used by the view to enable the deck button for the human player.
     * 
     * @return True if cannot drop, false otherwise.
     */
    public boolean nextPlayerCannotDrop() {

        for (UnoCard card : nextPlayer.getCards()) {
            if (discardPile.cardMatch(card)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Generate a random dealer, the dealer, deal card and the player at the left of
     * the dealer is the first player of the game.
     */
    private void randomDealer() {
        dealer = (int) (Math.random() * (numberOfPlayer - 0));
        players.setDealer(dealer);
    }

    /**
     * Initialize or reinitialize the model for a new game.
     */
    public void reset() {
        deck = UnoDeckSimpleFactory.makeUnoDeck();
        discardPile = new DiscardPile();

        setNumberOfPlayers();
        randomDealer();

        currentPlayer = players.iterator().next();
        nextPlayer = players.nextPlayer();

        firstTurn = true;
    }

    /**
     * Change the discard pile color.
     * 
     * @param color The new color of the discard pile.
     */
    public void setDiscardPileColor(UnoColor color) {
        discardPile.setCurrentColor(color);
    }

    /**
     * Generate players for the match, this method does not have parameters because
     * the number of players is decided at game options level.
     */
    public void setNumberOfPlayers() {

        numberOfPlayer = optionsModel.getNumberOfPlayer();

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
}
