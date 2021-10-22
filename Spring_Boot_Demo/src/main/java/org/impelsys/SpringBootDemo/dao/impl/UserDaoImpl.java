package org.impelsys.SpringBootDemo.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.impelsys.SpringBootDemo.dao.UserDao;
import org.impelsys.SpringBootDemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao{
	
//	HashMap map=new HashMap();
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public int addUser(User user) {
//		long startTime = System.currentTimeMillis();
//		System.out.println("In AddUser():"+user);
		int row;
		Session session=sessionFactory.openSession();
		System.out.println("saving");
		Transaction tx = session.beginTransaction();
		row=(Integer)session.save(user);
		System.out.println("Created user with id"+row);
		tx.commit();
		session.close();
//		System.out.println("Existing AddUser() with return value"+row);
//		long endTime = System.currentTimeMillis();
//		System.out.println("Time for execution:"+ (endTime-startTime) );
		return row;
	}
	@Override
	public void deleteUser(int id) {
		Session session=sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		User user = new User();
		user.setId(id);
		session.delete(user);
		tx.commit();
		session.close();
	}
	
	@Override
	public User viewUser(int id,Session session) {
		// TODO Auto-generated method stub
		//Session session = sessionFactory.openSession();
		User user = session.get(User.class,new Integer(id));
//		session.close();
		System.out.println(user);
		return user;
	}
	@Override
	public List<User> listUsers() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		List<User> userList =session.createQuery("from User").list();
//		userList=new ArrayList<User>();
//		userList=(List<User>)map.values();
		session.close();
		return userList;
	}
	@Override
	public User viewUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
