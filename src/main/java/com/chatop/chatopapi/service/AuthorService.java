package com.chatop.chatopapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.chatopapi.ChatopApiApplication;
import com.chatop.chatopapi.model.Author;
import com.chatop.chatopapi.model.Rental;
import com.chatop.chatopapi.repository.AuthorRepository;

@Service
public class AuthorService {
	

	@Autowired
	private AuthorRepository authorRepository;

	public Author getAuthor(Long id) {
		
		return this.authorRepository.findById(id).get();
	}	
	
	public Iterable<Author> getAuthors() {
		return authorRepository.findAll();
	}
	

	public Author saveAuthor(Author author) {
		author.setUpdatedAt(ChatopApiApplication.getDate());
		author.setCreatedAt(ChatopApiApplication.getDate());
		if (author.getId() == null)
			return this.authorRepository.save(author);
		return null;
	}
	 public boolean login() {
		 return true;
		 
	 }
	

}
