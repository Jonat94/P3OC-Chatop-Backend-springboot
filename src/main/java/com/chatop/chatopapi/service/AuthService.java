package com.chatop.chatopapi.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chatop.chatopapi.repository.UserRepository;

  
@Service
public class AuthService implements UserDetailsService  {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	if(!userRepository.findByEmail(username).isEmpty())
			return new User(
					userRepository.findByEmail(username).get(0).getEmail(), 
					userRepository.findByEmail(username).get(0).getPassword(), 
					new ArrayList<>()
					);
		throw new UsernameNotFoundException(username);
	}

	public com.chatop.chatopapi.model.User getUserInfos(String username) {
		return userRepository.findByEmail(username).get(0);
	}

	public void registerUser(String name, String password, String email) {
		
		com.chatop.chatopapi.model.User user = new com.chatop.chatopapi.model.User();
		user.setName(name);
		user.setPassword(password);
		user.setEmail(email);
		
		userRepository.save(user);
		
		
	}
}
