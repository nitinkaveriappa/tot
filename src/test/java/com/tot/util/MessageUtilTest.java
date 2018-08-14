package com.tot.util;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MessageUtilTest {
    
    @Test
    public void getEmblemTest() {
        assertEquals(MessageUtil.getEmblem("LAND"), "PANDA");
        assertEquals(MessageUtil.getEmblem("ICE"), "MAMMOTH");
        assertEquals(MessageUtil.getEmblem("WATER"), "OCTOPUS");
        assertEquals(MessageUtil.getEmblem("AIR"), "OWL");
        assertEquals(MessageUtil.getEmblem("FIRE"), "DRAGON");
    }

    @Test
    public void countCharsInMessageTest() {
        int i;
        String kingdom = "ICE";
        HashMap<Character, Integer> hashMap = MessageUtil.countCharsInMessage(MessageUtil.getEmblem(kingdom));
        i = hashMap.get('m');
        assertEquals(i,3);
        i = hashMap.get('a');
        assertEquals(i,1);
        i = hashMap.get('o');
        assertEquals(i,1);
        i = hashMap.get('t');
        assertEquals(i,1);
        i = hashMap.get('h');
        assertEquals(i,1);
    }

    @Test
    public void checkSecretExistsTrue() {
        String kingdom = "ICE";
        String msg2 = "Ahoy! Fight for me with men and money";
        String emblem = MessageUtil.getEmblem(kingdom);
        HashMap<Character, Integer> secret = MessageUtil.countCharsInMessage(emblem);
        HashMap<Character, Integer> msg = MessageUtil.countCharsInMessage(msg2);
        assertTrue(MessageUtil.checkSecretExists(secret, msg));
    }

    @Test
    public void checkSecretExistsFalse() {
        String kingdom = "WATER";
        String msg2 = "“Summer is coming”";
        String emblem = MessageUtil.getEmblem(kingdom);
        HashMap<Character, Integer> secret = MessageUtil.countCharsInMessage(emblem);
        HashMap<Character, Integer> msg = MessageUtil.countCharsInMessage(msg2);
        assertFalse(MessageUtil.checkSecretExists(secret, msg));
    }
}
