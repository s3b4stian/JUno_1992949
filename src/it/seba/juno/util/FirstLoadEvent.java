package it.seba.juno.util;

import java.util.EventObject;

public class FirstLoadEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    public FirstLoadEvent(Object source) {
        super(source);
    }
}