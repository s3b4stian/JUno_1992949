package it.seba.juno.event;

import java.util.EventObject;

public class CurrentPlayerDropEvent extends EventObject {

    private static final long serialVersionUID = -7955158188620118673L;

    /**
     * Class Constructor.
     * 
     * @param source the object that triggered this event.
     */
    public CurrentPlayerDropEvent(Object source) {
        super(source);
    }
}