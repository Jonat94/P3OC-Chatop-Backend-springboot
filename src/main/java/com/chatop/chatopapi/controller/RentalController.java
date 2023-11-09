package com.chatop.chatopapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.chatopapi.exceptions.RentalsNotFoundException;
import com.chatop.chatopapi.model.Rental;
import com.chatop.chatopapi.service.RentalService;

import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
@RestController
@RequestMapping("/api")
public class RentalController {

	@Autowired
	private RentalService rentalServices;

	/**
	 * Read - Get all Rentals
	 * (Long id, String name, Double price, Double surface, String description, String picture,
			String createdAt, String updatedAt, Long ownerId)
	 * @return - An Iterable object of Rental full filled
	 */
	@ApiOperation(value = "Get all rentals in json format (id, name, surface, price, picture, description, owner_id, created_at")
	
	@GetMapping("/rentals")
	public Iterable<Rental> getRental() {
		Iterable<Rental> rentals = rentalServices.getRentals();
		if (rentals==null) {
			throw new RentalsNotFoundException("rantal list not found for /api/rentals end point");
		}
			return rentals;
	}
}
