package com.cisco.bloggersInn.api;
import java.util.Set;

import com.cisco.bloggersInn.api.domain.Chats;
import com.cisco.bloggersInn.api.exception.ChatException;
import com.cisco.bloggersInn.api.exception.ChatNotFoundException;

public interface Chatable {

	public long createChat(Chats chats) 
			throws ChatException;
	
	public Set<Chats> findChat(String receiverUserName)
			throws ChatNotFoundException;
}
