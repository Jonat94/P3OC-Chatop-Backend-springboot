package com.chatop.chatopapi.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "OWNERS")
public class User {

	public User() {
	}



	public User(Long id, String email, String name, String createdAt, String updatedAt) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	private String name;

	@Column(name = "created_at")
	private String createdAt;

	@Column(name = "updated_at")
	private String updatedAt;

}