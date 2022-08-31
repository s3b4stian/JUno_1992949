package it.seba.juno.event;

import java.util.EventObject;

public class CurrentPlayerDrawFourCardEvent extends EventObject {

    private static final long serialVersionUID = 5184077359526049651L;

    /**
     * Class Constructor.
     * 
     * @param source the object that triggered this event.
     */
    public CurrentPlayerDrawFourCardEvent(Object source) {
        super(source);
    }
}