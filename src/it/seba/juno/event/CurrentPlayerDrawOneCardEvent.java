package it.seba.juno.event;

import java.util.EventObject;

/**
 * Game event used to update the GameView when the current player has no card to
 * drop, then draw a card.
 * 
 * @author Sebastian Rapetti
 */
public class CurrentPlayerDrawOneCardEvent extends EventObject {

    private static final long serialVersionUID = -6924750505071234036L;

    /**
     * Class Constructor.
     * 
     * @param source The object that triggered this event.
     */
    public CurrentPlayerDrawOneCardEvent(Object source) {
        super(source);
    }
}