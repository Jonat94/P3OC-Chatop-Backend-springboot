package com.chatop.chatopapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.chatopapi.model.Message;
import com.chatop.chatopapi.repository.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	MessageRepository messageRepository;
	
	public  void saveMessage(Message message) {
		System.out.println(message);
	messageRepository.save(message);
	}

	
}
