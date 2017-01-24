package com.cisco.bloggersInn.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Comment {

	public Comment(String content, String userName, long blog, String postedDate) {
		super();
		this.content = content;
		this.userName = userName;
		this.blog = blog;
		this.postedDate= postedDate;
	}
	public Comment() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue
	@Column(name="COMMENT_ID")
	protected long id;
	protected String content;
	protected String userName;
	protected long blog;
	protected String postedDate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}
	
}
