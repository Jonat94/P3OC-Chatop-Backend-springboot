package com.chatop.chatopapi.controller;

import java.text.ParseException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.chatopapi.ChatopApiApplication;
import com.chatop.chatopapi.model.AuthRequest;
import com.chatop.chatopapi.model.AuthResponse;
import com.chatop.chatopapi.model.User;
import com.chatop.chatopapi.model.UserDisplay;
import com.chatop.chatopapi.service.AuthService;
import com.chatop.chatopapi.util.JwtTokenUtil;

import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private JwtTokenUtil jwtTokentUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	AuthService authService;
	
	@Autowired
	UserDisplay userDisplay;
	

	/**
	 * write - Get the credential for identification
	 * 
	 * @param id The id of the rental
	 * @return A string for the response object in json format
	 */

	@ApiOperation(value = "User login password verification (param username , password)")

	@GetMapping(path = "/hello")
	public String hello() {
		return "hello";
	}

	
	@GetMapping(path = "/me")
	public UserDisplay currentUserInfo(@AuthenticationPrincipal UserDetails userDetails) throws ParseException {
		User user;
		
		user = authService.getUserInfos(userDetails.getUsername());
		userDisplay.setId(user.getId());
		userDisplay.setName(user.getName());
		userDisplay.setUsername(user.getEmail());
		userDisplay.setUpdatedAt(ChatopApiApplication.formatDate(user.getUpdatedAt()));
		userDisplay.setCreatedAt(ChatopApiApplication.formatDate(user.getCreatedAt()));
		
		return userDisplay;
	}
	
	
	@PostMapping("/login")
	public AuthResponse authenticate(@RequestBody AuthRequest jwtRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

		} catch (BadCredentialsException e) {
			throw new Exception("INVALIDE CREDENTIALS", e);
		}

		final UserDetails userDetails = authService.loadUserByUsername(jwtRequest.getUsername());

		final String token = new JwtTokenUtil().generateToken(userDetails);

		return new AuthResponse(token);
	}

	@Data
	public static class Credentials {
		private String username = null;
		private String password = null;
	}
}