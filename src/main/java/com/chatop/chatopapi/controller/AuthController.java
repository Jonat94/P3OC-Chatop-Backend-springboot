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

	@PostMapping(path = "/login")
	public String hello(@RequestBody Credentials cr) {
	return "helloworld";
	}
	
	
	
	@Data
	public static class Credentials {
		private String username = null;
	    private String password = null;
	}
}
