package com.chatop.chatopapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.chatopapi.model.Rental;
import com.chatop.chatopapi.repository.RentalRepository;


import lombok.Data;

@Data
@Service
public class RentalService {

	@Autowired
	private RentalRepository rentalRepository;

	public Iterable<Rental> getRentals() {
		return rentalRepository.findAll();
	}

	public Optional<Rental> getRental(Long id) {
		return rentalRepository.findById(id);
	}
	
	
	

	public Rental saveRental(Rental rental) {
		Rental savedRental = rentalRepository.save(rental);
		return savedRental;
	}

}
