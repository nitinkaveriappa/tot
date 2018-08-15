package com.tot.service;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MessageServiceTest {

    private final static Logger logger = LoggerFactory.getLogger(MessageServiceTest.class);

    private MessageService messageService;

    @Before
    public void initial() {
        messageService = new MessageServiceImpl();
    }

    @Test
    public void parseInputMessagesTest() {
        logger.info("Test 1 ##############################");
        String[] msgs = new String[]{"Air, oaaawaala", "Land, a1d22n333a4444p", "Ice, zmzmzmzaztzozh"};
        HashMap<String, List<String>> res = messageService.parseInputMessages(msgs);
        List<String> checkedList = res.get("checkedList");
        assertTrue(checkedList.contains("Air"));
        assertTrue(checkedList.contains("Land"));
        assertTrue(checkedList.contains("Ice"));
        logger.info("Test 2 ##############################");
        msgs = new String[]{"Air, Let’s swing the sword together", "Land, Die or play the tame of thrones",
                "Ice, Ahoy! Fight for me with men and money", "Water, Summer is coming", "Fire, Drag on Martin!"};
        res = messageService.parseInputMessages(msgs);
        checkedList = res.get("checkedList");
        assertTrue(checkedList.contains("Air"));
        assertTrue(checkedList.contains("Land"));
        assertTrue(checkedList.contains("Ice"));
        assertFalse(checkedList.contains("Water"));
        assertTrue(checkedList.contains("Fire"));
    }
}
