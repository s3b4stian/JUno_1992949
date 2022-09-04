package it.seba.juno.event;

import java.util.EventObject;

/**
 * Game event used to update view when the application starts.
 * 
 * @author Sebastian Rapetti
 *
 */
public class FirstLoadEvent extends EventObject {

    private static final long serialVersionUID = -6210758906120275330L;

    /**
     * Class Constructor.
     * 
     * @param source The object that triggered this event.
     */
    public FirstLoadEvent(Object source) {
        super(source);
    }
}