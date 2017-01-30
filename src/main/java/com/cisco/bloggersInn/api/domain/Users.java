package com.cisco.bloggersInn.api.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;

@Entity
public class Users {

	public Users(String name, String userName, String emailId, String password, String securityQuestion,
			String securityAnswer, String interests, Set<BlogInfo> myBlogs, Set<Chats> myChat) {
		super();
		this.name = name;
		this.userName =  userName;
		this.emailId = emailId;
		this.password = password;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.interests = interests;
		this.myBlogs = myBlogs;
		this.myChat = myChat;
	}
	public Users() {

	}

	@Id
	@GeneratedValue
	@Column(name="USER_ID")
	protected long id;
	protected String name;
	@Column(unique=true)
	protected String userName;
	protected String emailId;
	protected String password;
	protected String securityQuestion;
	protected String securityAnswer;
	protected String interests;
    @ElementCollection
	protected Set<Chats> myChat;
	@OneToMany
	protected Set<BlogInfo> myBlogs;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	public String getInterests() {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}
	public Set<BlogInfo> getMyBlogs() {
		return myBlogs;
	}
	public void setMyBlogs(Set<BlogInfo> myBlogs) {
		this.myBlogs = myBlogs;
	}
	public Set<Chats> getMyChat() {
		return myChat;
	}
	public void setMyChat(Set<Chats> myChat) {
		this.myChat = myChat;
	}
	
}
