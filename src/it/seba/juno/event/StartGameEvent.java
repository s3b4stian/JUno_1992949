package it.seba.juno.event;

import java.util.EventObject;

/**
 * Game event used to start a match, trigger the card dealing to players.
 * 
 * @author Sebastian Rapetti
 * 
 */
public class StartGameEvent extends EventObject {

    private static final long serialVersionUID = 1529693571164673010L;

    /**
     * Class Constructor.
     * 
     * @param source The object that triggered this event.
     */
    public StartGameEvent(Object source) {
        super(source);
    }
}