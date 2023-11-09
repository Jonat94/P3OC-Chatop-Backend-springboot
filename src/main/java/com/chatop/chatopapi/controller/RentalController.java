package com.chatop.chatopapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.chatopapi.model.Rental;
import com.chatop.chatopapi.service.RentalService;

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
	@GetMapping("/rentals")
	public Iterable<Rental> getRental() {
		//Rental r = new Rental(1L, "name");
		//Iterable<Rental> i = null;
		System.out.println(rentalServices.getRentals());
		return rentalServices.getRentals();
		//return rentalServices.getRentals();
	}

}
