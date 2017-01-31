package com.cisco.bloggersInn.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cisco.bloggersInn.api.domain.BlogInfo;
import com.cisco.bloggersInn.blogging.BlogBiz;
import com.cisco.bloggersInn.blogging.UserProvider;

@Path("/blog")
public class BlogContoller {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/add/")
	public Response add(BlogInfo bloginfo, @QueryParam("sessionId") String sessionId, @QueryParam("user")String usrnme) {
		if(UserProvider.isValidSession(sessionId, usrnme)){
			BlogBiz addBlog = new BlogBiz();
			BlogInfo blog = addBlog.createBlog(bloginfo);
			return Response.ok().entity(blog).build();
		}else{
			return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid session, please login again").build();
		}
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getBlogById/{id}")
	public Response get(@PathParam("id") long id, @QueryParam("sessionId") String sessionId, @QueryParam("user")String usrnme){
		if(UserProvider.isValidSession(sessionId, usrnme)){
			BlogBiz blogService = new BlogBiz();
			BlogInfo blog = blogService.findBlogById(id);
			return Response.ok().entity(blog).build();
		}else{
			return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid session, please login again").build();
		}
		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllBlogs/")
	public Response get(){
		BlogBiz blogService = new BlogBiz();
		List<BlogInfo> blog = blogService.getAllBlog();
		return Response.ok().entity(blog).build();

	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getBlogByHeading/{heading}")
	public Response get(@PathParam("heading") String key,@QueryParam("sessionId") String sessionId, @QueryParam("user")String usrnme){
		System.out.println("inside getBlogByHeading: sessionId "+sessionId+ " username " +usrnme);
		if(UserProvider.isValidSession(sessionId, usrnme)){
			BlogBiz blogService = new BlogBiz();
			List<BlogInfo> blog = blogService.findBlogByHeading(key);
			return Response.ok().entity(blog).build();
		}else{
			return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid session, please login again").build();
		}
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update/")
	public Response update(BlogInfo blogInfo, @QueryParam("sessionId") String sessionId, @QueryParam("user")String usrnme){
		if(UserProvider.isValidSession(sessionId, usrnme)){
			BlogBiz blogService = new BlogBiz();
			BlogInfo blog = blogService.findBlogById(blogInfo.getId());
			blog.setHeading(blogInfo.getHeading());
			blog.setContent(blogInfo.getContent());
			BlogInfo updatedBlog = blogService.updateBlog(blog);
			return Response.ok().entity(updatedBlog).build();
		}else{
			return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid session, please login again").build();
		}
		
	}
	
}
