package it.seba.juno.model;

import it.seba.juno.util.Observable;

public class OptionsModel extends Observable {

    private int numberOfPlayer = 3;
    private boolean fullScreen = false;
    private boolean sound = true;

    public OptionsModel() {
    }

    public int getNumberOfPlayer() {
        return numberOfPlayer;
    }

    public void setNumberOfPlayer(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }

    public boolean isSound() {
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }
}
