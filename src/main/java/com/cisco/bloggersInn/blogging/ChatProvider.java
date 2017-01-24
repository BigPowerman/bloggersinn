package com.cisco.bloggersInn.blogging;

import java.util.Set;

import com.cisco.bloggersInn.api.Chatable;
import com.cisco.bloggersInn.api.domain.Chats;
import com.cisco.bloggersInn.api.domain.Users;
import com.cisco.bloggersInn.api.exception.ChatException;
import com.cisco.bloggersInn.data.DAO;
import com.cisco.bloggersInn.data.impl.JPADao;

public class ChatProvider implements Chatable{
	private DAO dao= null;
	
	public long createChat(Chats chat) throws ChatException {
		dao = new JPADao();
		Users user = dao.findUserByUserName(chat.getUserName());
		Set<Chats> chatSet = user.getMyChat();
		chatSet.add(chat);
		user.setMyChat(chatSet);
		dao.updateUser(user);
		long Id = dao.addChat(chat);
		return Id;
	}
	
}
