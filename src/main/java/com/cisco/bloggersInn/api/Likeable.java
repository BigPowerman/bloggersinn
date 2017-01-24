package com.cisco.bloggersInn.api;

import com.cisco.bloggersInn.api.domain.Likes;
import com.cisco.bloggersInn.api.exception.LikeException;
import com.cisco.bloggersInn.api.exception.LikeNotFoundException;

public interface Likeable {
	public long enableLike(Likes like) 
			throws LikeException;
	
	public Likes findLike(long id)
			throws LikeNotFoundException, LikeException;
	
	public void disableLike(Likes like)
			throws LikeNotFoundException, LikeException;
	
}
