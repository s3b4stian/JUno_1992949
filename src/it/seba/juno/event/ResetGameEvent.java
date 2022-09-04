package it.seba.juno.event;

import java.util.EventObject;

/**
 * Game event used to reset game view when there is a change in options.
 * 
 * @author Sebastian Rapetti
 *
 */
public class ResetGameEvent extends EventObject {

    private static final long serialVersionUID = -3814667873027825626L;

    /**
     * Class Constructor.
     * 
     * @param source The object that triggered this event.
     */
    public ResetGameEvent(Object source) {
        super(source);
    }
}