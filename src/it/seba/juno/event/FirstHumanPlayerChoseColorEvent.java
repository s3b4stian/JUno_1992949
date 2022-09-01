package it.seba.juno.event;

import java.util.EventObject;

public class FirstHumanPlayerChoseColorEvent extends EventObject {

    private static final long serialVersionUID = 7148206871179142324L;

    /**
     * Class Constructor.
     * 
     * @param source the object that triggered this event.
     */
    public FirstHumanPlayerChoseColorEvent(Object source) {
        super(source);
    }
}