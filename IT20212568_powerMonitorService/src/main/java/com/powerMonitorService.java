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
}