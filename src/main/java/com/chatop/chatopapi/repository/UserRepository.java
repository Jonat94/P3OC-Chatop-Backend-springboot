package com.chatop.chatopapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.chatop.chatopapi.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	
	public List<User> findByName(String name);
	
	public List<User> findByEmail(String email);
	
	
}
