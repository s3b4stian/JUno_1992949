package it.seba.juno.event;

import java.util.EventObject;

/**
 * Game event used to update the GameView when the current player have skip the
 * turn.
 * 
 * @author Sebastian Rapetti
 */
public class CurrentPlayerSkipEvent extends EventObject {

    private static final long serialVersionUID = 3470116777291680573L;

    /**
     * Class Constructor.
     * 
     * @param source The object that triggered this event.
     */
    public CurrentPlayerSkipEvent(Object source) {
        super(source);
    }
}