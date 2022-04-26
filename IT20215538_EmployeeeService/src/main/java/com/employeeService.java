package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.employee;

@Path("/employee") 
public class employeeService
{ 
	employee empObj = new employee(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readEmployees() 
	{ 
		return empObj.readEmployees();
	}

	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertEmployee(@FormParam("employeeNumber") String employeeNumber,
	 @FormParam("employeeName") String employeeName,
	 @FormParam("employeeArea") String employeeArea, 
	 @FormParam("employeePnumber") String employeePnumber,
	 @FormParam("employeeMail") String employeeMail) 
	
	
	{ 
	 String output = empObj.insertEmployee(employeeNumber,employeeName,employeeArea,employeePnumber,employeeMail); 
	return output; 
	}
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateEmployee(String empData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject empObject = new JsonParser().parse(empData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String idemployee = empObject.get("idemployee").getAsString();
	 String employeeNumber = empObject.get("employeeNumber").getAsString(); 
	 String employeeName = empObject.get("employeeName").getAsString(); 
	 String employeeArea = empObject.get("employeeArea").getAsString(); 
	 String employeePnumber = empObject.get("employeePnumber").getAsString(); 
	 String employeeMail = empObject.get("employeeMail").getAsString(); 
	 String output = empObj.updateEmployee(idemployee,employeeNumber,employeeName,employeeArea,employeePnumber,employeeMail); 
	return output; 
	}

}
