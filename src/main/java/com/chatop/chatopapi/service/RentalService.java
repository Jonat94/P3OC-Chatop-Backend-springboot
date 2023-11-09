package com.chatop.chatopapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.chatopapi.model.Rental;
import com.chatop.chatopapi.repository.RentalRepository;

import lombok.Data;

@Data
@Service
public class RentalService {

	@Autowired
	private RentalRepository rentalRepositories;

	public Iterable<Rental> getRentals() {
		return rentalRepositories.findAll();
	}

}
