package it.seba.juno.event;

import java.util.EventObject;

public class FirstEnableChoseColorPanelEvent extends EventObject {

    private static final long serialVersionUID = -107979488405683526L;

    /**
     * Class Constructor.
     * 
     * @param source the object that triggered this event.
     */
    public FirstEnableChoseColorPanelEvent(Object source) {
        super(source);
    }
}