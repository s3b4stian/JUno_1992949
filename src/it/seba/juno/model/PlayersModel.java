package it.seba.juno.model;

import java.util.HashMap;
import java.util.Map;

import it.seba.juno.util.Observable;

public class PlayersModel extends Observable {

    PlayersProfileModel currentProfile;

    Map<String, PlayersProfileModel> playersProfile;

    public PlayersModel() {
        playersProfile = new HashMap<String, PlayersProfileModel>();
    }

    public PlayersProfileModel getCurrentProfile() {
        return currentProfile;
    }

    public void setCurrentProfile(String name) {
        currentProfile = playersProfile.get(name);
    }

    public void addPlayer(String name, PlayersProfileModel model) {
        playersProfile.put(name, model);
    }

    public void removePlayer(String name) {
        playersProfile.remove(name);
    }

    public Map<String, PlayersProfileModel> getPlayers() {

        return playersProfile;
    }
}
