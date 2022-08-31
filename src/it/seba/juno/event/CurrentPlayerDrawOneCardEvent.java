package it.seba.juno.event;

import java.util.EventObject;

public class CurrentPlayerDrawOneCardEvent extends EventObject {

    private static final long serialVersionUID = -6924750505071234036L;

    /**
     * Class Constructor.
     * 
     * @param source the object that triggered this event.
     */
    public CurrentPlayerDrawOneCardEvent(Object source) {
        super(source);
    }
}