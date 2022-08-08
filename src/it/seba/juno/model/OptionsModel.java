package it.seba.juno.model;

import it.seba.juno.util.Observable;

public class OptionsModel extends Observable {

    private int numberOfPlayer;
    private boolean fullScreen;

    public OptionsModel() {

    }

    public void setNumberOfPlayers(int np) {
        numberOfPlayer = np;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayer;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }
}
