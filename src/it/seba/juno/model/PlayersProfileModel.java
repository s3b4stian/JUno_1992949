package it.seba.juno.model;

import java.io.Serializable;

/**
 * The model for a single player profile in game.
 * 
 * @author Sebastian Rapetti
 *
 */
public class PlayersProfileModel implements Serializable {

    private static final long serialVersionUID = -4125686735413583911L;

    /**
     * The name of the player.
     */
    private String name;

    /**
     * Matches played.
     */
    private int played = 0;

    /**
     * Matches won.
     */
    private int won = 0;

    /**
     * Class Constructor.
     * 
     * @param name The name of the new player.
     */
    public PlayersProfileModel(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the player.
     * 
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns how many times the player has played the game.
     * 
     * @return The number of matches played.
     */
    public int getPlayed() {
        return played;
    }

    /**
     * Returns how many times the player won in game.
     * 
     * @return The number of matches won.
     */
    public int getWon() {
        return won;
    }

    /**
     * Increment both matches won and played counter.
     */
    public void incrementWon() {
        played++;
        won++;
    }

    /**
     * Increment only matches played counter.
     */
    public void incrementPlayed() {
        played++;
    }
}
