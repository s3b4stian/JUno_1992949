package it.seba.juno.view.component;

/**
 * Label to title a sub section in a view.
 * 
 * @author Sebastian Rapetti
 *
 */
public class SubSectionLabel extends AbstractTextLabel {

    private static final long serialVersionUID = -4345709972401152190L;

    /**
     * Class Constructor, default text of the label is void.
     */
    public SubSectionLabel() {
        this("");
    }

    /**
     * Class Constructor.
     * 
     * @param text The default text of the label.
     */
    public SubSectionLabel(String text) {
        super(text, 24f);
    }
}
