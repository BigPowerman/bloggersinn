package com.cisco.bloggersInn.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Chats {

	public Chats() {
		
	}
	
	public Chats(String userName, String message) {
		super();
		this.userName = userName;
		this.message = message;
	}
	@Id
	@GeneratedValue
	@Column(name="CHAT_ID")
	protected long id;
	protected String userName;
	protected String message;
	
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
