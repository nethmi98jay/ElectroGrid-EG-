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
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertSupplyDetails(@FormParam("accountNo") String accountNo, 
	 @FormParam("psupplyType") String psupplyType, 
	 @FormParam("psupplyDate") String psupplyDate,
	 @FormParam("psupplyStatus") boolean psupplyStatus,
	 @FormParam("customerID") int customerID)
	
	{ 
	 String output = supObj.insertSupplyDetails(accountNo,psupplyType,psupplyDate,psupplyStatus,customerID); 
	return output; 
	}
	
}

