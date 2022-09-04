package it.seba.juno.event;

import java.util.EventObject;

/**
 * Game event used to update the GameView when the current player ends the turn,
 * made a move.
 * 
 * @author Sebastian Rapetti
 */
public class CurrentPlayerMoveEvent extends EventObject {

    private static final long serialVersionUID = -2433606224504448908L;

    /**
     * Class Constructor.
     * 
     * @param source The object that triggered this event.
     */
    public CurrentPlayerMoveEvent(Object source) {
        super(source);
    }
}