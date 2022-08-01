package it.seba.juno.player;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.function.Consumer;

public class UnoPlayers implements Iterable<Player> {

    // direction default clockwise
    private boolean clockwise = true;
    private ArrayDeque<Player> players;

    public UnoPlayers() {
        players = new ArrayDeque<Player>();
    }

    public boolean getOrderOfPlay() {
        return clockwise;
    }

    public void add(Player player) {
        players.offer(player);
    }

    public int number() {
        return players.size();
    }

    public void forEach(Consumer<? super Player> c) {
        players.forEach(c);
    }

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

    @Override
    public Iterator<Player> iterator() {
        return new Iterator<Player>() {

            @Override
            public boolean hasNext() {

                /*for (Player p : players) {
                    if (p.isWinner()) {
                        return false;
                    }
                }*/

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
