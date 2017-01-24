package com.cisco.bloggersInn.api;
import com.cisco.bloggersInn.api.domain.Chats;
import com.cisco.bloggersInn.api.exception.ChatException;

public interface Chatable {

	public long createChat(Chats chats) 
			throws ChatException;

}
