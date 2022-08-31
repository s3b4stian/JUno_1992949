package it.seba.juno.event;

import java.util.EventObject;

public class HumanPlayerDrawOneCardEvent extends EventObject {

    private static final long serialVersionUID = 4001258379906804308L;

    /**
     * Class Constructor.
     * 
     * @param source the object that triggered this event.
     */
    public HumanPlayerDrawOneCardEvent(Object source) {
        super(source);
    }
}