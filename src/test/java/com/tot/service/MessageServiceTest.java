package com.tot.service;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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
    public void postInputMessagesTest() {
        logger.info("Test 1 ##############################");
        List<String> list = new ArrayList<>();
        list.add("Air, oaaawaala");
        list.add("Land, a1d22n333a4444p");
        list.add("Ice, zmzmzmzaztzozh");
        List<String> res = messageService.postInputMessages(list);
        assertTrue(res.contains("Air"));
        assertTrue(res.contains("Land"));
        assertTrue(res.contains("Ice"));
        logger.info("Test 2 ##############################");
        list = new ArrayList<>();
        list.add("Air, Let’s swing the sword together");
        list.add("Land, Die or play the tame of thrones");
        list.add("Ice, Ahoy! Fight for me with men and money");
        list.add("Water, Summer is coming");
        list.add("Fire, Drag on Martin!");
        res = messageService.postInputMessages(list);
        assertTrue(res.contains("Air"));
        assertTrue(res.contains("Land"));
        assertTrue(res.contains("Ice"));
        assertFalse(res.contains("Water"));
        assertTrue(res.contains("Fire"));
    }
}
