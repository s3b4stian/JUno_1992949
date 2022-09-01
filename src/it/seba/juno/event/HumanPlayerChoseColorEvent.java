package it.seba.juno.event;

import java.util.EventObject;

public class HumanPlayerChoseColorEvent extends EventObject {

    private static final long serialVersionUID = -8918322166647353245L;

    /**
     * Class Constructor.
     * 
     * @param source the object that triggered this event.
     */
    public HumanPlayerChoseColorEvent(Object source) {
        super(source);
    }
}