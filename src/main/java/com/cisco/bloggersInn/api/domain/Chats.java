package com.cisco.bloggersInn.api.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Chats {

	public Chats() {
		
	}
	
	public Chats(String senderUserName, String receiverUserName, String message, String date) {
		super();
		this.senderUserName = senderUserName;
		this.receiverUserName = receiverUserName;
		this.message = message;
		this.date = date;		
	}
	@Id
	@GeneratedValue
	@Column(name="CHAT_ID")
	protected long id;
	protected String senderUserName;
	protected String receiverUserName;
	protected String message;
	protected String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSenderUserName() {
		return senderUserName;
	}

	public void setSenderUserName(String senderUserName) {
		this.senderUserName = senderUserName;
	}

	public String getReceiverUserName() {
		return receiverUserName;
	}

	public void setReceiverUserName(String receiverUserName) {
		this.receiverUserName = receiverUserName;
	}
	
}
