package it.seba.juno.event;

import java.util.EventObject;

/**
 * Game event used to update the GameView when the current player has no card to
 * drop, then draw a card and drop it.
 * 
 * @author Sebastian Rapetti
 */
public class CurrentPlayerDrawOneCardAndDropEvent extends EventObject {

    private static final long serialVersionUID = -4638425927430776323L;

    /**
     * Class Constructor.
     * 
     * @param source The object that triggered this event.
     */
    public CurrentPlayerDrawOneCardAndDropEvent(Object source) {
        super(source);
    }
}