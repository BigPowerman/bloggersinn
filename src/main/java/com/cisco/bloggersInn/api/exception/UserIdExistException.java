package com.cisco.bloggersInn.api.exception;

@SuppressWarnings("serial")
public class UserIdExistException extends AccountException {

	public UserIdExistException() {
		// TODO Auto-generated constructor stub
	}

	public UserIdExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserIdExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public UserIdExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserIdExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
