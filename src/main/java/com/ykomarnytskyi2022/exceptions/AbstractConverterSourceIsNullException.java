package com.ykomarnytskyi2022.exceptions;

public class AbstractConverterSourceIsNullException extends RuntimeException {

	public AbstractConverterSourceIsNullException() {
		super("The source argument in AbstractConverter cannot be null");
	}
	
}
