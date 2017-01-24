package com.cisco.bloggersInn.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Likes {

	public Likes(){
		
	}
	public Likes(String userName, long blog) {
		super();
		this.userName = userName;
		this.blog = blog;
	}
	@Id
	@GeneratedValue
	@Column(name="LIKE_ID")
	protected long id;
	protected String userName;
	protected long blog;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getBlog() {
		return blog;
	}
	public void setBlog(long blog) {
		this.blog = blog;
	}
	
	
}
