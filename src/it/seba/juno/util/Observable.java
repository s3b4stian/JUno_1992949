package it.seba.juno.util;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

/**
 * My implementation of Observable class.<br>
 * Only to avoid the deprecation message :)
 * 
 * @author Sebastian Rapetti
 *
 */
public class Observable {

    private List<InterfaceObserver> observers;

    /**
     * Class Constructor.
     */
    public Observable() {
        observers = new ArrayList<InterfaceObserver>();
    }

    /**
     * Add an Observer.
     * 
     * @param o The object to be added to observers.
     */
    public void addObserver(InterfaceObserver o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    /**
     * Delete an Observer.
     * 
     * @param o The object to be deleted from observers.
     */
    public void deleteObserver(InterfaceObserver o) {
        observers.remove(o);
    }

    /**
     * Notify to all Observers.
     *
     * @param e TShe event that triggered the notify.
     */
    public void notifyObservers(EventObject e) {
        observers.forEach(o -> o.update(this, e));
    }
}
