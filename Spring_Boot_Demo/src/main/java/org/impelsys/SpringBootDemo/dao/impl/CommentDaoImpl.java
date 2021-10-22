package org.impelsys.SpringBootDemo.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.impelsys.SpringBootDemo.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDaoImpl {
	@Autowired
	SessionFactory sessionFactory;
	public List<Comment> listComments(){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from comment",Comment.class);		
		List<Comment> commentsList = query.list();
		session.close();
		return commentsList;
	}
	
	public Comment getComment(int id) {
		Session session = sessionFactory.openSession();
		Comment comment = session.get(Comment.class, new Integer(id));			
		if(comment!=null)
			System.out.println("Comemnt"+comment);
		session.close();
		return comment;
	}
	

}
