package it.seba.juno.model;

public class OptionsModel {

    private int numberOfPlayer;
    private int screenResolution;

    public OptionsModel() {

    }

    public void setNumberOfPlayers(int np) {
        numberOfPlayer = np;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayer;
    }

    public void setScreenResolution(int sr) {
        screenResolution = sr;
    }

    public int getScreenResolution() {
        return screenResolution;
    }
}
