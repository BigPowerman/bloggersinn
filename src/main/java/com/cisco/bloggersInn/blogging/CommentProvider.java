package com.cisco.bloggersInn.blogging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.cisco.bloggersInn.api.Commentable;
import com.cisco.bloggersInn.api.domain.BlogInfo;
import com.cisco.bloggersInn.api.domain.Comment;
import com.cisco.bloggersInn.api.exception.CommentException;
import com.cisco.bloggersInn.api.exception.CommentNotFoundException;
import com.cisco.bloggersInn.data.DAO;
import com.cisco.bloggersInn.data.impl.JPADao;

public class CommentProvider implements Commentable {
	private DAO dao= null;

	public Comment createComment(Comment cmt) throws CommentException {
		dao = new JPADao();
		cmt.setPostedDate((new SimpleDateFormat("dd/MM/yy")).format(new Date()));
		Comment comment = dao.addComment(cmt);
		BlogInfo blog = dao.findBlogById(cmt.getBlog());
		Set<Comment> cmtSet = blog.getComments();
		cmtSet.add(cmt);
		blog.setComments(cmtSet);
		dao.updateBlog(blog);
		return comment;
	}

	public Comment updateComment(Comment cmt) throws CommentNotFoundException, CommentException {
		dao = new JPADao();
		Comment comment = dao.editComment(cmt);
		return comment;
	}

//	public Comment findComment(long id) throws CommentNotFoundException, CommentException {
//		dao = new JPADao();
//		Comment cmt = dao.
//	}

	public void deleteComment(long id) throws CommentNotFoundException, CommentException {
		dao = new JPADao();
		dao.deleteComment(id);
	}

}
