package org.impelsys.SpringBootDemo.service;

import java.util.ArrayList;
import java.util.List;

import org.impelsys.SpringBootDemo.dao.UserRepository;
import org.impelsys.SpringBootDemo.exception.UserNotFoundException;
import org.impelsys.SpringBootDemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public List<User> getAllusers()
	{
		List<User> userList = new ArrayList();
		userRepository.findAll().forEach(userList::add);
		return userList;
	}
	
	public User getUser(int id){
		try {
			User user= userRepository.findById(id)
					.orElseThrow(()->new UserNotFoundException(id));
			return user; 
		}catch(UserNotFoundException e) 
		{
			System.out.println("in catch");
			throw (e);
			
		}
			
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public User addUser(User user) {
		return userRepository.save(user);
	}
	

}
