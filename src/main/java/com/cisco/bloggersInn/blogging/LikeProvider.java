package com.cisco.bloggersInn.blogging;

import java.util.Set;

import com.cisco.bloggersInn.api.Likeable;
import com.cisco.bloggersInn.api.domain.BlogInfo;
import com.cisco.bloggersInn.api.domain.Likes;
import com.cisco.bloggersInn.api.exception.LikeException;
import com.cisco.bloggersInn.api.exception.LikeNotFoundException;
import com.cisco.bloggersInn.data.DAO;
import com.cisco.bloggersInn.data.impl.JPADao;

public class LikeProvider implements Likeable{
	private DAO dao= null;

	public long enableLike(Likes like) throws LikeException {
		dao = new JPADao();
		long likeId = dao.enableLike(like);
		BlogInfo blog = dao.findBlogById(like.getBlog());
		Set<Likes> likes = blog.getLikes();
		likes.add(like);
		blog.setLikes(likes);
		dao.updateBlog(blog);
		return likeId;
	}
	
	public Likes findLike(long id) throws LikeNotFoundException, LikeException {
		dao = new JPADao();
		
		return dao.findLike(id);
	}	

	public void disableLike(Likes like) throws LikeNotFoundException, LikeException {
		dao = new JPADao();

		BlogInfo blog = dao.findBlogById(like.getBlog());
		Set<Likes> likes = blog.getLikes();
		likes.remove(like);
		blog.setLikes(likes);
		dao.updateBlog(blog);
		
		dao.disableLike(like);
		return;
	}

}
