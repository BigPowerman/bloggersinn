package com.cisco.bloggersInn.blogging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.cisco.bloggersInn.api.Blog;
import com.cisco.bloggersInn.api.domain.BlogInfo;
import com.cisco.bloggersInn.api.domain.Users;
import com.cisco.bloggersInn.api.exception.BlogException;
import com.cisco.bloggersInn.api.exception.BlogNotFoundException;
import com.cisco.bloggersInn.data.DAO;
import com.cisco.bloggersInn.data.impl.JPADao;

public class BlogBiz implements Blog {

	private DAO dao= null;
	
	public BlogInfo createBlog(BlogInfo blog) throws BlogException {
		dao= new JPADao();
		blog.setPostedDate((new SimpleDateFormat("dd/MM/yy")).format(new Date()));
		BlogInfo blogInfo = dao.createBlog(blog);
		Users user = dao.findUserByUserName(blog.getUserName());
		Set<BlogInfo> tempBlog = user.getMyBlogs();
		tempBlog.add(blog);
		dao.updateUser(user);
		return blogInfo;
	}

	public BlogInfo updateBlog(BlogInfo blog) throws BlogNotFoundException, BlogException {
		dao= new JPADao();
		blog.setPostedDate((new SimpleDateFormat("dd/MM/yy")).format(new Date()));
		BlogInfo blogInfo= dao.updateBlog(blog);
		return blogInfo;
	}

	public BlogInfo findBlogById(long id) throws BlogNotFoundException, BlogException {
		dao= new JPADao();
		BlogInfo blog = dao.findBlogById(id);
		return blog;
	}
	
	public List<BlogInfo> findBlogByHeading(String key) throws BlogNotFoundException, BlogException {
		dao= new JPADao();
		List<BlogInfo> blog = dao.findBlogByHeading(key);
		return blog;
	}

	public void deleteBlog(String Id) throws BlogNotFoundException, BlogException {
		dao= new JPADao();

	}

	public List<BlogInfo> getAllBlog() throws BlogException {
		dao= new JPADao();
		List<BlogInfo> blog = dao.getAllBlog();
		return blog;
	}

}
