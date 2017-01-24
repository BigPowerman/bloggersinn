package com.cisco.bloggersInn.api.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;

@Entity
public class BlogInfo {
	
	public BlogInfo(String heading, String content, Set<Likes> likes, Set<Comment> comments, String userName,
			String postedDate) {
		super();
		this.heading = heading;
		this.content = content;
		this.likes = likes;
		this.comments = comments;
		this.userName = userName;
		this.postedDate = postedDate;
	}

	public BlogInfo() {
		
	}

	@Id
	@GeneratedValue
	@Column(name="BLOG_ID")
	protected long id;
	protected String heading;
	protected String content;
	@OneToMany(fetch=FetchType.EAGER)
	protected Set<Likes> likes; 
	@OneToMany(fetch=FetchType.EAGER)
	protected Set<Comment> comments; 
	protected String userName;
	protected String postedDate;

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
	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public Set<Likes> getLikes() {
		return likes;
	}

	public void setLikes(Set<Likes> likes) {
		this.likes = likes;
	}
	
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public String getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}
	
	
	
}
