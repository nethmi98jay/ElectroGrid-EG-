package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


import model.User;

@Path("/User") 
public class UserService
{ 
	User userObj = new User(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readUsers() 
	{ 
		return userObj.readUsers();
	} 
	
	
}
