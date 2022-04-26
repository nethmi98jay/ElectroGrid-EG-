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
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertUser(@FormParam("UserName") String UserName, 
	 @FormParam("UserNIC") String UserNIC, 
	 @FormParam("UserAddress") String UserAddress,
	 @FormParam("UserPhone") String UserPhone) 
	
	{ 
	 String output = userObj.insertUser(UserName,UserNIC,UserAddress,UserPhone); 
	return output; 
	}
	
	
	
}
