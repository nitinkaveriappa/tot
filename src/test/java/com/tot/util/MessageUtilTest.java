package com.tot.util;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MessageUtilTest {

    private MessageUtil messageUtil;

    @Before
    public void init() {
       messageUtil = new MessageUtil();
    }

    @Test
    public void getEmblemTest() {
        assertEquals(messageUtil.getEmblem("LAND"), "PANDA");
        assertEquals(messageUtil.getEmblem("ICE"), "MAMMOTH");
        assertEquals(messageUtil.getEmblem("WATER"), "OCTOPUS");
        assertEquals(messageUtil.getEmblem("AIR"), "OWL");
        assertEquals(messageUtil.getEmblem("FIRE"), "DRAGON");
    }

    @Test
    public void countCharsInMessageTest() {
        int i;
        String kingdom = "ICE";
        HashMap<Character, Integer> hashMap = messageUtil.countCharsInMessage(messageUtil.getEmblem(kingdom));
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
        String emblem = messageUtil.getEmblem(kingdom);
        HashMap<Character, Integer> secret = messageUtil.countCharsInMessage(emblem);
        HashMap<Character, Integer> msg = messageUtil.countCharsInMessage(msg2);
        assertTrue(messageUtil.checkSecretExists(secret, msg));
    }

    @Test
    public void checkSecretExistsFalse() {
        String kingdom = "WATER";
        String msg2 = "“Summer is coming”";
        String emblem = messageUtil.getEmblem(kingdom);
        HashMap<Character, Integer> secret = messageUtil.countCharsInMessage(emblem);
        HashMap<Character, Integer> msg = messageUtil.countCharsInMessage(msg2);
        assertFalse(messageUtil.checkSecretExists(secret, msg));
    }
}
