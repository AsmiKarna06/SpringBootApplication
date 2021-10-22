package org.impelsys.SpringBootDemo.service;

import org.impelsys.SpringBootDemo.dao.UserLoginRepository;
import org.impelsys.SpringBootDemo.model.UserLogin;
import org.impelsys.SpringBootDemo.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserLoginService implements UserDetailsService {
	@Autowired
	private UserLoginRepository userLoginRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//get username from repository
		UserLogin user=userLoginRepo.findByUsername(username);
		System.out.println(user);
		if(user==null)
			throw new UsernameNotFoundException("Non Existent User");
		
		return new UserPrincipal(user);
	}
}
