package com.tot.controller;

import com.tot.model.CustomRequest;
import com.tot.model.Messages;
import com.tot.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/p1")
@Slf4j
public class Problem1Controller {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/sendmsgs", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> sendMessages(@RequestBody CustomRequest<Messages> customRequest) {
        log.info("Inside Problem1Controller: sendMessages(): "+ customRequest.getBody().getInputmessages());
        return messageService.postInputMessages(customRequest.getBody().getSender(), customRequest.getBody().getInputmessages());
    }

    @RequestMapping(value = "/whosruler", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<?> whosRuler() {
        log.info("Inside Problem1Controller: whosRuler(): ");
        return new ResponseEntity(messageService.whosRuler(), HttpStatus.OK);
    }

    @RequestMapping(value = "/alliesOfRuler", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> alliesOfRuler(@RequestBody CustomRequest<Messages> customRequest) {
        log.info("Inside Problem1Controller: alliesOfRuler(): ");
        return new ResponseEntity(messageService.alliesOfRuler(customRequest.getBody().getSender()), HttpStatus.OK);
    }
}
