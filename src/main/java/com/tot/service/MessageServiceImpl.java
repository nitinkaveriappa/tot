package com.tot.service;

import com.tot.repo.MessagesRepository;
import com.tot.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final static Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    private MessagesRepository messagesRepository;

    @Override
    public List<String> postInputMessages(List<String> inputMessages) {
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


        return checkedList;
    }
}