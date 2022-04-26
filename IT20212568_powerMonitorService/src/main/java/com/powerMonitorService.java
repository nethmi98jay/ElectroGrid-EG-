package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

import model.powerMonitor;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/powerMonitor")
public class powerMonitorService {
	powerMonitor itemObj = new powerMonitor();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPowerMonitor() {
		return itemObj.readPowerMonitor();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPowerMonitor(@FormParam("meterNo") String meterNo, @FormParam("meterReading") String meterReading,
			@FormParam("units") String units, @FormParam("readingDate") String readingDate) {
		String output = itemObj.insertPowerMonitor(meterNo, meterReading, units, readingDate);
		return output;
	}
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePowerMonitor(String itemData) {
//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
//Read the values from the JSON object
		String monitorId = itemObject.get("monitorId").getAsString();
		String meterNo = itemObject.get("meterNo").getAsString();
		String meterReading = itemObject.get("meterReading").getAsString();
		String units = itemObject.get("units").getAsString();
		String readingDate = itemObject.get("readingDate").getAsString();
		String output = itemObj.updatePowerMonitor(monitorId, meterNo, meterReading, units, readingDate);
		return output;
	}
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePowerMonitor(String itemData) {
//Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
//Read the value from the element <monitorId>
		String monitorId = doc.select("monitorId").text();
		String output = itemObj.deletePowerMonitor(monitorId);
		return output;
	}
}