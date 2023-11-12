package com.chatop.chatopapi.controller;

import java.util.Map;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.chatopapi.exceptions.CustomException;
import com.chatop.chatopapi.model.Rental;
import com.chatop.chatopapi.model.User;
import com.chatop.chatopapi.service.RestService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.Data;

@Data

@RestController
@RequestMapping("/api")
public class RestControl {

	@Autowired
	private RestService restService;

	/**
	 * Read - Get one user
	 * 
	 * @param id The id of the user
	 * @return A User object full filled
	 */

	@ApiOperation(value = "Get one user in json format (id, name, surface, price, picture, description, user_id, created_at) with {id} as url parameter")

	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") final Long id) {
		User user = this.restService.getUser(id);
		// System.out.println("_____>" + user);
		if (user.getId() != null) {
			return user;
		} else {
			throw new CustomException("User not found Exception: /api/user/{i} end point");
		}
	}

	/**
	 * Read - Get all Rentals (Long id, String name, Double price, Double surface,
	 * String description, String picture, String createdAt, String updatedAt, Long
	 * ownerId)
	 * 
	 * @return - An Iterable object of Rental full filled
	 */
	@ApiOperation(value = "Get all rentals in json format (id, name, surface, price, picture, description, owner_id, created_at")

	@GetMapping("/rentals")
	public Iterable<Rental> getRentals() {
		Iterable<Rental> rentals = this.restService.getRentals();
		if (rentals == null) {
			throw new CustomException("Rental list not found Exception: /api/rentals end point");
		}
		return rentals;
	}

	/**
	 * Read - Get one rental
	 * 
	 * @param id The id of the rental
	 * @return A Rental object full filled
	 */

	@ApiOperation(value = "Get one rental in json format (id, name, surface, price, picture, description, owner_id, created_at) with {id} as url parameter")

	@GetMapping("/rentals/{id}")
	public Rental getRental(@PathVariable("id") final Long id) {
		Rental rental = this.restService.getRental(id);
		// System.out.println("_____>" + rental);
		if (rental.getId() != null) {
			return rental;
		} else {
			throw new CustomException("Rental not found Exception: /api/rentals/{i} end point");
		}
	}

	/**
	 * Write - Add one rental
	 * 
	 * 
	 * @return A json object containing "Rental created !"
	 */

	@ApiOperation(value = "Add one rental in json format (id, name, surface, price, picture, description, owner_id, created_at)")
	@PostMapping("/rentals")
	public String createRental(@Param("name") String name, @Param("surface") Double surface,
			@Param("price") Double price, @Param("picture") MultipartFile picture,
			@Param("description") String description) {

		String picname = picture.getOriginalFilename().isEmpty() ? null : picture.getOriginalFilename();

		Rental r = new Rental(name, price, surface, description, picname, 1L);

		Rental rent = this.restService.saveRental(r);

		if (rent != null)
			return "{\"message\": \"Rental created !\"}";
		throw new CustomException("Rental Post controller error");
	}

	/**
	 * Write - Update one rental
	 * 
	 * @param id The id of the rental
	 * @return A json object containing "Rental created !"
	 * @throws PutRentalException
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
		
		Rental rent = this.restService.updateRental(r);

		if (rent != null)
			return "{\"message\": \"Rental updated !\"}";
		throw new CustomException("Rental put controller error, rental update fail");
	}

	/**
	 * Read - Get all Authors (Long id, String name, Double price, Double surface,
	 * String description, String picture, String createdAt, String updatedAt, Long
	 * ownerId)
	 * 
	 * @return - An Iterable object of Author full filled
	 */
	@ApiOperation(value = "Get all authors in json format (id, name, surface, price, picture, description, owner_id, created_at")

	@GetMapping("/me")
	public User getUser() {
		User user = this.restService.getUser(null);
		if (user == null) {
			throw new CustomException("Author list not found Exception: /api/authors end point");
		}
		return user;
	}

	/**
	 * Write - Add one rental
	 * 
	 * 
	 * @return A json object containing "Rental created !"
	 */

//	@PostMapping("/auth/login")
//	@ResponseBody
//	public String login(@RequestBody Credentials c) {
//		System.out.println("param values : " + c);
//		//this.restService.login("log", "pass");
//		return "{\"message\": \"error\"}";
//	
//	
//	}
	
//	@ResponseBody
//	@PostMapping("/somepath")
//	public String doSomeThing(@RequestBody LoginObject lo){
//		System.out.println("aa:"+ lo.getUsername());
//	    return "Parameters are " ;
//	}
	
	
	
	 
	 
	
	/**
	 * Write - Add one rental
	 * 
	 * 
	 * @return A json object containing "Rental created !"
	 */

	/*
	 * @ApiOperation(value =
	 * "Add one author in json format (id, name, surface, price, picture, description, owner_id, created_at)"
	 * )
	 * 
	 * @PostMapping("/register")
	 * 
	 * public String createAuthor(@RequestBody Author author) {
	 * System.out.println("debut register"); Author rent =
	 * this.authorService.saveAuthor(author); if (rent != null) return
	 * "{\"message\": \"Author created !\"}"; throw new
	 * CustomException("Author Post controller error"); }
	 */

}
