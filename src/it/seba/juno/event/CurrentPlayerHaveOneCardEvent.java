package it.seba.juno.event;

import java.util.EventObject;

/**
 * Game event used to update the GameView when the current player have only one
 * card.
 * 
 * @author Sebastian Rapetti
 */
public class CurrentPlayerHaveOneCardEvent extends EventObject {

    private static final long serialVersionUID = -6924750505071234036L;

    /**
     * Class Constructor.
     * 
     * @param source The object that triggered this event.
     */
    public CurrentPlayerHaveOneCardEvent(Object source) {
        super(source);
    }
}