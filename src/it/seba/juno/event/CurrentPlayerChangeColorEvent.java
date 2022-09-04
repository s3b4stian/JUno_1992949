package it.seba.juno.event;

import java.util.EventObject;

/**
 * Game event used to update the GameView when the current player change the
 * discard pile color.
 * 
 * @author Sebastian Rapetti
 */
public class CurrentPlayerChangeColorEvent extends EventObject {

    private static final long serialVersionUID = 6655421933450940728L;

    /**
     * Class Constructor.
     * 
     * @param source The object that triggered this event.
     */
    public CurrentPlayerChangeColorEvent(Object source) {
        super(source);
    }
}