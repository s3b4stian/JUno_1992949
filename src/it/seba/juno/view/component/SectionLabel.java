package it.seba.juno.view.component;

/**
 * Label to title a section in a view.
 * 
 * @author Sebastian Rapetti
 *
 */
public class SectionLabel extends AbstractTextLabel {

    private static final long serialVersionUID = -188287675781778913L;

    /**
     * Class Constructor, default text of the label is void.
     */
    public SectionLabel() {
        this("");
    }

    /**
     * Class Constructor.
     * 
     * @param text The default text of the label.
     */
    public SectionLabel(String text) {
        super(text, 36f);
    }
}
