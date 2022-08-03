package it.seba.juno.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.seba.juno.deck.DiscardPile;

class TestNpcPlayer {

    @Test
    void newInstanceTest() {

        Player p = new NpcPlayer("NPC0", new ColorDropStrategy(new DiscardPile()), new MostColorStrategy());

        assertTrue(p instanceof Player);
        assertTrue(p instanceof NpcPlayer);
        //assertEquals(p.getName(), "NPC0");

    }

}
