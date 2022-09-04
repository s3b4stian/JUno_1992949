package it.seba.juno.event;

import java.util.EventObject;

/**
 * Game event used to update the GameView when the human player have to choose
 * the discard pile color, the GameView enable the color panel, to choose new
 * color.
 * 
 * @author Sebastian Rapetti
 */
public class EnableChoseColorPanelEvent extends EventObject {

    private static final long serialVersionUID = 1432815215743204105L;

    /**
     * Class Constructor.
     * 
     * @param source The object that triggered this event.
     */
    public EnableChoseColorPanelEvent(Object source) {
        super(source);
    }
}