package com.ykomarnytskyi2022.exceptions;

public class EntityByIdDoesntExistException extends RuntimeException {

	public EntityByIdDoesntExistException(Long id) {
		super("An Entity by id: %s Does Not exist in the database".formatted(id));
	}
	
}
