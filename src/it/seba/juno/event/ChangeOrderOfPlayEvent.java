package it.seba.juno.event;

import java.util.EventObject;

/**
 * Game event used to update the GameView when there is a change of the order of
 * play.
 * 
 * @author Sebastian Rapetti
 */
public class ChangeOrderOfPlayEvent extends EventObject {

    private static final long serialVersionUID = 4274385486518085745L;

    /**
     * Class Constructor.
     * 
     * @param source The object that triggered this event.
     */
    public ChangeOrderOfPlayEvent(Object source) {
        super(source);
    }
}