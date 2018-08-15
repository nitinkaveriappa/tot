package com.tot.service;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

public interface MessageService {

    HashMap<String, List<String>> parseInputMessages(String[] inputMessages);

    ResponseEntity<?> postInputMessages(String[] inputMessages);

}