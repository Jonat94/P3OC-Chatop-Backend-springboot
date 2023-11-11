package com.chatop.chatopapi.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.chatopapi.ChatopApiApplication;
import com.chatop.chatopapi.exceptions.CustomException;
import com.chatop.chatopapi.exceptions.PostRentalException;
import com.chatop.chatopapi.model.Author;
import com.chatop.chatopapi.model.Rental;
import com.chatop.chatopapi.service.AuthorService;


import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data

@RestController
@RequestMapping("/api/auth")
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	
	/**
	 * Read - Get all Authors (Long id, String name, Double price, Double surface,
	 * String description, String picture, String createdAt, String updatedAt, Long
	 * ownerId)
	 * 
	 * @return - An Iterable object of Author full filled
	 */
	@ApiOperation(value = "Get all authors in json format (id, name, surface, price, picture, description, owner_id, created_at")

	@GetMapping("/auth/me")
	public Iterable<Author> getAuthor() {
		Iterable<Author> authors = this.authorService.getAuthors();
		if (authors == null) {
			throw new CustomException("Author list not found Exception: /api/authors end point");
		}
		return authors;
	}
	
	
	/**
	 * Write - Add one rental
	 * 
	 * 
	 * @return A json object containing "Rental created !"
	 */

	
	
	@ApiOperation(value = "Add one rental in json format (id, name, surface, price, picture, description, owner_id, created_at)")
	@PostMapping("/login")
	public String login(@RequestBody Author author) {
		System.out.println("debut login");
		this.authorService.login();
		return "{\"message\": \"error\"}";
	}
	
	
	
	/**
	 * Write - Add one rental
	 * 
	 * 
	 * @return A json object containing "Rental created !"
	 */

	
	@ApiOperation(value = "Add one author in json format (id, name, surface, price, picture, description, owner_id, created_at)")
	@PostMapping("/register")
	
	public String createAuthor(@RequestBody Author author) {
		System.out.println("debut register");
		Author rent = this.authorService.saveAuthor(author);
		if (rent != null)
			return "{\"message\": \"Author created !\"}";
		throw new CustomException("Author Post controller error");
	}
	
	
}
