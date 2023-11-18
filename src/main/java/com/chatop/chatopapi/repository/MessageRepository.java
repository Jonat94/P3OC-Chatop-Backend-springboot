package com.chatop.chatopapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chatop.chatopapi.model.Message;
@Repository
public interface MessageRepository extends CrudRepository<Message, Long>{

}
