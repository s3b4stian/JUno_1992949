package it.seba.juno.event;

import java.util.EventObject;

public class CurrentPlayerChangeColorEvent extends EventObject {

    private static final long serialVersionUID = 6655421933450940728L;

    /**
     * Class Constructor.
     * 
     * @param source the object that triggered this event.
     */
    public CurrentPlayerChangeColorEvent(Object source) {
        super(source);
    }
}