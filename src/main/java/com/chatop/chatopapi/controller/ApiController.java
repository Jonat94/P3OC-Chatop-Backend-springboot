package com.chatop.chatopapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.chatopapi.exceptions.CustomException;
import com.chatop.chatopapi.exceptions.FourHundredException;
import com.chatop.chatopapi.exceptions.FourOoneException;
import com.chatop.chatopapi.model.AppUser;
import com.chatop.chatopapi.model.AuthRequest;
import com.chatop.chatopapi.model.Message;
import com.chatop.chatopapi.model.MessageRequest;
import com.chatop.chatopapi.model.RegisterRequest;
import com.chatop.chatopapi.model.Rental;
import com.chatop.chatopapi.model.UserResponse;
import com.chatop.chatopapi.service.ApiService;
import com.chatop.chatopapi.util.DateUtil;
import com.chatop.chatopapi.util.JwtTokenUtil;

import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private ApiService apiService;
	
	@Autowired
	private JwtTokenUtil jwtTokentUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserResponse userResponse;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private  DateUtil dateUtil;


	/**
	 * Read - Get one user
	 * 
	 * @param id The id of the user
	 * @return A User object full filled
	 * @throws CustomException
	 */

	@ApiOperation(value = "Get one user in json format (id, name, surface, price, picture, description, user_id, created_at) with {id} as url parameter")

	@GetMapping("/user/{id}")
	public AppUser getUser(@PathVariable("id") final Long id) {
		AppUser appUser = this.apiService.getUser(id);
		if (appUser.getId() != null) {
			return appUser;
		} else {
			throw new CustomException("User not found Exception: /api/user/{i} end point");
		}
	}

	/**
	 * Read - Get all Rentals (Long id, String name, Double price, Double surface,
	 * String description, String picture, String createdAt, String updatedAt, Long
	 * ownerId)
	 * @return - An Iterable object of Rental full filled
	 */
	@ApiOperation(value = "Get all rentals in json format (id, name, surface, price, picture, description, owner_id, created_at")

	@GetMapping("/rentals")
	public Iterable<Rental> getRentals() {
		Iterable<Rental> rentals = this.apiService.getRentals();
		if (rentals == null) {
			throw new CustomException("Rental list not found Exception: /api/rentals end point");
		}
		return rentals;
	}

	/**
	 * Read - Get one rental
	 * @param id The id of the rental
	 * @return A Rental object full filled
	 * @throws CustomException
	 */

	@ApiOperation(value = "Get one rental in json format (id, name, surface, price, picture, description, owner_id, created_at) with {id} as url parameter")

	@GetMapping("/rentals/{id}")
	public Rental getRental(@PathVariable("id") final Long id) {
		Rental rental = this.apiService.getRental(id);
		if (rental.getId() != null) {
			return rental;
		} else {
			throw new CustomException("Rental not found Exception: /api/rentals/{i} end point");
		}
	}

	/**
	 * Write - Add one rental
	 * @param name, surface, price, picture, description
	 * @return A json object containing "Rental created !"
	 * @throws CustomException
	 */

	@ApiOperation(value = "Add one rental in json format (id, name, surface, price, picture, description, owner_id, created_at)")
	@PostMapping("/rentals")
	public String createRental(@Param("name") String name, @Param("surface") Double surface,
			@Param("price") Double price, @Param("picture") MultipartFile picture,
			@Param("description") String description) {

		String picname = picture.getOriginalFilename().isEmpty() ? null : picture.getOriginalFilename();

		Rental r = new Rental(name, price, surface, description, picname, 1L);

		Rental rent = this.apiService.saveRental(r);

		if (rent != null)
			return "{\"message\": \"Rental created !\"}";
		throw new CustomException("Rental Post controller error");
	}

	/**
	 * Write - Update one rental
	 * 
	 * @param id The id of the rental
	 * @return A json object containing "Rental created !"
	 * @throws CustomException
	 */

	@ApiOperation(value = "Update one rental in json format (id, name, surface, price, picture, description, owner_id, created_at) id and name are mandatory.")

	@PutMapping("/rentals/{id}")
	public String updateRental(@Param("name") String name, @Param("surface") Double surface,
			@Param("price") Double price, @Param("description") String description, @PathVariable("id") final Long id) {

		Rental r = new Rental();
		r.setId(id);
		r.setName(name);
		r.setDescription(description);
		r.setSurface(surface);
		r.setPrice(price);

		Rental rent = this.apiService.updateRental(r);

		if (rent != null)
			return "{\"message\": \"Rental updated !\"}";
		throw new CustomException("Rental put controller error, rental update fail");
	}


	
	/**
	 * read - Get informations on the current user
	 * @param Connected user credentials
	 * @return A User Response object containing the informations
	 */

	@ApiOperation(value = "Return informations on the curent user")

	@GetMapping(path = "/auth/me")
	public UserResponse currentUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
		AppUser appUser;

		appUser = apiService.getUserInfos(userDetails.getUsername());
		userResponse.setId(appUser.getId());
		userResponse.setName(appUser.getName());
		userResponse.setUsername(appUser.getEmail());
		userResponse.setUpdatedAt(dateUtil.formatDate(appUser.getUpdatedAt()));
		userResponse.setCreatedAt(dateUtil.formatDate(appUser.getCreatedAt()));

		return userResponse;
	}

	
	/**
	 * write - Register one user in the db.
	 * @param name ,email , password of the user.
	 * @return A jwt in a json object corresponding to this user.
	 * @throws FourOone Exception
	 */


	@ApiOperation(value = "Register a user with his name email and password.")

	@PostMapping(path = "/auth/register", produces = "application/json")
	public String registerUser(@RequestBody RegisterRequest registerRequest) {

		if (registerRequest.getName() == null || registerRequest.getEmail() == null
				|| registerRequest.getPassword() == null) {
			throw new FourOoneException("Some data are missing");
		} else {
			try {

				String encodedPassword = bCryptPasswordEncoder.encode(registerRequest.getPassword());
				apiService.registerUser(registerRequest.getName(), encodedPassword, registerRequest.getEmail());
			} catch (Exception e) {
				return "{}";
			}
		}

		final UserDetails userDetails = apiService.loadUserByUsername(registerRequest.getEmail());

		final String token = jwtTokentUtil.generateToken(userDetails);

		return "{\"token\":\"" + token + "\"}";
	}

	
	/**
	 * read - Authenticate a user and respond with a jwt
	 * 
	 * @param email , password of the user.
	 * @return A jwt in a json object corresponding to this user.
	 */

	@ApiOperation(value = "Authenticate a user with his email and password and return a json object containing a jwt")
	
	@PostMapping("/auth/login")
	public String authenticate(@RequestBody AuthRequest jwtRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getLogin(), jwtRequest.getPassword()));

		} catch (BadCredentialsException e) {
			return "{\"message\":\"error\"}";
		}
			
		final UserDetails userDetails = apiService.loadUserByUsername(jwtRequest.getLogin());
		
		final String token = jwtTokentUtil.generateToken(userDetails);

		return "{\"token\":\"" + token + "\"}";
	}
	

	
	/**
	 * write - Save a text message in the db with the user Id and rental Id
	 * 
	 * @param  a String message, long user_id, long rental_id in json format.
	 * @return A a json success message or a bad request.
	 */


	@ApiOperation(value = "Save a message with his user id and rental id")
	
	@PostMapping("/messages")
	public String createMessage(@RequestBody MessageRequest messageRequest) {
			if(messageRequest.getRental_id()==null|| messageRequest.getUser_id()==null||messageRequest.getMessage()==null)
				throw new FourHundredException("Paramètre non valide");
		Message message = new Message();
		message.setRentalId(messageRequest.getRental_id());
		message.setUserId( messageRequest.getUser_id());
		message.setMessage(messageRequest.getMessage());
		apiService.saveMessage(message);
			
		return "{\"message\": \"Message sent with success\"}";
		
	}
	

}
