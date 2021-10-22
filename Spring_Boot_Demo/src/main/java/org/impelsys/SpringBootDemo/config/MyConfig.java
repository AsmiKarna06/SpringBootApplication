package org.impelsys.SpringBootDemo.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.impelsys.SpringBootDemo.resources.MessageResource;
import org.impelsys.SpringBootDemo.resources.UserResource;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ApplicationPath("/rest")
public class MyConfig extends ResourceConfig{
	
	public MyConfig() {
		register(MessageResource.class);
		//register(UserResource.class);
	}
}
