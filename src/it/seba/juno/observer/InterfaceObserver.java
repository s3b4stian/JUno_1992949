package it.seba.juno.observer;

import java.util.EventObject;

/**
 * My implementation of Observer interface.<br>
 * Only to avoid the deprecation message :)
 * 
 * @author Sebastian Rapetti
 *
 */
public interface InterfaceObserver {

    /**
     * Update the Observer.
     *
     * @param o The Observable to update.
     * @param e The event triggered the update.
     */
    void update(Observable o, EventObject e);
}
