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
        System.out.println(index);

        for (int i = 0; i < index; i++) {
            // Player p = players.poll();
            // System.out.println(index + " " + p.getName());
            // players.offer(p);
        }

        // players.push(players.pop());
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
