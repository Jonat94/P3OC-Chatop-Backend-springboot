package com.chatop.chatopapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chatop.chatopapi.model.Rental;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {

}
