package it.seba.juno.model;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import it.seba.juno.manger.SerializationManager;
import it.seba.juno.util.Observable;

/**
 * The model for the players of the game, the instance of this class isn't
 * directly serialized to disk like options model, instead are serialized to
 * disk all players profile.
 * 
 * @author Sebastian Rapetti
 *
 */
public class PlayersModel extends Observable {

    PlayersProfileModel currentProfile;

    Map<String, PlayersProfileModel> playersProfile;

    /**
     * Class Constructor.
     */
    public PlayersModel() {
        playersProfile = new HashMap<String, PlayersProfileModel>();
    }

    /**
     * Returns the current profile loaded in game.
     * 
     * @return the current profile.
     */
    public PlayersProfileModel getCurrentProfile() {
        return currentProfile;
    }

    /**
     * Set a new current profile in game.
     * 
     * @param name the name of the profile to load.
     */
    public void setCurrentProfile(String name) {
        currentProfile = playersProfile.get(name);
    }

    /**
     * Add a new player profile to the model.
     * 
     * @param name  the name of the new profile.
     * @param model the instance of the new profile model.
     */
    public void addPlayer(String name, PlayersProfileModel model) {
        playersProfile.put(name, model);
    }

    /**
     * Remove a player profile from the profiles map.
     * 
     * @param name the name of the player to be removed.
     */
    public void removePlayer(String name) {
        // delete profile serialized file
        (new File(SerializationManager.getInstance().getProfilesDir() + "/" + name)).delete();
        // remove from map
        playersProfile.remove(name);
    }

    /**
     * Returns all players profile saved into the map.
     * 
     * @return all players profile.
     */
    public Map<String, PlayersProfileModel> getPlayers() {
        return playersProfile;
    }

    /**
     * Save all player profiles to disk.
     */
    public void save() {
        SerializationManager sm = SerializationManager.getInstance();

        // save all profiles
        for (Map.Entry<String, PlayersProfileModel> entry : playersProfile.entrySet()) {
            sm.savePlayers(entry.getValue());
        }
    }
}
