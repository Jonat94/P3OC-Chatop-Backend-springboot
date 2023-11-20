package com.chatop.chatopapi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chatop.chatopapi.model.AppUser;

@Repository
public interface UserRepository extends CrudRepository<AppUser, Long> {
	
	
	public List<AppUser> findByName(String name);
	
	public List<AppUser> findByEmail(String email);

	
	
	
}
