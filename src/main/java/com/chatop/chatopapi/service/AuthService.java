package com.chatop.chatopapi.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.chatop.chatopapi.repository.UserRepository;
import com.chatop.chatopapi.model.User;



@Service
public class AuthService implements UserDetailsService  {
	 @Autowired
	    private UserRepository userRepository;

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        User user = userRepository.findByUserName(username);
	        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), new ArrayList<>());
	    }

	
	
}
