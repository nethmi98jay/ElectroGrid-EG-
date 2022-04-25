package com;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


import model.Supply;

@Path("/Supply") 
public class SupplyService {
Supply supObj = new Supply(); 
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readSupply() 
	{ 
		return supObj.readSupplyDetails();
	} 
	
	
}

