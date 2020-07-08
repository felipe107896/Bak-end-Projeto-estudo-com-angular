package com.pessoa.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.pessoa.exception.ExceptionResponse;
import com.pessoa.exception.InvalidJwtAuthenticationException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(InvalidJwtAuthenticationException.class)
	public final ResponseEntity<ExceptionResponse> invalidAuthenticationException(Exception ex, WebRequest request){
		ExceptionResponse response = new ExceptionResponse(new Date(),ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
