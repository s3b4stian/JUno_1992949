package it.seba.juno.util;

import java.util.EventObject;

/**
 * Used to update view when the application starts.
 * 
 * @author Sebastian Rapetti
 *
 */
public class FirstLoadEvent extends EventObject {

    private static final long serialVersionUID = -6210758906120275330L;

    /**
     * Class Constructor.
     * 
     * @param source the object that triggered this event.
     */
    public FirstLoadEvent(Object source) {
        super(source);
    }
}