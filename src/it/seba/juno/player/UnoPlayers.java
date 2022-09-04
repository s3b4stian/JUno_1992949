package it.seba.juno.player;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * All players in a Uno match.
 * 
 * @author Sebastian Rapetti
 *
 */
public class UnoPlayers implements Iterable<Player> {

    /**
     * Is the order of play clockwise?
     */
    private boolean clockwise = true;

    /**
     * The number of players in match.
     */
    private ArrayDeque<Player> players;

    /**
     * Class Constructor.
     */
    public UnoPlayers() {
        players = new ArrayDeque<Player>();
    }

    /**
     * Add a player for the next match, this method should be used before the start
     * of a match.
     * 
     * @param player The player to add.
     */
    public void add(Player player) {
        players.offer(player);
    }

    /**
     * Performs an action on every player.
     * 
     * <p>
     * <b>Official documentation</b><br/>
     * {@inheritDoc}
     * </p>
     */
    public void forEach(Consumer<? super Player> c) {
        players.forEach(c);
    }

    /**
     * Returns the order of play, the order change when a player drops the reverse
     * card.
     * 
     * @return True if clockwise, false if anti-clockwise.
     */
    public boolean getOrderOfPlay() {
        return clockwise;
    }

    /**
     * Returns the players in match.
     * 
     * @return List of the players in match.
     */
    public ArrayDeque<Player> getPlayers() {
        return players;
    }

    /**
     * A way to iterate over players, every time the next player, the for cycle that
     * use this iterator have to be stopped manually.
     * 
     * @return Iterator to iterate over players.
     */
    @Override
    public Iterator<Player> iterator() {
        return new Iterator<Player>() {

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Player next() {

                Player currentPlayer = players.poll();
                players.offer(currentPlayer);

                return currentPlayer;
            }
        };
    }

    public Player nextPlayer() {
        return players.peek();
    }

    /**
     * Returns the number of players in a match.
     * 
     * @return The number of players.
     */
    public int number() {
        return players.size();
    }

    /**
     * Set the dealer, iterate through players until the dealer is reached.
     * 
     * @param index The index of the player.
     */
    public void setDealer(int index) {
        int c = index + 1;

        if (index == players.size() - 1) {
            c = 0;
        }

        for (int i = 0; i < c; i++) {
            players.offer(players.poll());
        }
    }

    /**
     * Invert the direction of play, from clockwise to anti-clockwise and vice
     * versa.
     */
    public void switchDirection() {

        clockwise = clockwise ? false : true;

        ArrayDeque<Player> tmp = new ArrayDeque<Player>();

        for (int i = 1; i < players.size(); i++) {
            players.offer(players.poll());
        }

        while (!players.isEmpty()) {
            tmp.push(players.poll());
        }

        while (!tmp.isEmpty()) {
            players.offer(tmp.pop());
        }

        tmp = null;
    }
}
