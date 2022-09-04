package it.seba.juno.event;

import java.util.EventObject;

/**
 * Game event used to update the GameView when the current player have to choose
 * the discard pile color when the first card in discard pile is a wild.
 * 
 * @author Sebastian Rapetti
 */
public class FirstEnableChoseColorPanelEvent extends EventObject {

    private static final long serialVersionUID = -107979488405683526L;

    /**
     * Class Constructor.
     * 
     * @param source The object that triggered this event.
     */
    public FirstEnableChoseColorPanelEvent(Object source) {
        super(source);
    }
}