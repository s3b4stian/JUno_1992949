package it.seba.juno.model;

import java.io.Serializable;

public class PlayersProfileModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private int played = 0;
    private int won = 0;

    public PlayersProfileModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPlayed() {
        return played;
    }

    public int getWon() {
        return won;
    }

    public void incrementWon() {
        played++;
        won++;
    }

    public void incrementPlayed() {
        played++;
    }
}
