package it.seba.juno.event;

import java.util.EventObject;

public class CurrentPlayerDrawOneCardAndDropEvent extends EventObject {

    private static final long serialVersionUID = -4638425927430776323L;

    /**
     * Class Constructor.
     * 
     * @param source the object that triggered this event.
     */
    public CurrentPlayerDrawOneCardAndDropEvent(Object source) {
        super(source);
    }
}