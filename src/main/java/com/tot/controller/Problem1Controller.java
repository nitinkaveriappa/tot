package com.tot.controller;

import com.tot.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/p1")
public class Problem1Controller {

    private final static Logger logger = LoggerFactory.getLogger(Problem1Controller.class);

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/sendmsgs", method = RequestMethod.POST)
    public ResponseEntity<?> sendMessages(List<String> inputMessages) {
        logger.info("Inside Problem1Controller: sendMessages(): "+ inputMessages);
        messageService.postInputMessages(inputMessages);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}