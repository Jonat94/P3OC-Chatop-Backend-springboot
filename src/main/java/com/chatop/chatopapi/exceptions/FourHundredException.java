package com.chatop.chatopapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FourHundredException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FourHundredException(String s) {
		super(s);
		System.out.println("Custom exception :" + s);
	}

}
