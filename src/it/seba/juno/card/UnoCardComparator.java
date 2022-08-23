package it.seba.juno.card;

import java.util.Comparator;

/**
 * Compare Two cards, used to reorder cards.
 * 
 * @author Sebastian Rapetti
 *
 */
public class UnoCardComparator implements Comparator<UnoCard> {

    /**
     * Class constructor.
     */
    public UnoCardComparator() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(UnoCard o1, UnoCard o2) {

        return o1.compareTo(o2);
    }

}
