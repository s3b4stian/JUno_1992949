package it.seba.juno.player;

public enum BadgePlayed implements Badge {

    NONE("played_none.png"), GREEN("played_green.png"), BRONZE("played_bronze.png"), SILVER("played_silver.png"),
    GOLD("played_gold.png"), RED("played_red.png");

    private final String image;

    BadgePlayed(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return image;
    }
}
