package com.mars.exception;

public class PersonNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PersonNotFoundException(Long id) {

	        super(String.format("Person with Id %d not found", id));
	    }
}
