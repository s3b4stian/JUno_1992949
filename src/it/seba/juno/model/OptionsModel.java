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

    private int numberOfPlayer = 3;
    private boolean fullScreen = false;
    private boolean sound = true;

    /**
     * Class Constructor.
     */
    public OptionsModel() {
    }

    /**
     * Return the number of players that play a match.
     * 
     * @return the number of players, 2, 3 or 4 players.
     */
    public int getNumberOfPlayer() {
        return numberOfPlayer;
    }

    /**
     * Set the number of players for a match.
     * 
     * @param numberOfPlayer the number of players.
     */
    public void setNumberOfPlayer(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }

    /**
     * Check if sound is on or off.
     * 
     * @return true if the sound is on, false otherwise.
     */
    public boolean isSound() {
        return sound;
    }

    /**
     * Set the sound on or off.
     * 
     * @param sound the next state of the sound.
     */
    public void setSound(boolean sound) {
        this.sound = sound;
    }

    /**
     * Check if the game is in full-screen mode.
     * 
     * @return true if full-screen, false otherwise.
     */
    public boolean isFullScreen() {
        return fullScreen;
    }

    /**
     * Set the game to full-screen or to window mode.
     * 
     * @param fullScreen the next screen mode, true for full-screen, false to
     *                   window.
     */
    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    /**
     * Save the state of the options to disk.
     */
    public void save() {
        SerializationManager.getInstance().saveOptions(this);
    }
}
