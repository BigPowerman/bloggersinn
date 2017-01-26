package com.cisco.bloggersInn.api;

import java.util.List;

import com.cisco.bloggersInn.api.domain.BlogInfo;
import com.cisco.bloggersInn.api.exception.BlogException;
import com.cisco.bloggersInn.api.exception.BlogNotFoundException;

public interface Blog {
	public BlogInfo createBlog(BlogInfo bloginfo) 
			throws BlogException;
	
	public BlogInfo updateBlog(BlogInfo bloginfo) 
			throws BlogNotFoundException, BlogException;
	
	public BlogInfo findBlogById(long id) 
			throws BlogNotFoundException, BlogException;
	
	public List<BlogInfo> getAllBlog() throws BlogException;
	
	public List<BlogInfo> findBlogByHeading(String key) throws BlogNotFoundException, BlogException;
	
	public void deleteBlog(String id) 
			throws BlogNotFoundException, BlogException;
}
