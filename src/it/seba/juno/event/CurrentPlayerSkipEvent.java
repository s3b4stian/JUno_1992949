package it.seba.juno.event;

import java.util.EventObject;

public class CurrentPlayerSkipEvent extends EventObject {

    private static final long serialVersionUID = 3470116777291680573L;

    /**
     * Class Constructor.
     * 
     * @param source the object that triggered this event.
     */
    public CurrentPlayerSkipEvent(Object source) {
        super(source);
    }
}