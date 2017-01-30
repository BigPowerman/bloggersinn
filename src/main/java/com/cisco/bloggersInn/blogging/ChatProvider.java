package com.cisco.bloggersInn.blogging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
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
		Users user = dao.findUserByUserName(chat.getReceiverUserName());
		
		Set<Chats> chatSet = user.getMyChat();
		if(chatSet!=null) {
		chatSet.add(chat);
		} else {
			chatSet = new HashSet<Chats>();
			chatSet.add(chat);
		}
		user.setMyChat(chatSet);
		
		long Id = dao.addChat(chat);
		dao.updateUser(user);
		return Id;
	}

	public Set<Chats> findChat(String userName) throws ChatException {
		dao = new JPADao();
		Users user = dao.findUserByUserName(userName);
		Set<Chats> chatSet = user.getMyChat();
		return chatSet;
	}
}
