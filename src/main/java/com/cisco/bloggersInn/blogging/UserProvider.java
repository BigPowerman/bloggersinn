package com.cisco.bloggersInn.blogging;

import java.security.MessageDigest;
import java.util.Date;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

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
	private static DAO dao= null;

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
		String userId = null;
		try{
			userId  = dao.createUser(users);
		}catch(Exception ex){
			throw new AccountException("Username already exist, please try with a different username");
		}
		
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
			if(usr != null ){
				if(usr.getPassword().equals(user.getPassword())){
					// encryption code
					Date sessionCreatedTime = new Date();
					System.out.println(sessionCreatedTime);
					String sessionString = usr.getId() +"|"+ usr.getUserName() +"|"+ sessionCreatedTime;
					System.out.println(sessionString);
					String hash = DatatypeConverter.printHexBinary( 
					           MessageDigest.getInstance("MD5").digest(sessionString.getBytes("UTF-8")));
					System.out.println(hash);
					usr.setSessionId(hash);
					usr = dao.updateUser(usr);
					return usr;
				}else{
					throw new PasswordIncorrectException("Username or password incorrect, please try again");
				}
				
			}else{
				throw new UserNotExistException("User doesn't exist, please register");
			}
		}catch(Exception ex){
			throw new AccountException("User doesn't exist, please register");
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

	public Users updateAccount(Users user) throws AccountException {
		dao = new JPADao();
		Users usr = dao.updateUser(user);
		return usr;
	}

	public static boolean isValidSession(String sessionId, String userName){
		dao = new JPADao();
		Users usr = dao.findUserByUserName(userName);
		System.out.println("session id "+usr.getSessionId());
		return ((usr.getSessionId()).equals(sessionId));
	}

	public boolean logout(String userName) {
		dao = new JPADao();
		Users usr = dao.findUserByUserName(userName);
		usr.setSessionId(null);
		dao.updateUser(usr);
		return true;
	}
}
