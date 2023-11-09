package com.chatop.chatopapi.model;

import java.sql.Timestamp;

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

	private String price;

	private String surface;

	private String description;

	private String picture;

	private Timestamp created_at;

	private Timestamp updated_at;

	private Long owner_id;

}
