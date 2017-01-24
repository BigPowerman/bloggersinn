package com.cisco.bloggersInn.data;

import com.cisco.bloggersInn.api.domain.BlogInfo;
import com.cisco.bloggersInn.api.domain.Chats;
import com.cisco.bloggersInn.api.domain.Comment;
import com.cisco.bloggersInn.api.domain.Likes;
import com.cisco.bloggersInn.api.domain.Users;

public interface DAO {

	/*
	 * User Related API
	 */
	public String createUser(Users users);
	public boolean updateUser(Users user);
	public Users findUser(long userId);
	public Users findUserByUserName(String userName);
	public void deleteUser(String userId);
	
	/*
	 * Blog Related API
	 */
	public BlogInfo createBlog(BlogInfo bloginfo);
	public BlogInfo updateBlog(BlogInfo bloginfo);
	public BlogInfo findBlogById(long id);
	public BlogInfo findBlogByHeading(String key);
	public void deleteBlog(long id);
	
	/*
	 * Comment Related API
	 */
	public Comment addComment(Comment comment);
	public Comment editComment(Comment comment);
	public void deleteComment(long id);
	
	/*
	 * Likes Related API
	 */
	public long enableLike(Likes like);
	public Likes findLike(long id);
	public void disableLike(Likes like);	
	
	/*
	 * Chat Related API
	 */
	public long addChat(Chats chat);	
	
}
