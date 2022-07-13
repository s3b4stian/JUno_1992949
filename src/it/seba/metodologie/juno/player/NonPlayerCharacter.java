package it.seba.metodologie.juno.player;

import it.seba.metodologie.juno.card.UnoCard;

//import javax.swing.Icon;

public class NonPlayerCharacter extends AbstractPlayer implements NonPlayerCharacterDrop {

    DropBehavior dropBehavior;

    public NonPlayerCharacter(String name, DropBehavior dropper) {
        super(name);
        dropBehavior = dropper;
        dropBehavior.setPlayerCards(cards);
    }

    public boolean isNpc() {
        return true;
    }

    @Override
    public UnoCard dropCard() {
        return dropBehavior.dropCard();
    }
}
