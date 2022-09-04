package it.seba.juno.event;

import java.util.EventObject;

/**
 * Game event used to update the GameView when the current player have to draw
 * two cards.
 * 
 * @author Sebastian Rapetti
 */
public class CurrentPlayerDrawTwoCardEvent extends EventObject {

    private static final long serialVersionUID = 1716568394364330768L;

    /**
     * Class Constructor.
     * 
     * @param source The object that triggered this event.
     */
    public CurrentPlayerDrawTwoCardEvent(Object source) {
        super(source);
    }
}