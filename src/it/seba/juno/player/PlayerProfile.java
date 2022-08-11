package it.seba.juno.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlayerProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private int played = 0;
    private int won = 0;
    
    public PlayerProfile(String name) {
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
