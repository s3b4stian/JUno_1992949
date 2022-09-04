package it.seba.juno.event;

import java.util.EventObject;

/**
 * Game event used to update the GameView when the human player have to choose
 * the discard pile color when the first card in discard pile is a wild, the
 * GameView enable the color panel, to choose new color.
 * 
 * @author Sebastian Rapetti
 */
public class FirstHumanPlayerChoseColorEvent extends EventObject {

    private static final long serialVersionUID = 7148206871179142324L;

    /**
     * Class Constructor.
     * 
     * @param source The object that triggered this event.
     */
    public FirstHumanPlayerChoseColorEvent(Object source) {
        super(source);
    }
}