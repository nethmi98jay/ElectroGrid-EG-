package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Supply;

@Path("/Supply")
public class SupplyService {
	Supply supObj = new Supply();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readSupply() {
		return supObj.readSupplyDetails();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertSupplyDetails(@FormParam("accountNo") String accountNo,
			@FormParam("psupplyType") String psupplyType, @FormParam("psupplyDate") String psupplyDate,
			@FormParam("psupplyStatus") boolean psupplyStatus, @FormParam("customerID") int customerID)

	{
		String output = supObj.insertSupplyDetails(accountNo, psupplyType, psupplyDate, psupplyStatus, customerID);
		return output;
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateSupplyDetails(String supplyData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject supplyObject = new JsonParser().parse(supplyData).getAsJsonObject(); 
	//Read the values from the JSON object 
	 String psupplyID = supplyObject.get("psupplyID").getAsString(); 
	 String accountNo = supplyObject.get("accountNo").getAsString(); 
	 String psupplyType = supplyObject.get("psupplyType").getAsString(); 
	 String psupplyDate = supplyObject.get("psupplyDate").getAsString(); 
	 String psupplyStatus = supplyObject.get("psupplyStatus").getAsString(); 
	 String customerID = supplyObject.get("customerID").getAsString(); 
	 String output = supObj.updateSupplyDetails(psupplyID,accountNo,psupplyType,psupplyDate,psupplyStatus,customerID);
	return output; 
	}


}
