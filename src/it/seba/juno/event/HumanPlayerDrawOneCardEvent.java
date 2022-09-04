package it.seba.juno.event;

import java.util.EventObject;

/**
 * Game event used to update the GameView when the human player has no card to
 * drop, then draw a card.
 * 
 * @author Sebastian Rapetti
 */
public class HumanPlayerDrawOneCardEvent extends EventObject {

    private static final long serialVersionUID = 4001258379906804308L;

    /**
     * Class Constructor.
     * 
     * @param source The object that triggered this event.
     */
    public HumanPlayerDrawOneCardEvent(Object source) {
        super(source);
    }
}