package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.User;

@Path("/User")
public class UserService {
	User userObj = new User();

	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUsers() {
		return userObj.readUsers();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(@FormParam("UserName") String UserName, @FormParam("UserNIC") String UserNIC,
			@FormParam("UserAddress") String UserAddress, @FormParam("UserPhone") String UserPhone)

	{
		String output = userObj.insertUser(UserName, UserNIC, UserAddress, UserPhone);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String userData) {
		// Convert the input string to a JSON object
		JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
		// Read the values from the JSON object
		String UserID = userObject.get("UserID").getAsString();
		String UserName = userObject.get("UserName").getAsString();
		String UserNIC = userObject.get("UserNIC").getAsString();
		String UserAddress = userObject.get("UserAddress").getAsString();
		String UserPhone = userObject.get("UserPhone").getAsString();
		String output = userObj.updateUser(UserID, UserName, UserNIC, UserAddress, UserPhone);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteUser(String userData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(userData, "", Parser.xmlParser());
		
		//Read the value from the element <serviceID>
		String UserID = doc.select("UserID").text();
		String output =userObj.deleteUser(UserID);
		
		return output;
	}
}
