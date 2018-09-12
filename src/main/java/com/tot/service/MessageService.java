package com.tot.service;

import com.tot.model.Messages;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

public interface MessageService {

    HashMap<String, List<String>> parseInputMessages(String[] inputMessages);

    ResponseEntity<?> postInputMessages(String sender, String[] inputMessages);

    HashMap<String, Integer> countVotes(List<Messages> msgs);

    String whosRuler();

    String alliesOfRuler(String sender);

}