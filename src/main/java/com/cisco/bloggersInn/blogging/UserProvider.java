package com.cisco.bloggersInn.blogging;

import java.util.Set;

import com.cisco.bloggersInn.api.Account;
import com.cisco.bloggersInn.api.domain.Chats;
import com.cisco.bloggersInn.api.domain.Users;
import com.cisco.bloggersInn.api.exception.AccountException;
import com.cisco.bloggersInn.api.exception.ChatException;
import com.cisco.bloggersInn.api.exception.ChatNotFoundException;
import com.cisco.bloggersInn.api.exception.PasswordIncorrectException;
import com.cisco.bloggersInn.api.exception.UserIdExistException;
import com.cisco.bloggersInn.api.exception.UserNotExistException;
import com.cisco.bloggersInn.data.DAO;
import com.cisco.bloggersInn.data.impl.JPADao;

public class UserProvider implements Account{
	private DAO dao= null;

	public UserProvider() {
		// TODO Auto-generated constructor stub
	}

	public Users getAccount(String userName) throws UserNotExistException, AccountException {
		dao = new JPADao();
		Users user = dao.findUserByUserName(userName);
		return user;
	}

	public String createAccount(Users users) throws UserIdExistException, AccountException {
		dao= new JPADao();
		String userId = dao.createUser(users);
		return userId;
	}

	public Users getAccountById(long id) throws UserNotExistException, AccountException {
		dao = new JPADao();
		Users user = dao.findUser(id);
		return user;
	}
	
	public Users login(Users user) throws UserNotExistException, PasswordIncorrectException, AccountException {
		dao = new JPADao();
		Users usr = null;
		try{
			usr = dao.findUserByUserName(user.getUserName());
		}catch(Exception ex){
			throw new AccountException("Authentication Failed");
		}
		if(usr != null ){
			if(usr.getPassword().equals(user.getPassword())){
				return usr;
			}else{
				throw new PasswordIncorrectException("Authentication Failed, please try again");
			}
			
		}else{
			throw new UserNotExistException("User doesn't exist, please register");
		}
	}

	public Users getChats() throws ChatNotFoundException, ChatException {
		// TODO Auto-generated method stub
		return null;
	}

	public Users setChats(Set<Chats> chat) throws ChatNotFoundException, ChatException {
		// TODO Auto-generated method stub
		return null;
	}

}
