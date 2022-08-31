package it.seba.juno.event;

import java.util.EventObject;

public class CurrentPlayerWinnerEvent extends EventObject {

    private static final long serialVersionUID = 6869946478443238893L;

    /**
     * Class Constructor.
     * 
     * @param source the object that triggered this event.
     */
    public CurrentPlayerWinnerEvent(Object source) {
        super(source);
    }
}