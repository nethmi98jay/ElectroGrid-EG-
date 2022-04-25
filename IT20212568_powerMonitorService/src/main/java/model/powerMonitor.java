package model;

import java.sql.*;

public class powerMonitor {
//A common method to connect to the DB
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

//Provide the correct details: DBServer/DBmeterReading, usermeterReading, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "root12345");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	public String readPowerMonitor() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Meter Number</th><th>Meter Reading</th>" + "<th>Units</th>"
					+ "<th>Reading Date</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from power_monitor";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

// iterate through the rows in the result set
			while (rs.next()) {
				String monitorId = Integer.toString(rs.getInt("monitorId"));
				String meterNo = rs.getString("meterNo");
				String meterReading = rs.getString("meterReading");
				String units = Integer.toString(rs.getInt("units"));
				String readingDate = rs.getString("readingDate");

// Add into the html table
				output += "<tr><td>" + meterNo + "</td>";
				output += "<td>" + meterReading + "</td>";
				output += "<td>" + units + "</td>";
				output += "<td>" + readingDate + "</td>";

// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'" + "class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='PowerMonitor.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'" + "class='btn btn-danger'>"
						+ "<input name='monitorId' type='hidden' value='" + monitorId + "'>" + "</form></td></tr>";
			}

			con.close();

// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading power monitoring details.";
			System.err.println(e.getMessage());
		}

		return output;

	}
}