package it.seba.juno.event;

import java.util.EventObject;

public class HumanPlayerDropEvent extends EventObject {

    private static final long serialVersionUID = -9095607591015419942L;

    /**
     * Class Constructor.
     * 
     * @param source the object that triggered this event.
     */
    public HumanPlayerDropEvent(Object source) {
        super(source);
    }
}