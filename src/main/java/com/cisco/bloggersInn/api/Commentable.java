package com.cisco.bloggersInn.api;

import com.cisco.bloggersInn.api.domain.Comment;
import com.cisco.bloggersInn.api.exception.CommentException;
import com.cisco.bloggersInn.api.exception.CommentNotFoundException;

public interface Commentable {

	public Comment createComment(Comment cmt) 
			throws CommentException;
	
	public Comment updateComment(Comment cmt) 
			throws CommentNotFoundException, CommentException;
	
//	public Comment findComment(long id) 
//			throws CommentNotFoundException, CommentException;
	
	public void deleteComment(long id) 
			throws CommentNotFoundException, CommentException;
}
