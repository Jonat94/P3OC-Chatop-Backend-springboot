package com.chatop.chatopapi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

	/*
	public HashMap<String,String> getInfos(String username) {
		List<com.chatop.chatopapi.model.User> l;
		HashMap<String,String> h = new HashMap<String,String>();
		
		l=userRepository.findByEmail(username);
		h.put("id", l.get(0).getId());
		h.put("email", l.get(0).getEmail());
		h.put("name", l.get(0).getName());
		h.put("createdAt", l.get(0).getCreatedAt());
		h.put("updatedAt", l.get(0).getUpdatedAt());
		System.out.println(h);
		return h;
	}*/
}
