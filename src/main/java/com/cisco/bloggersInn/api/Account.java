package com.cisco.bloggersInn.api;

import com.cisco.bloggersInn.api.exception.UserIdExistException;
import com.cisco.bloggersInn.api.exception.UserNotExistException;

import java.util.Set;

import com.cisco.bloggersInn.api.domain.Chats;
import com.cisco.bloggersInn.api.domain.Users;
import com.cisco.bloggersInn.api.exception.AccountException;
import com.cisco.bloggersInn.api.exception.ChatException;
import com.cisco.bloggersInn.api.exception.ChatNotFoundException;

public interface Account {
	public String createAccount(Users user) 
			throws UserIdExistException, AccountException;
	
	public boolean updateAccount(Users user) throws AccountException;
	
	public Users getAccount(String userName) throws UserNotExistException, AccountException;
	
	public Users getAccountById(long id) throws UserNotExistException, AccountException;
	
	public Users login(Users user) throws UserNotExistException, AccountException;
	
}
