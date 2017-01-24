package com.cisco.bloggersInn.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.cisco.bloggersInn.api.domain.Comment;
import com.cisco.bloggersInn.blogging.CommentProvider;

@Path("/comment")
public class CommentController {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add/")
	public Response add(Comment comment){
		CommentProvider cmtProvider = new CommentProvider();
		Comment cmt = cmtProvider.createComment(comment);
		return Response.ok().entity(cmt).build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update/")
	public Response edit(Comment comment){
		CommentProvider cmtProvider = new CommentProvider();
		Comment cmt = cmtProvider.updateComment(comment);
		return Response.ok().entity(cmt).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete/{id}")
	public Response remove(@PathParam("id") long id){
		CommentProvider cmtProvider = new CommentProvider();
		cmtProvider.deleteComment(id);
		return Response.ok().entity("true").build();
	}
}
