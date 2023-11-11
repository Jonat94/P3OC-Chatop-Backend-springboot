package com.chatop.chatopapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chatop.chatopapi.model.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> { }
