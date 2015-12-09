package com.azoomee.service.exception;

public class DepartmentAlreadyExistsException extends RuntimeException{
	public DepartmentAlreadyExistsException(final String message){
		super(message);
	}
}
