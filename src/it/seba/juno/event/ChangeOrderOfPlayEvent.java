package it.seba.juno.event;

import java.util.EventObject;

public class ChangeOrderOfPlayEvent extends EventObject {

    private static final long serialVersionUID = 4274385486518085745L;

    /**
     * Class Constructor.
     * 
     * @param source the object that triggered this event.
     */
    public ChangeOrderOfPlayEvent(Object source) {
        super(source);
    }
}