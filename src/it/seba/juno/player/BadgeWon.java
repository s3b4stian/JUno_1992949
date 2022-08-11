package it.seba.juno.player;

public enum BadgeWon implements Badge {
    NONE("won_none.png"),
    GREEN("won_green.png"), BRONZE("won_bronze.png"), SILVER("won_silver.png"), GOLD("won_gold.png"), RED("won_red.png");
    
    private final String image;
    
    BadgeWon(String image) {
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
