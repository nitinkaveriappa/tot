package com.tot.service;

import com.tot.model.Messages;
import com.tot.repo.MessagesRepository;
import com.tot.util.GeneralUtil;
import com.tot.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessagesRepository messagesRepository;

    @Override
    public HashMap<String, List<String>> parseInputMessages(String[] inputMessages) {
        HashMap<String, List<String>> res = new HashMap<>();
        List<String> checkedList = new ArrayList<>();
        List<String> uncheckedList = new ArrayList<>();
        for(String s : inputMessages) {
            String kingdom = null, message, emblem = null;
            String split[] = s.split(",");
            try {
                kingdom = split[0].trim();
                emblem = MessageUtil.getEmblem(kingdom);
                log.info("emblem: "+emblem);
                if (emblem.equals("NADA")) {
                    throw new Exception("Invalid Kingdom Value");
                }
            } catch (Exception e) {
                log.info("Invalid Kingdom Value");
            }
            HashMap<Character, Integer> secret = MessageUtil.countCharsInMessage(emblem);
            log.info("secret: "+secret);
            message = split[1].trim();
            HashMap<Character, Integer> msg = MessageUtil.countCharsInMessage(message);
            log.info("msg: "+msg);
            log.info("add to uncheckedList: "+kingdom);
            uncheckedList.add(kingdom);
            if(MessageUtil.checkSecretExists(secret, msg)) {
                log.info("add to checkedList: "+kingdom);
                checkedList.add(kingdom);
            }
        }
        log.info("checkedList: "+checkedList);
        log.info("uncheckedList: "+uncheckedList);

        res.put("checkedList", checkedList);
        res.put("uncheckedList", uncheckedList);
        return res;
    }

    @Override
    public ResponseEntity<?> postInputMessages(String sender, String[] inputMessages) {
        HashMap<String, List<String>> op = parseInputMessages(inputMessages);
        Messages messages = new Messages();
        messages.setId(GeneralUtil.getUniqueID());
        messages.setInputmessages(inputMessages);
        messages.setSender(sender);
        messages.setReceivers(op.get("uncheckedList").toArray(new String[0]));
        messages.setVerifiedreceivers(op.get("checkedList").toArray(new String[0]));
        log.info("Messages: " + messages);
        messagesRepository.save(messages);
        return new ResponseEntity("Messages Parsed Successfully",HttpStatus.OK);
    }

    @Override
    public HashMap<String, Integer> countVotes(List<Messages> list) {
        HashMap<String, Integer> votes = new HashMap<>();
        for(Messages msgs : list) {
            for(String s : msgs.getVerifiedreceivers()) {
                if (votes.keySet().contains(msgs.getSender()))
                    votes.put(msgs.getSender(), votes.get(msgs.getSender()) + 1);
                else
                    votes.put(msgs.getSender(), 1);
            }
        }
        return votes;
    }

    @Override
    public String whosRuler() {
        String ruler = "";
        List<Messages> list = messagesRepository.findAll();
        int maxValueInMap = 0;
        HashMap<String, Integer> votes = countVotes(list);
        for (Map.Entry<String,Integer> entry : votes.entrySet())
        {
            String key  = entry.getKey();
            Integer val = entry.getValue();
            if (val > maxValueInMap)
            {
                maxValueInMap = val;
                ruler = key;
            }
            // If there is a tie, pick lexicographically smaller.
            else if (val == maxValueInMap &&
                    ruler.compareTo(key) > 0)
                ruler = key;
        }
        return ruler;
    }

    @Override
    public String alliesOfRuler(String sender) {
        List<String> allies = new ArrayList<>();
        List<Messages> list;
        if(sender != null) {
            list = messagesRepository.findBySender(sender);
        }
        else {
            list = messagesRepository.findBySender(whosRuler());
        }
        for(Messages msgs : list) {
            for (String s : msgs.getVerifiedreceivers()) {
                allies.add(s);
            }
        }
        return allies.toString().replace("[", "").replace("]", "");
    }
}