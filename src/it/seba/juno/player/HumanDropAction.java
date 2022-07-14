package it.seba.juno.player;

import it.seba.juno.card.UnoCard;

public interface HumanDropAction extends PlayerAction {

    UnoCard dropCard(int index);
}
