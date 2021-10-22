package org.impelsys.SpringBootDemo.exception;

public class UserNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID=1L;
	
	public UserNotFoundException(int id) {
		super(String.format("User with 5d not found",id));
	}
	
}
