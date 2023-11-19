package com.chatop.chatopapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RegisterRequest {
	private String name;
	private String email;
	private String password;
}
