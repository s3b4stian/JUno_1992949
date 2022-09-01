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

    // direction default clockwise
    private boolean clockwise = true;
    private ArrayDeque<Player> players;

    public ArrayDeque<Player> getPlayers() {
        return players;
    }

    /**
     * Class Constructor.
     */
    public UnoPlayers() {
        players = new ArrayDeque<Player>();
    }

    /**
     * Returns the order of play, the order change when a player drops the reverse
     * card.
     * 
     * @return true if clockwise, false if anti-clockwise.
     */
    public boolean getOrderOfPlay() {
        return clockwise;
    }

    /**
     * Add a player for the next match, this method should be used before the start
     * of a match.
     * 
     * @param player the player to add.
     */
    public void add(Player player) {
        players.offer(player);
    }

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
     * Returns the number of players in a match.
     * 
     * @return the number of players.
     */
    public int number() {
        return players.size();
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
     * Invert the direction of play, from clockwise to anti-clockwise and vice
     * versa.
     */
    public void switchDirection() {

        clockwise = clockwise ? false : true;

        ArrayDeque<Player> tmp = new ArrayDeque<Player>();

        // System.out.println(players);

        // for (int i = 1; i < players.size(); i++) {
        players.offer(players.poll());
        // }

        while (!players.isEmpty()) {
            tmp.push(players.poll());
        }

        while (!tmp.isEmpty()) {
            players.offer(tmp.pop());
        }

        // System.out.println(players);

        tmp = null;
    }

    public Player nextPlayer() {
        return players.peek();
    }

    /**
     * A way to iterate over players, every time the next player, the for cycle that
     * use this iterator have to be stopped manually.
     * 
     * @return iterator to iterate over players.
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
}
