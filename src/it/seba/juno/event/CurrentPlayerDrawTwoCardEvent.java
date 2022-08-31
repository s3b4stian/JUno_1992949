package it.seba.juno.event;

import java.util.EventObject;

public class CurrentPlayerDrawTwoCardEvent extends EventObject {

    private static final long serialVersionUID = 1716568394364330768L;

    /**
     * Class Constructor.
     * 
     * @param source the object that triggered this event.
     */
    public CurrentPlayerDrawTwoCardEvent(Object source) {
        super(source);
    }
}