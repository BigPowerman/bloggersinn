package com.cisco.bloggersInn.rest;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.cisco.bloggersInn.api.domain.Chats;
import com.cisco.bloggersInn.blogging.ChatProvider;

@Path("/chat")
public class ChatController {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add/")
	public Response add(Chats chat){
		ChatProvider chatProvider = new ChatProvider();
		long ch = chatProvider.createChat(chat);
		return Response.ok().entity(ch).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get/{userName}")
	public Response remove(@PathParam("userName") String userName){
		ChatProvider chatProvider = new ChatProvider();
		Set<Chats> chats = chatProvider.findChat(userName);
		return Response.ok().entity(chats).build();
	}
	
}
