package it.seba.juno.event;

import java.util.EventObject;

public class EnableChoseColorPanelEvent extends EventObject {

    private static final long serialVersionUID = 1432815215743204105L;

    /**
     * Class Constructor.
     * 
     * @param source the object that triggered this event.
     */
    public EnableChoseColorPanelEvent(Object source) {
        super(source);
    }
}