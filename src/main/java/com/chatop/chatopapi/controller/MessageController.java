package com.chatop.chatopapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.chatopapi.exceptions.CustomException;
import com.chatop.chatopapi.exceptions.FourHundredException;
import com.chatop.chatopapi.exceptions.FourOoneException;
import com.chatop.chatopapi.model.Message;
import com.chatop.chatopapi.model.MessageRequest;
import com.chatop.chatopapi.model.User;
import com.chatop.chatopapi.service.MessageService;

import lombok.Data;

@Data
@RestController
@RequestMapping("/api")
public class MessageController {

	@Autowired
	MessageService messageService;
	
	@PostMapping("/messages")
	public String createMessage(@RequestBody MessageRequest messageRequest,@RequestHeader(value="Authorization") String authorizationHeader) {
	
		System.out.println(authorizationHeader);
		if(messageRequest.getRental_id() == null ||messageRequest.getRental_id()==null || messageRequest.getMessage()==null)
		   throw new FourHundredException("Some fields are missing");
		if(authorizationHeader == "tesrt")
				throw new FourOoneException("test");
		Message message = new Message();
		message.setRentalId(messageRequest.getRental_id());
		message.setUserId( messageRequest.getUser_id());
		message.setMessage(messageRequest.getMessage());
		System.out.println("fin" + message);
		messageService.saveMessage(message);
		return "{\"message\": \"Message sent with success\"}";
		
	}
	
}
