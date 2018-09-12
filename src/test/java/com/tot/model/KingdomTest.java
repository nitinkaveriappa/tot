package com.tot.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KingdomTest {

    @Test
    public void KingdomTest() {
        assertEquals(Kingdom.LAND.getEmblem(),"PANDA");
        assertEquals(Kingdom.WATER.getEmblem(),"OCTOPUS");
        assertEquals(Kingdom.ICE.getEmblem(),"MAMMOTH");
        assertEquals(Kingdom.AIR.getEmblem(),"OWL");
        assertEquals(Kingdom.FIRE.getEmblem(),"DRAGON");
        assertEquals(Kingdom.SPACE.getEmblem(),"GORILLA");
    }
}
