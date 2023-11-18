package com.chatop.chatopapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatop.chatopapi.ChatopApiApplication;
import com.chatop.chatopapi.controller.AuthController.Credentials;
import com.chatop.chatopapi.model.Rental;
import com.chatop.chatopapi.model.User;
import com.chatop.chatopapi.repository.RentalRepository;
import com.chatop.chatopapi.repository.UserRepository;

import lombok.Data;

@Data
@Service
public class RestService {

	
	@Autowired
	private RentalRepository rentalRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	/*
	public String login(String user, String password) {
		 return "{token : jwt}";		 
	 }
	 */
	public User getUser(Long id) {	
		return this.userRepository.findById(id).get();
	}
	
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
	public Rental updateRental(Rental rental) {
		rental.setUpdatedAt(ChatopApiApplication.getDate());		
			Optional<Rental> r= rentalRepository.findById(rental.getId());
			if( !r.isEmpty())
				{
				Rental rent = r.get();
				rent.setId(rental.getId());
				rent.setName(rental.getName());
				rent.setDescription(rental.getDescription());
				rent.setSurface(rental.getSurface());
				rent.setPrice(rental.getPrice());
				rent.setUpdatedAt(ChatopApiApplication.getDate());
				return this.rentalRepository.save(rent);
				}
			return null;
	}

	
	
	public String authentification(User user) {
		if(user.getEmail().equals("test@test.com") && user.getPassword().equals("test!31") )
			return "{\"token\" : \"jwt\"}"; 
		return "{\"message\" : \"error\"}";
	}
}
