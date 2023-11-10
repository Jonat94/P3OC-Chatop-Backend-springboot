package com.chatop.chatopapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.chatopapi.exceptions.UserNotFoundException;
import com.chatop.chatopapi.model.User;
import com.chatop.chatopapi.service.UserService;


import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	/**
	 * Read - Get one user
	 * 
	 * @param id The id of the user
	 * @return A User object full filled
	 */

	@ApiOperation(value = "Get one user in json format (id, name, surface, price, picture, description, user_id, created_at) with {id} as url parameter")

	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") final Long id) {
		User user = this.userService.getUser(id);
		//System.out.println("_____>" + user);
		if (user.getId() != null) {
			return user;
		} else {
			throw new UserNotFoundException("User not found Exception: /api/user/{i} end point");
		}
	}
}
