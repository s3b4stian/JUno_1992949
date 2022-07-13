package it.seba.juno.card;

import java.util.Comparator;

public class UnoCardComparator implements Comparator<UnoCard> {

    public UnoCardComparator() {

    }

    @Override
    public int compare(UnoCard o1, UnoCard o2) {

        return o1.compareTo(o2);
    }

}
