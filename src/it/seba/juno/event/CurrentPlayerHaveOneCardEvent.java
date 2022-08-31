package it.seba.juno.event;

import java.util.EventObject;

public class CurrentPlayerHaveOneCardEvent extends EventObject {

    private static final long serialVersionUID = -6924750505071234036L;

    /**
     * Class Constructor.
     * 
     * @param source the object that triggered this event.
     */
    public CurrentPlayerHaveOneCardEvent(Object source) {
        super(source);
    }
}