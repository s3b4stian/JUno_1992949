package it.seba.juno.event;

import java.util.EventObject;

/**
 * Game event used to update the GameView when the current player is the winner
 * of the match.
 * 
 * @author Sebastian Rapetti
 */
public class CurrentPlayerWinnerEvent extends EventObject {

    private static final long serialVersionUID = 6869946478443238893L;

    /**
     * Class Constructor.
     * 
     * @param source The object that triggered this event.
     */
    public CurrentPlayerWinnerEvent(Object source) {
        super(source);
    }
}