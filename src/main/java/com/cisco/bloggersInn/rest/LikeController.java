package com.cisco.bloggersInn.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cisco.bloggersInn.api.domain.Likes;
import com.cisco.bloggersInn.blogging.LikeProvider;

@Path("/like")
public class LikeController {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/enable/")
	public Response add(Likes like){
		LikeProvider likeProvider = new LikeProvider();
		long id = likeProvider.enableLike(like);
		return Response.ok().entity(id+"").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/disable/{id}")
	public Response remove(@PathParam("id") long id){
		LikeProvider likeProvider = new LikeProvider();
		Likes like = likeProvider.findLike(id);
		likeProvider.disableLike(like);
		return Response.ok().entity("true").build();
	}
}
