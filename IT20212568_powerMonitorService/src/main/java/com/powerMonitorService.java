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
}