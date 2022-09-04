package it.seba.juno.event;

import java.util.EventObject;

/**
 * Game event used to update the GameView when the human player choose the
 * discard pile new color.
 * 
 * @author Sebastian Rapetti
 */
public class HumanPlayerChoseColorEvent extends EventObject {

    private static final long serialVersionUID = -8918322166647353245L;

    /**
     * Class Constructor.
     * 
     * @param source The object that triggered this event.
     */
    public HumanPlayerChoseColorEvent(Object source) {
        super(source);
    }
}