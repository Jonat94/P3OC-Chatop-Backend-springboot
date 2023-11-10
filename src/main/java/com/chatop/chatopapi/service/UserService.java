package com.chatop.chatopapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.chatopapi.model.User;
import com.chatop.chatopapi.repository.UserRepository;

@Service
public class UserService {
	

	@Autowired
	private UserRepository userRepository;

	public User getUser(Long id) {
		
		return this.userRepository.findById(id).get();
	}
	
	
	
	
	

}
