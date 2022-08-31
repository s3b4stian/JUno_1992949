package it.seba.juno.event;

import java.util.EventObject;

public class CurrentPlayerMoveEvent extends EventObject {

    private static final long serialVersionUID = -2433606224504448908L;

    /**
     * Class Constructor.
     * 
     * @param source the object that triggered this event.
     */
    public CurrentPlayerMoveEvent(Object source) {
        super(source);
    }
}