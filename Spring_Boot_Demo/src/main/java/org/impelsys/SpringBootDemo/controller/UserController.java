package org.impelsys.SpringBootDemo.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.impelsys.SpringBootDemo.exception.CommentNotFoundException;
import org.impelsys.SpringBootDemo.exception.UserNotFoundException;
import org.impelsys.SpringBootDemo.model.User;
import org.impelsys.SpringBootDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //-> combination of @Controller and @ReponseBody
@RequestMapping("/users") //http://localhost:8080/Messenger/comments/comment/1
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping(value="/list1",produces="application/json")
	public ResponseEntity<List<User>> getUsers(){
		List<User> usersList = userService.getAllusers();
		ResponseEntity<List<User>> responseEntity= new ResponseEntity<List<User>>(usersList,HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(value="/user/{id}",produces="application/json")
	public ResponseEntity<User> getUser(@PathVariable int id) {
		User user = userService.getUser(id);
		System.out.println("Int getcomment()");
//		if(user==null)
//			throw new CommentNotFoundException(id);
		ResponseEntity<User> responseEntity = new ResponseEntity<User>(user,HttpStatus.OK);
		return responseEntity;
	}
	
//	@ExceptionHandler(UserNotFoundException.class)
//	public void handlerForCommentsNotFound(HttpServletRequest req, HttpServletResponse resp){
//		System.out.println("Will look into this issue");
//	}
	
	@PostMapping(value="/",consumes="application/json",produces="application/json")
	public User saveUser(User user) {
		return userService.saveUser(user);
	}
	
	@PostMapping(value="/user",consumes=MediaType.APPLICATION_JSON,produces=MediaType.APPLICATION_JSON)
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}

	
}
