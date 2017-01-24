package com.cisco.bloggersInn.data.impl;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Hibernate;

import com.cisco.bloggersInn.api.domain.BlogInfo;
import com.cisco.bloggersInn.api.domain.Chats;
import com.cisco.bloggersInn.api.domain.Comment;
import com.cisco.bloggersInn.api.domain.Likes;
import com.cisco.bloggersInn.api.domain.Users;
import com.cisco.bloggersInn.data.DAO;

public class JPADao implements DAO {
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("user");

	public JPADao() {
		// TODO Auto-generated constructor stub
	}

	public String createUser(Users users) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		users.setMyBlogs(null);
		em.persist(users);
		em.getTransaction().commit();
		em.close();
		System.out.println("User got succefully created with Id : " +users.getId());
		return users.getUserName();
	}

	public boolean updateUser(Users user) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	public Users findUser(long userId) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Users user = em.find(Users.class, userId);
		if(user != null){
			System.out.println("User is found using the id : "+user.getId());
			Hibernate.initialize(user.getMyBlogs());
			Hibernate.initialize(user.getMyChat());
		}else{
			System.out.println("User is not found for the id : "+ userId);
		}
		em.close();
		return user;
	}
	
	public Users findUserByUserName(String userName){
		EntityManager em = factory.createEntityManager();
		Users user = (Users)em.createQuery("select usr from Users usr where usr.userName Like :user").setParameter("user", userName).getSingleResult();
		if(user != null){
			System.out.println("User is found using the name :"+ user.getName());
			Hibernate.initialize(user.getMyBlogs());
			Hibernate.initialize(user.getMyChat());
		}else{
			System.out.println("User is not found using the name : "+userName);
		}
		em.close();
		return user;
	}

	public void deleteUser(String userId) {
		// TODO Auto-generated method stub

	}

	public BlogInfo createBlog(BlogInfo bloginfo) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(bloginfo);
		em.getTransaction().commit();
		em.close();
		System.out.println("Blog is created successfully ");
		return bloginfo;
	}

	public BlogInfo updateBlog(BlogInfo bloginfo) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(bloginfo);
		em.getTransaction().commit();
		em.close();
		System.out.println("Blog is Updated successfully ");
		return bloginfo;
	}

	public BlogInfo findBlogById(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		BlogInfo blog = em.find(BlogInfo.class, id);
		if(blog != null){
			System.out.println("Blog is found by the id : "+blog.getId());
		}else{
			System.out.println("Blog is not foudn using the id : "+id);
		}
		em.close();
		return blog;
	}


	public BlogInfo findBlogByHeading(String key) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		BlogInfo blog = (BlogInfo)em.createQuery("select blog from BlogInfo blog where blog.heading Like :key").setParameter("key", key).getSingleResult();
		if(blog != null){
			System.out.println("Blog is found by the heading : "+blog.getHeading());
		}else{
			System.out.println("Blog is not foudn using the id : "+key);
		}
		em.close();
		return blog;
	}

	public void deleteBlog(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		BlogInfo blog = em.find(BlogInfo.class, id);
		em.remove(blog);
		em.getTransaction().commit();
		em.close();
		return;
		
	}

	public Comment addComment(Comment comment) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(comment);
		em.getTransaction().commit();
		em.close();
		System.out.println("Comment is created successfully ");
		return comment;
	}

	public Comment editComment(Comment comment) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(comment);
		em.getTransaction().commit();
		em.close();
		System.out.println("Comment is Updated successfully ");
		return comment;
	}

	public void deleteComment(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Comment comment = em.find(Comment.class, id);
		em.remove(comment);
		em.getTransaction().commit();
		em.close();
	}

	public long enableLike(Likes like) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(like);
		em.getTransaction().commit();
		em.close();
		System.out.println("Comment is Updated successfully ");
		return like.getId();
	}

	public Likes findLike(long id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Likes like = em.find(Likes.class, id);
		em.close();
		return like;
	}
	
	public void disableLike(Likes like) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Likes likes = em.find(Likes.class, like.getId());
		em.remove(likes);
		em.getTransaction().commit();
		em.close();
	}
	
	public long addChat(Chats chat) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(chat);
		em.getTransaction().commit();
		em.close();
		System.out.println("Comment is created successfully ");
		return chat.getId();
	}
	
}
