package it.seba.juno.view.component;

/**
 * Label to title a sub section in a view.
 * 
 * @author Sebastian Rapetti
 *
 */
public class PlayerLabel extends AbstractTextLabel {

    private static final long serialVersionUID = -4345709972401152190L;

    /**
     * Class Constructor, default text of the label is void.
     */
    public PlayerLabel() {
        this("");
    }

    /**
     * Class Constructor.
     * 
     * @param text The default text of the label.
     */
    public PlayerLabel(String text) {
        super(text, 20f);
    }
}
