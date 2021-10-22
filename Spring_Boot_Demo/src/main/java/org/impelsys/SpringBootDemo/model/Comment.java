package org.impelsys.SpringBootDemo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
public class Comment {
	@Id
	@Column(name="comment_id")
	private int commentId;
	@Column
	private String commentContent;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="comment_like",joinColumns={
	@JoinColumn(name="comment_id",referencedColumnName="comment_id")},
	inverseJoinColumns={@JoinColumn(name="likedBy",referencedColumnName="like_id")})
	private List<Likes> likedBy;
	
	
	@ManyToMany(mappedBy="commentList")
	private List<User> userList;


	public int getCommentId() {
		return commentId;
	}


	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}


	public String getCommentContent() {
		return commentContent;
	}


	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}


	public List<Likes> getLikedBy() {
		return likedBy;
	}


	public void setLikedBy(List<Likes> likedBy) {
		this.likedBy = likedBy;
	}


	public List<User> getUserList() {
		return userList;
	}


	public void setUserList(List<User> userList) {
		this.userList = userList;
	}


	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentContent=" + commentContent + ", likedBy=" + likedBy
				+ ", userList=" + userList + "]";
	}

	
	
	
}
