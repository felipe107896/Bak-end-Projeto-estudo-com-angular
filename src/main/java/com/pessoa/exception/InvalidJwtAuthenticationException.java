package com.pessoa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidJwtAuthenticationException extends AuthenticationException {
	
	 public InvalidJwtAuthenticationException(String message) {
	        super(message);
	    }

	    public InvalidJwtAuthenticationException(String message, Throwable cause) {
	        super(message, cause);
	    }
	  
	}
