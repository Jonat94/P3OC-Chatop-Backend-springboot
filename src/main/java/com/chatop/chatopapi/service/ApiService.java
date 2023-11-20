package com.chatop.chatopapi.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chatop.chatopapi.model.AppUser;
import com.chatop.chatopapi.model.Message;
import com.chatop.chatopapi.model.Rental;
import com.chatop.chatopapi.repository.MessageRepository;
import com.chatop.chatopapi.repository.RentalRepository;
import com.chatop.chatopapi.repository.UserRepository;
import com.chatop.chatopapi.util.DateUtil;

import lombok.Data;

@Data
@Service
public class ApiService implements UserDetailsService{

	
	@Autowired
	private RentalRepository rentalRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private DateUtil dateUtil;
	
	public AppUser getUser(Long id) {	
		return this.userRepository.findById(id).get();
	}
	
	public Iterable<Rental> getRentals() {
		return rentalRepository.findAll();
	}

	public Rental getRental(Long id) {
		return rentalRepository.findById(id).get();
	}

	public Rental saveRental(Rental rental) {
		rental.setUpdatedAt(dateUtil.getDate());
		rental.setCreatedAt(dateUtil.getDate());
		if (rental.getId() == null)
			return this.rentalRepository.save(rental);
		return null;
	}

	// a modifier eventuellement pour retourner un objet rental
	public Rental updateRental(Rental rental) {
		rental.setUpdatedAt(dateUtil.getDate());		
			Optional<Rental> r= rentalRepository.findById(rental.getId());
			if( !r.isEmpty())
				{
				Rental rent = r.get();
				rent.setId(rental.getId());
				rent.setName(rental.getName());
				rent.setDescription(rental.getDescription());
				rent.setSurface(rental.getSurface());
				rent.setPrice(rental.getPrice());
				rent.setUpdatedAt(dateUtil.getDate());
				return this.rentalRepository.save(rent);
				}
			return null;
	}

	
	
	
	public  void saveMessage(Message message) {
	messageRepository.save(message);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	if(!userRepository.findByEmail(username).isEmpty())
			return new User(
					userRepository.findByEmail(username).get(0).getEmail(), 
					userRepository.findByEmail(username).get(0).getPassword(), 
					new ArrayList<>()
					);
		throw new UsernameNotFoundException(username);
	}

	public AppUser getUserInfos(String username) {
		return userRepository.findByEmail(username).get(0);
	}

	public void registerUser(String name, String password, String email) {
		
		AppUser user = new AppUser();
		user.setName(name);
		user.setPassword(password);
		user.setEmail(email);
		
		userRepository.save(user);
		
		
	}
}
