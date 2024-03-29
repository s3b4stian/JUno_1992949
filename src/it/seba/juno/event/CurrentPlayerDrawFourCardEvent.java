package it.seba.juno.event;

import java.util.EventObject;

/**
 * Game event used to update the GameView when the current player have to draw
 * four cards.
 * 
 * @author Sebastian Rapetti
 */
public class CurrentPlayerDrawFourCardEvent extends EventObject {

    private static final long serialVersionUID = 5184077359526049651L;

    /**
     * Class Constructor.
     * 
     * @param source The object that triggered this event.
     */
    public CurrentPlayerDrawFourCardEvent(Object source) {
        super(source);
    }
}