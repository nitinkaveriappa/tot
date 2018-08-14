package com.tot.util;

import java.util.HashMap;

public class MessageUtil {

    private String getEmblem(String kingdom) {

        return "string";
    }

    private HashMap<Character, Integer> countCharsInMessage(String msg) {
        msg = msg.toLowerCase();
        int len = msg.length();
        HashMap<Character, Integer> cntChars = new HashMap<>(Math.min(len, 26));

        for (int i = 0; i < len; ++i) {
            char charAt = msg.charAt(i);
//            System.out.println(charAt);
            if (!cntChars.containsKey(charAt)) {
                cntChars.put(charAt, 1);
            }
            else {
                cntChars.put(charAt, cntChars.get(charAt) + 1);
            }
        }

        System.out.println(cntChars);
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
        String msg1 = "PandA";
        String msg2 = "Die or play the tame of thrones";
        MessageUtil messageUtil = new MessageUtil();
        HashMap<Character, Integer> secret = messageUtil.countCharsInMessage(msg1);
        HashMap<Character, Integer> msg = messageUtil.countCharsInMessage(msg2);
        System.out.println("Equals Method: " +secret.equals(msg));
        System.out.println("Check: " + messageUtil.checkSecretExists(secret, msg));
    }
}
