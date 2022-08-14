package it.seba.juno.model;

import java.util.HashMap;
import java.util.Map;

import it.seba.juno.manger.SerializationManager;
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
    
    public void save() {
        SerializationManager sm = SerializationManager.getInstance();
        //Map<String, PlayersProfileModel> players = playersModel.getPlayers();

        // save all profiles
        for (Map.Entry<String, PlayersProfileModel> entry : playersProfile.entrySet()) {
            sm.savePlayers(entry.getValue());
        }
    }
}
