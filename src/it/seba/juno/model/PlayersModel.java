package it.seba.juno.model;

import java.util.HashMap;
import java.util.Map;

import it.seba.juno.util.Observable;

public class PlayersModel extends Observable  {

    PlayerProfileModel currentProfile;

    Map<String, PlayerProfileModel> models;

    public PlayersModel() {
        models = new HashMap<String, PlayerProfileModel>();
    }

    public PlayerProfileModel getCurrentProfile() {
        return currentProfile;
    }

    public void setCurrentProfile(String name) {
        currentProfile = models.get(name);
    }

    public void addPlayer(String name, PlayerProfileModel model) {
        models.put(name, model);
    }

    public void removePlayer(String name) {
        models.remove(name);
    }
}
