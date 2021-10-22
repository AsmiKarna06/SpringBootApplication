package org.impelsys.SpringBootDemo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//@Configuration
//@EnableWebSecurity
public class MyAppSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		//DB Connection
		authProvider.setUserDetailsService(userDetailsService);
		//setting the password encoder
//		authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//HTTP basic authentication
	
	/*	http
		.authorizeRequests()
		.antMatchers(HttpMethod.GET,"/Messenger").permitAll()
		.anyRequest().authenticated().and()
		//login configuration
		.formLogin()
//		.loginPage("/login")
		.permitAll();*/
		
		http
		.authorizeRequests()
//		.antMatchers(HttpMethod.GET,"/Messenger").permitAll()
		.antMatchers(HttpMethod.GET,"/Messenger").hasRole("USER")
		.antMatchers(HttpMethod.POST,"/users/user").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/users/remove/*").hasRole("ADMIN")
//		.antMatchers(HttpMethod.GET,"/comments/*").hasRole("ADMIN")
		.anyRequest().authenticated().and()
		.httpBasic().and()
		.formLogin().disable()
		.csrf().disable();
		/*	http
		.httpBasic()
		.and()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST,"/users/user").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/users/delete").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/home").hasRole("USER");
		*/
	}
	
//	@Bean
//	protected UserDetailsService userDetailsService() {
//		
//		List<UserDetails> users = new ArrayList<>();
//		users.add(User.withDefaultPasswordEncoder().username("asmi").password("1234").roles("USER").build());
//		users.add(User.withDefaultPasswordEncoder().username("root").password("1234").roles("USER,ADMIN").build());
//		return new InMemoryUserDetailsManager(users);	
//	}
}
