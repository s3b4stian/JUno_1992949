package it.seba.juno.view.component;

/**
 * Identify a badge assigned to player for played matches.
 * 
 * @author Sebastian Rapetti
 *
 */
public enum BadgePlayed implements Badge {

    /**
     * Void Badge.
     */
    NONE("played_none.png"),

    /**
     * Green badge, assigned for 10 matches played.
     */
    GREEN("played_green.png"),

    /**
     * Bronze badge, assigned for 20 matches played.
     */
    BRONZE("played_bronze.png"),

    /**
     * Silver badge, assigned for 40 matches played.
     */
    SILVER("played_silver.png"),

    /**
     * Gold badge, assigned for 80 matches played.
     */
    GOLD("played_gold.png"),

    /**
     * Red badge, assigned for 160 matches played.
     */
    RED("played_red.png");

    private final String image;

    /**
     * Enum Constructor.
     * 
     * @param image The image file name of the badge.
     */
    BadgePlayed(String image) {
        this.image = image;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getImage() {
        return image;
    }

    /**
     * Casting to String, returns the image file name (with extension) associate to
     * the badge.
     * 
     * @return The image file name.
     */
    @Override
    public String toString() {
        return image;
    }
}
