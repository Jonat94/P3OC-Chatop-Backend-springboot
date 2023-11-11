package com.chatop.chatopapi.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.chatopapi.ChatopApiApplication;
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

	public Rental getRental(Long id) {
		return rentalRepository.findById(id).get();
	}

	public Rental saveRental(Rental rental) {
		rental.setUpdatedAt(ChatopApiApplication.getDate());
		rental.setCreatedAt(ChatopApiApplication.getDate());
		if (rental.getId() == null)
			return this.rentalRepository.save(rental);
		return null;
	}

	// a modifier eventuellement pour retourner un objet rental
	public String updateRental(Rental rental) {
		rental.setUpdatedAt(ChatopApiApplication.getDate());
		Rental r = this.rentalRepository.save(rental);
		return "{\"message\": \"Rental updated !\"}";

	}

}
