package com.chatop.chatopapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.chatopapi.exceptions.PostRentalException;
import com.chatop.chatopapi.exceptions.PutRentalException;
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
	private RentalService rentalService;

	/**
	 * Read - Get all Rentals (Long id, String name, Double price, Double surface,
	 * String description, String picture, String createdAt, String updatedAt, Long
	 * ownerId)
	 * 
	 * @return - An Iterable object of Rental full filled
	 */
	@ApiOperation(value = "Get all rentals in json format (id, name, surface, price, picture, description, owner_id, created_at")

	@GetMapping("/rentals")
	public Iterable<Rental> getRental() {
		Iterable<Rental> rentals = this.rentalService.getRentals();
		if (rentals == null) {
			throw new RentalsNotFoundException("rantal list not found for /api/rentals end point");
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
		Optional<Rental> rental = this.rentalService.getRental(id);
		if (rental.isPresent()) {
			return rental.get();
		} else {
			throw new RentalsNotFoundException("rantal list not found for /api/rentals end point");
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
	public String createRental(@RequestBody Rental rental) {
		Rental rent = this.rentalService.saveRental(rental);
		if (rent != null)
			return "{\"message\": \"Rental created !\"}";
		throw new PostRentalException("Rantal Post controller error");
	}

	/**
	 * Write - Update one rental
	 * 
	 * @param id The id of the rental
	 * @return A json object containing "Rental created !"
	 * @throws PutRentalException
	 */

	@ApiOperation(value = "Update one rental in json format (id, name, surface, price, picture, description, owner_id, created_at)")

	@PutMapping("/rentals/{id}")
	public String updateRental(@RequestBody Rental rental, @PathVariable("id") final Long id) {
		Rental rent = this.rentalService.updateRental(rental, id);
		if (rent != null)
			return "{\"message\": \"Rental updated !\"}";
		throw new PutRentalException("Rental Put controller error");
	}
}
