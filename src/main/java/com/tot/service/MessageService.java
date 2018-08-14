package com.tot.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {

    List<String> postInputMessages(List<String> inputMessages);
}