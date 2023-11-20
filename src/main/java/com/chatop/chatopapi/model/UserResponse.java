package com.chatop.chatopapi.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class UserResponse {
	private long Id;
	private String username;
	private String name;
	private String createdAt;
	private String updatedAt;

}
