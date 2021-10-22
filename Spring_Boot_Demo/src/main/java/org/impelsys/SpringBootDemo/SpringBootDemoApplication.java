package org.impelsys.SpringBootDemo;

import org.impelsys.SpringBootDemo.controller.UserController;
import org.impelsys.SpringBootDemo.dao.impl.CommentDaoImpl;
import org.impelsys.SpringBootDemo.exception.handler.ControllerExceptionHandler;
import org.impelsys.SpringBootDemo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAutoConfiguration(exclude= { SecurityAutoConfiguration.class,
		ManagementWebSecurityAutoConfiguration.class
	//	SecurityFilterAutoConfiguration.class
		//,ManagementWebSecurityAutoConfiguration.class
	
})
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass=true)
@EnableJpaRepositories
//@ComponentScan(basePackages={"org.impelsys.controller","org.impelsys.resources","org.impelsys.config"})
@ComponentScan(basePackageClasses= {UserController.class,CommentService.class,CommentDaoImpl.class,ControllerExceptionHandler.class})
public class SpringBootDemoApplication {

	@Autowired
	public static Environment environment;
	
	
	/*
	 * @Autowired static ApplicationContext context;
	 */
	public static void main(String[] args) {
	//	System.setProperty("server.servlet.context-path", "/SpringBootDemo");
	//	System.setProperty("spring.profiles.active","dev");
		ApplicationContext context = new SpringApplicationBuilder(SpringBootDemoApplication.class)
									.profiles("dev").run(args);
				
//		UserController controller = (UserController) context.getBean("commentController");
//		if(controller==null)
			System.out.println("Comment controller not created");
		System.out.println("hello to the world of SpringBoot");
	}
}
