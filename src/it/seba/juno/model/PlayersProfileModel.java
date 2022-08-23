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

    private String name;

    private int played = 0;
    private int won = 0;

    /**
     * Class Constructor.
     * 
     * @param name the name of the new player.
     */
    public PlayersProfileModel(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the player.
     * 
     * @return the name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns how many times the player has played the game.
     * 
     * @return the number of matches played.
     */
    public int getPlayed() {
        return played;
    }

    /**
     * Returns how many times the player won in game.
     * 
     * @return the number of matches won.
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
