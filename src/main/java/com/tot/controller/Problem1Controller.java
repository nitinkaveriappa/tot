package com.tot.controller;

import com.tot.model.JsonRequest;
import com.tot.model.Messages;
import com.tot.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/p1")
@Slf4j
public class Problem1Controller {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/sendmsgs", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> sendMessages(@RequestBody JsonRequest<Messages> jsonRequest) {
        log.info("Inside Problem1Controller: sendMessages(): "+ jsonRequest.getBody().getInputmessages());
        return messageService.postInputMessages(jsonRequest.getBody().getInputmessages());
    }

    @RequestMapping(value = "/ruler", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> whosRuler(@RequestBody JsonRequest<?> jsonRequest) {
        log.info("Inside Problem1Controller: whosRuler(): ");
        return messageService.whosRuler();
    }
}
