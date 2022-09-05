package it.seba.juno.model;

import java.io.Serializable;

import it.seba.juno.manger.SerializationManager;
import it.seba.juno.util.Observable;

/**
 * The model for the options of the game.
 * 
 * @author Sebastian Rapetti
 *
 */
public class OptionsModel extends Observable implements Serializable {

    private static final long serialVersionUID = -4928065349983814513L;

    /**
     * Number of players of a match.
     */
    private int numberOfPlayer = 3;

    /**
     * Screen mode, true full-screen, false window.
     */
    private boolean fullScreen = false;

    /**
     * Sound, true on, false off.
     */
    private boolean sound = true;

    /**
     * Return the number of players that play a match.
     * 
     * @return The number of players, 2, 3 or 4 players.
     */
    public int getNumberOfPlayer() {
        return numberOfPlayer;
    }

    /**
     * Check if the game is in full-screen mode.
     * 
     * @return True if full-screen, false otherwise.
     */
    public boolean isFullScreen() {
        return fullScreen;
    }

    /**
     * Check if sound is on or off.
     * 
     * @return True if the sound is on, false otherwise.
     */
    public boolean isSound() {
        return sound;
    }

    /**
     * Save the state of the options to disk.
     */
    public void save() {
        SerializationManager.getInstance().saveOptions(this);
    }

    /**
     * Set the game to full-screen or to window mode.
     * 
     * @param fullScreen The next screen mode, true for full-screen, false to
     *                   window.
     */
    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    /**
     * Set the number of players for a match.
     * 
     * @param numberOfPlayer The number of players.
     */
    public void setNumberOfPlayer(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }

    /**
     * Set the sound on or off.
     * 
     * @param sound The next state of the sound.
     */
    public void setSound(boolean sound) {
        this.sound = sound;
    }
}
