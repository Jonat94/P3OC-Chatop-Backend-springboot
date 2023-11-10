package com.chatop.chatopapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PutRentalException extends RuntimeException {
	
	public PutRentalException(String s){
		super(s);
		System.out.println(s);
	}
	
}
