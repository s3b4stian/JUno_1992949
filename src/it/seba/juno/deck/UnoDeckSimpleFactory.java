package it.seba.juno.deck;

import java.util.ArrayList;
import java.util.List;

import it.seba.juno.card.UnoCard;
import it.seba.juno.card.UnoColor;
import it.seba.juno.card.UnoValue;

public class UnoDeckSimpleFactory {

    /**
     * Returns a ready to use deck of Uno cards.
     * 
     * @return
     */
    public UnoDeck makeUnoDeck() {

        List<UnoCard> cardList = new ArrayList<UnoCard>();

        // add zeroes card into the deck
        cardList.add(new UnoCard(UnoValue.ZERO, UnoColor.BLUE));
        cardList.add(new UnoCard(UnoValue.ZERO, UnoColor.RED));
        cardList.add(new UnoCard(UnoValue.ZERO, UnoColor.GREEN));
        cardList.add(new UnoCard(UnoValue.ZERO, UnoColor.YELLOW));

        // add two card from 1 to stop of every color
        for (UnoValue value : UnoValue.values()) {
            if (value.equals(UnoValue.ZERO) || value.equals(UnoValue.WILD) || value.equals(UnoValue.WILD_DRAW_FOUR)) {
                continue;
            }

            for (UnoColor color : UnoColor.values()) {
                cardList.add(new UnoCard(value, color));
                cardList.add(new UnoCard(value, color));
            }
        }

        // add wild cards
        for (int i = 0; i < 4; i++) {
            cardList.add(new UnoCard(UnoValue.WILD));
            cardList.add(new UnoCard(UnoValue.WILD_DRAW_FOUR));
        }

        UnoDeck deck = new UnoDeck(cardList);
        deck.shuffle();

        return deck;
    }
}
