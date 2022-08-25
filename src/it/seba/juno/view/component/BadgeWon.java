package it.seba.juno.view.component;

/**
 * Identify a badge assigned to player for won matches.
 * 
 * @author Sebastian Rapetti
 *
 */
public enum BadgeWon implements Badge {

    /**
     * Void Badge.
     */
    NONE("won_none.png"),

    /**
     * Green badge, assigned for 10 matches won.
     */
    GREEN("won_green.png"),

    /**
     * Bronze badge, assigned for 20 matches won.
     */
    BRONZE("won_bronze.png"),

    /**
     * Silver badge, assigned for 40 matches won.
     */
    SILVER("won_silver.png"),

    /**
     * Gold badge, assigned for 80 matches won.
     */
    GOLD("won_gold.png"),

    /**
     * Red badge, assigned for 160 matches won.
     */
    RED("won_red.png");

    private final String image;

    /**
     * Enum Constructor.
     * 
     * @param image the image file name of the badge.
     */
    BadgeWon(String image) {
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
     * @return the image file name.
     */
    @Override
    public String toString() {
        return image;
    }
}
