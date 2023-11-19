package com.chatop.chatopapi.controller;

import java.text.ParseException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.chatopapi.ChatopApiApplication;
import com.chatop.chatopapi.exceptions.FourHundredException;
import com.chatop.chatopapi.exceptions.FourOoneException;
import com.chatop.chatopapi.model.AuthRequest;
import com.chatop.chatopapi.model.TokenResponse;
import com.chatop.chatopapi.model.MessageResponse;
import com.chatop.chatopapi.model.RegisterRequest;
import com.chatop.chatopapi.model.Response;
import com.chatop.chatopapi.model.User;
import com.chatop.chatopapi.model.UserDisplay;
import com.chatop.chatopapi.repository.UserRepository;
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
	private AuthService authService;

	@Autowired
	private UserDisplay userDisplay;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

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
	public UserDisplay currentUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
		User user;

		user = authService.getUserInfos(userDetails.getUsername());
		userDisplay.setId(user.getId());
		userDisplay.setName(user.getName());
		userDisplay.setUsername(user.getEmail());
		userDisplay.setUpdatedAt(ChatopApiApplication.formatDate(user.getUpdatedAt()));
		userDisplay.setCreatedAt(ChatopApiApplication.formatDate(user.getCreatedAt()));

		return userDisplay;
	}

	@PostMapping(path = "/register", produces = "application/json")
	public String registerUser(@RequestBody RegisterRequest registerRequest) {

		if (registerRequest.getName() == null || registerRequest.getEmail() == null
				|| registerRequest.getPassword() == null) {
			throw new FourOoneException("Some data are missing");
		} else {
			try {

				String encodedPassword = bCryptPasswordEncoder.encode(registerRequest.getPassword());
				authService.registerUser(registerRequest.getName(), encodedPassword, registerRequest.getEmail());
			} catch (Exception e) {
				return "{}";
				//throw new FourHundredException("Duplicate entry");
			}
		}

		final UserDetails userDetails = authService.loadUserByUsername(registerRequest.getEmail());

		final String token = jwtTokentUtil.generateToken(userDetails);

		return "{\"token\":\"" + token + "\"}";
	}

	@PostMapping("/login")
	public Response authenticate(@RequestBody AuthRequest jwtRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getLogin(), jwtRequest.getPassword()));

		} catch (BadCredentialsException e) {
			return new MessageResponse("error");
		}
			
		final UserDetails userDetails = authService.loadUserByUsername(jwtRequest.getLogin());

		final String token = jwtTokentUtil.generateToken(userDetails);

		return new TokenResponse(token);
	}
/*
	public String thent(String username, String password) throws Exception {
		/*try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (BadCredentialsException e) {
			throw new Exception("INVALIDE CREDENTIALS", e);
		}*/
	/*	final UserDetails userDetails = authService.loadUserByUsername(username);
		final String token = jwtTokentUtil.generateToken(userDetails);
		return 
	}
*/

	@Data
	public static class Credentials {
		private String username = null;
		private String password = null;
	}
}
