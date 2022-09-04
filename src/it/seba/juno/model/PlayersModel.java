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

    /**
     * Does there is a profile loaded in this model?
     */
    private boolean isProfileLoaded = false;

    /**
     * The current profile loaded.
     */
    private PlayersProfileModel currentProfile;

    /**
     * A map of player profiles.
     */
    private Map<String, PlayersProfileModel> playersProfile;

    /**
     * Class Constructor.
     */
    public PlayersModel() {
        playersProfile = new HashMap<String, PlayersProfileModel>();
    }

    /**
     * Add a new player profile to the model.
     * 
     * @param name  The name of the new profile.
     * @param model The instance of the new profile model.
     */
    public void addPlayer(String name, PlayersProfileModel model) {
        playersProfile.put(name, model);
    }

    /**
     * Returns the current profile loaded in game.
     * 
     * @return The current profile.
     */
    public PlayersProfileModel getCurrentProfile() {
        return currentProfile;
    }

    /**
     * Returns all players profile saved into the map.
     * 
     * @return All players profile.
     */
    public Map<String, PlayersProfileModel> getPlayers() {
        return playersProfile;
    }

    /**
     * Returns if a profile is selected.
     * 
     * @return True if there is a profile selected, false otherwise.
     */
    public boolean isProfileLoaded() {
        return isProfileLoaded;
    }

    /**
     * Remove a player profile from the profiles map.
     * 
     * @param name The name of the player to be removed.
     */
    public void removePlayer(String name) {
        // delete profile serialized file
        (new File(SerializationManager.getInstance().getProfilesDir() + "/" + name)).delete();
        // remove from map
        playersProfile.remove(name);
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

    /**
     * Set a new current profile in game.
     * 
     * @param name The name of the profile to load.
     */
    public void setCurrentProfile(String name) {
        isProfileLoaded = true;
        currentProfile = playersProfile.get(name);
    }
}
