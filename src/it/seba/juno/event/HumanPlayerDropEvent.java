package it.seba.juno.event;

import java.util.EventObject;

/**
 * Game event used to update the GameView when the human player drop a card.
 * 
 * @author Sebastian Rapetti
 */
public class HumanPlayerDropEvent extends EventObject {

    private static final long serialVersionUID = -9095607591015419942L;

    /**
     * Class Constructor.
     * 
     * @param source The object that triggered this event.
     */
    public HumanPlayerDropEvent(Object source) {
        super(source);
    }
}