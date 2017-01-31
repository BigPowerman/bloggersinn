package com.cisco.bloggersInn.rest;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
	@Path("/update/")
	public Response update(Users users) {
		UserProvider up = new UserProvider();
		boolean status = up.updateAccount(users);
		return Response.ok().entity(status+"").build();
		
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
			 Cookie cookie = new Cookie("userName", user.getUserName());
			 return Response.ok().header("Set-Cookie", cookie+";secure; HttpOnly").expires(new Date()).entity(user).build();
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
	@Path("/getUserByName/{userName}")
	public Response findByUserName(@PathParam("userName") String userName){
		UserProvider up = new UserProvider();
		Users user = up.getAccount(userName);
		return Response.ok().entity(user).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getUserById/{id}")
	public Response find(@PathParam("id") long id){
		UserProvider up = new UserProvider();
		Users user = up.getAccountById(id);
		return Response.ok().entity(user).build();
	}

}
