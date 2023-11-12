package com.chatop.chatopapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.chatopapi.model.User;
import com.chatop.chatopapi.service.RestService;

import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	RestService restServices;
	
	/**
	 * write - Get the credential for identification
	 * 
	 * @param id The id of the rental
	 * @return A string for the response object in json format
	 */
	
	@ApiOperation(value = "User login password verification (param username , password)")

//	@PostMapping(path = "/login")
//	public String validateLogin(@RequestBody Credentials cr) {
////		String username = cr.getUsername();
////		String password = cr.getPassword();
//		return restServices.authentification(cr);
//	}
	
	@PostMapping(path = "/login2")
	public String validateLogin(@RequestBody User user) {
//		String username = cr.getUsername();
//		String password = cr.getPassword();
		return restServices.authentification(user);
	}
	
	
	
	
	@Data
	public static class Credentials {
		private String username = null;
	    private String password = null;
	}
}
