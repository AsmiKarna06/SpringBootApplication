package org.impelsys.SpringBootDemo.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;



//Resource URI  localhost:8080/Messenger/messages/list
@Path("/messages")
@Service
public class MessageResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getMessages() {
		System.out.println("Inside getMessages()");
		return "Hello User";
	}
}
