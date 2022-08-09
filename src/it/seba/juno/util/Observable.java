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
     * Class Constructor
     */
    public Observable() {
        observers = new ArrayList<InterfaceObserver>();
    }

    /**
     * Add an observer.
     * 
     * @param o
     */
    public void addObserver(InterfaceObserver o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    /**
     * Delete an Obeserver.
     * 
     * @param o
     */
    public void deleteObserver(InterfaceObserver o) {
        observers.remove(o);
    }

    /**
     * Notify to all Observers.
     *
     * @param s
     */
    public void notifyObservers(EventObject e) {
        observers.forEach(o -> o.update(this, e));
    }
}
