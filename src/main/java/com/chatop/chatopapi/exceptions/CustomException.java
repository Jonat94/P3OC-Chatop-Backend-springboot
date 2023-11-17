package com.chatop.chatopapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomException(String s) {
		super(s);
		System.out.println("Custom exception :" + s);
	}

}
