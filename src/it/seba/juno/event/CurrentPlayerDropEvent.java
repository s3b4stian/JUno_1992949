package it.seba.juno.event;

import java.util.EventObject;

/**
 * Game event used to update the GameView when the current player drop a card.
 * 
 * @author Sebastian Rapetti
 */
public class CurrentPlayerDropEvent extends EventObject {

    private static final long serialVersionUID = -7955158188620118673L;

    /**
     * Class Constructor.
     * 
     * @param source The object that triggered this event.
     */
    public CurrentPlayerDropEvent(Object source) {
        super(source);
    }
}