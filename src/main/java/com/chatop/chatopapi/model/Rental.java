package com.chatop.chatopapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "RENTALS")
public class Rental {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Double price;

	private Double surface;

	private String description;

	private String picture;

	@Column(name = "created_at")
	private String createdAt;

	@Column(name = "updated_at")
	private String updatedAt;

	@Column(name = "owner_id")
	private Long ownerId;
	
	public Rental(){}
	
	public Rental(String name, Double price, Double surface, String description, String picture, Long ownerId) {
		this.name = name;
		this.price = price;
		this.surface = surface;
		this.description = description;
		this.picture = picture;
		this.ownerId = ownerId;
	}
}

