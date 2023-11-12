package com.chatop.chatopapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.chatop.chatopapi.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> { }
