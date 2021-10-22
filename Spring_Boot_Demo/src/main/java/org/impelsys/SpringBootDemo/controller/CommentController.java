package org.impelsys.SpringBootDemo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.impelsys.SpringBootDemo.exception.CommentNotFoundException;
import org.impelsys.SpringBootDemo.model.Comment;
import org.impelsys.SpringBootDemo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //-> combination of @Controller and @ReponseBody
@RequestMapping("/comments") //http://localhost:8080/Messenger/comments/comment/1
public class CommentController {
	@Autowired
	CommentService commentService;
	
	@GetMapping(value="/list",produces="application/json")
	public List<Comment> getcomments(){
		List<Comment> commentsList = commentService.getAllComments();
		return commentsList;
	}
	
	@GetMapping(value="/list1",produces="application/json")
	public ResponseEntity<List<Comment>> getcomments1(){
		List<Comment> commentsList = commentService.getAllComments();
		ResponseEntity<List<Comment>> responseEntity= new ResponseEntity<List<Comment>>(commentsList,HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(value="/comment/{id}",produces="application/json")
	public ResponseEntity<Comment> getComment(@PathVariable int id){
		Comment comment = commentService.getComment(id);
		System.out.println("Int getcomment()");
		if(comment==null)
			throw new CommentNotFoundException(id);
		ResponseEntity<Comment> responseEntity = new ResponseEntity<Comment>(comment,HttpStatus.OK);
		return responseEntity;
	}
	
	@ExceptionHandler(CommentNotFoundException.class)
	public void handlerForCommentsNotFound(HttpServletRequest req, HttpServletResponse resp){
		System.out.println("Will look into this issue");
	}
}
