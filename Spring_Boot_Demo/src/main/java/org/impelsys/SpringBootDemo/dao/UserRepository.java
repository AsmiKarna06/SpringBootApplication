package org.impelsys.SpringBootDemo.dao;

import org.impelsys.SpringBootDemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User,Integer>{
	
}
