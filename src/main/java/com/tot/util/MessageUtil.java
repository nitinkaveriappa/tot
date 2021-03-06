package com.tot.util;

import com.tot.model.Kingdom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class MessageUtil {

    private final static Logger logger = LoggerFactory.getLogger(MessageUtil.class);

    public static String getEmblem(String kingdom) {
        kingdom = kingdom.toUpperCase();
        String emblem = null;
        switch (kingdom) {
            case "LAND":
                emblem = Kingdom.LAND.getEmblem();
                break;
            case "WATER" :
                emblem = Kingdom.WATER.getEmblem();
                break;
            case "ICE" :
                emblem = Kingdom.ICE.getEmblem();
                break;
            case "AIR" :
                emblem = Kingdom.AIR.getEmblem();
                break;
            case "FIRE" :
                emblem = Kingdom.FIRE.getEmblem();
                break;
            default:
                break;
        }
        if(emblem != null) {
            return emblem;
        }
        else {
            return "NADA";
        }
    }

    public static HashMap<Character, Integer> countCharsInMessage(String msg) {
        msg = msg.toLowerCase();
        int len = msg.length();
        HashMap<Character, Integer> cntChars = new HashMap<>(Math.min(len, 26));
        for (int i = 0; i < len; ++i) {
            char charAt = msg.charAt(i);
            if (!cntChars.containsKey(charAt)) {
                cntChars.put(charAt, 1);
            }
            else {
                cntChars.put(charAt, cntChars.get(charAt) + 1);
            }
        }
        return cntChars;
    }

    public static boolean checkSecretExists(HashMap<Character, Integer> secretHashMap, HashMap<Character, Integer> msgHashMap) {
        for(Character c : secretHashMap.keySet()) {
            if(msgHashMap.containsKey(c)) {
                if(msgHashMap.get(c) < secretHashMap.get(c)) {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        return true;
    }
}
