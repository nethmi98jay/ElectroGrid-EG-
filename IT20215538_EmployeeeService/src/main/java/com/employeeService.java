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
	
}
