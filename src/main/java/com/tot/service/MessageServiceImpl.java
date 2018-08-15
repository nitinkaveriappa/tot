package com.tot.service;

import com.tot.model.Messages;
import com.tot.repo.MessagesRepository;
import com.tot.util.GeneralUtil;
import com.tot.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final static Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

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
                logger.info("emblem: "+emblem);
                if (emblem.equals("NADA")) {
                    throw new Exception("Invalid Kingdom Value");
                }
            } catch (Exception e) {
                logger.info("Invalid Kingdom Value");
            }
            HashMap<Character, Integer> secret = MessageUtil.countCharsInMessage(emblem);
            logger.info("secret: "+secret);
            message = split[1].trim();
            HashMap<Character, Integer> msg = MessageUtil.countCharsInMessage(message);
            logger.info("msg: "+msg);
            logger.info("add to uncheckedList: "+kingdom);
            uncheckedList.add(kingdom);
            if(MessageUtil.checkSecretExists(secret, msg)) {
                logger.info("add to checkedList: "+kingdom);
                checkedList.add(kingdom);
            }
        }
        logger.info("checkedList: "+checkedList);
        logger.info("uncheckedList: "+uncheckedList);

        res.put("checkedList", checkedList);
        res.put("uncheckedList", uncheckedList);
        return res;
    }

    @Override
    public ResponseEntity<?> postInputMessages(String[] inputMessages) {
        HashMap<String, List<String>> op = parseInputMessages(inputMessages);
        Messages messages = new Messages();
        messages.setId(GeneralUtil.getUniqueID());
        messages.setInputmessages(Arrays.toString(inputMessages));
        messages.setSender("King Shah");
        messages.setReceivers(op.get("uncheckedList").toString());
        messages.setVerifiedreceivers(op.get("checkedList").toString());
        logger.info("Messages: " + messages);
        messagesRepository.save(messages);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}