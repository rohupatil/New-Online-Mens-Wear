package com.shopx.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleResourceNotFoundException(NoSuchElementException e) {
		System.out.println("method arg invalid " + e);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getLocalizedMessage());
	}

}
