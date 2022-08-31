package it.seba.juno.event;

import java.util.EventObject;

public class HumanDropEvent extends EventObject {


    private static final long serialVersionUID = -9095607591015419942L;

    /**
     * Class Constructor.
     * 
     * @param source the object that triggered this event.
     */
    public HumanDropEvent(Object source) {
        super(source);
    }
}