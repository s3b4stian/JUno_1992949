package it.seba.juno.event;

import java.util.EventObject;

public class StartGameEvent extends EventObject {

    private static final long serialVersionUID = 1529693571164673010L;

    /**
     * Class Constructor.
     * 
     * @param source the object that triggered this event.
     */
    public StartGameEvent(Object source) {
        super(source);
    }
}