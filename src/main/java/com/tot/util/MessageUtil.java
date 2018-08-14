package com.tot.util;

import com.tot.model.Kingdom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class MessageUtil {

    private final static Logger logger = LoggerFactory.getLogger(MessageUtil.class);

    private String getEmblem(String kingdom) {
        kingdom = kingdom.toUpperCase();
        String emblem = "";
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
        if(emblem.equals("")) {
            return emblem;
        }
        else {
            logger.info("Kingdom value Invalid");
            return "NADA";
        }
    }

    private HashMap<Character, Integer> countCharsInMessage(String msg) {
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
        logger.info(cntChars.toString());
        return cntChars;
    }

    private boolean checkSecretExists(HashMap<Character, Integer> secretHashMap, HashMap<Character, Integer> msgHashMap) {
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

    public static void main(String args[]) {
        String kingdom = "ICE";
        String msg2 = "Ahoy! Fight for me with men and money";
        MessageUtil messageUtil = new MessageUtil();

        String emblem = messageUtil.getEmblem(kingdom);
        System.out.println("Emblem: " + emblem);

        HashMap<Character, Integer> secret = messageUtil.countCharsInMessage(emblem);
        HashMap<Character, Integer> msg = messageUtil.countCharsInMessage(msg2);


        System.out.println("Equals Method: " +secret.equals(msg));
        System.out.println("Check: " + messageUtil.checkSecretExists(secret, msg));
    }
}
