package com.cisco.bloggersInn.rest;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.derby.tools.sysinfo;

import com.cisco.bloggersInn.api.domain.Users;
import com.cisco.bloggersInn.api.exception.AccountException;
import com.cisco.bloggersInn.api.exception.PasswordIncorrectException;
import com.cisco.bloggersInn.api.exception.UserNotExistException;
import com.cisco.bloggersInn.blogging.UserProvider;

@Path("/user")
public class UserController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add/")
	public Response add(Users users) {
		try{
			UserProvider up = new UserProvider();
			String userName = up.createAccount(users);
			return Response.ok().entity(userName).build();
		}catch(AccountException ex){
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
		}
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update/")
	public Response update(Users users, @QueryParam("sessionId") String sessionId, @QueryParam("user")String userName) {
		UserProvider up = new UserProvider();
		System.out.println("session : " + sessionId);
		System.out.println("username : "+userName);
		if(UserProvider.isValidSession(sessionId, userName)){
			Users usr = up.updateAccount(users);
			return Response.ok().entity(usr).build();
		}else{
			return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid session, please login again").build();
		}
		
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login/")
	public Response login(Users users) {
		UserProvider up = new UserProvider();
		Users user = null;
		try{
			 user = up.login(users);
			 return Response.ok().entity(user).build();
		}catch(UserNotExistException ex){
			System.out.println(ex.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
		}catch(PasswordIncorrectException ex){
			System.out.println(ex.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
		}catch(AccountException ex){
			System.out.println(ex.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
		}
		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/logout/{userName}")
	public Response logout(String userName, @QueryParam("sessionId") String sessionId, @QueryParam("user")String usrnme){
		System.out.println("inside getUserByName: sessionId "+sessionId+ " username " +usrnme);
		UserProvider up = new UserProvider();
		if(UserProvider.isValidSession(sessionId, usrnme)){
			boolean status = up.logout(userName);
			return Response.ok().entity(status+"").build();
		}else{
			return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid session, please login again").build();
		}
		
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getUserByName/{userName}")
	public Response findByUserName(@PathParam("userName") String userName, @QueryParam("sessionId") String sessionId, @QueryParam("user")String usrnme){
		System.out.println("inside getUserByName: sessionId "+sessionId+ " username " +usrnme);
		UserProvider up = new UserProvider();
		if(UserProvider.isValidSession(sessionId, usrnme)){
			Users user = up.getAccount(userName);
			return Response.ok().entity(user).build();
		}else{
			return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid session, please login again").build();
		}
		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getUserById/{id}")
	public Response find(@PathParam("id") long id, @QueryParam("sessionId") String sessionId, @QueryParam("user")String usrnme){
		System.out.println("inside getUserById: sessionId "+sessionId+ " username " +usrnme);
		if(UserProvider.isValidSession(sessionId, usrnme)){
			UserProvider up = new UserProvider();
			Users user = up.getAccountById(id);
			return Response.ok().entity(user).build();
		}else{
			return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid session, please login again").build();
		}
	}

}
