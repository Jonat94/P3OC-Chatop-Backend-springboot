package com.chatop.chatopapi.model;

import lombok.Data;

@Data
public class MessageRequest {
	private String message;
	private Long user_id;
	private Long rental_id;
}
