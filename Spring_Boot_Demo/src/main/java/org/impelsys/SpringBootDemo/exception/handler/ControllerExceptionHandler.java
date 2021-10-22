package org.impelsys.SpringBootDemo.exception.handler;

import org.impelsys.SpringBootDemo.exception.CommentNotFoundException;
import org.impelsys.SpringBootDemo.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice   //interceptor for exceptions occuring in all controllers
@ResponseBody	//it will automatically convert to json format
public class ControllerExceptionHandler {

	@ExceptionHandler(CommentNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND,reason="unable to find user")
	public String handlerForCommentsNotFound(CommentNotFoundException ce) {
		
		return "Will look into this issue:"+ce.getMessage();
	}
	
	/*
	 * @ExceptionHandler(UserNotFoundException.class)
	 * 
	 * @ResponseStatus(value=HttpStatus.NOT_FOUND,reason="unable to find user")
	 * public ResponseEntity<String> handlerForUserNotFound(UserNotFoundException
	 * ue) { System.out.println("in user not found exception"); String
	 * msg="Will look into this issue:"+ue.getMessage(); return new
	 * ResponseEntity<String>(msg,HttpStatus.NOT_FOUND); }
	 */
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND,reason="unable to find user")
	public String handleAllException(Exception e) {
		System.out.println("in exception");
		return e.getMessage();
	}
}
