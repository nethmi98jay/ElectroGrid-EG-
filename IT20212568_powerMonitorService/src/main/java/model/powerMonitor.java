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
	public String insertPowerMonitor(String meterNo, String meterReading, String units, String readingDate) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

// create a prepared statement
			String query = " insert into power_monitor " + "(`monitorId`,`meterNo`,`meterReading`,`units`,`readingDate`)"
					+ " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, meterNo);
			preparedStmt.setString(3, meterReading);
			preparedStmt.setInt(4, Integer.parseInt(units));
			preparedStmt.setString(5, readingDate);

// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting Power monitoring details.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	public String updatePowerMonitor(String monitorId, String meterNo, String meterReading, String units, String readingDate) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

// create a prepared statement
			String query = "UPDATE power_monitor SET meterNo=?,meterReading=?,units=?,readingDate=?" + "WHERE monitorId=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

// binding values
			preparedStmt.setString(1, meterNo);
			preparedStmt.setString(2, meterReading);
			preparedStmt.setInt(3, Integer.parseInt(units));
			preparedStmt.setString(4, readingDate);
			preparedStmt.setInt(5, Integer.parseInt(monitorId));

// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating power monitoring details.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	public String deletePowerMonitor(String monitorId) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

// create a prepared statement
			String query = "delete from power_monitor where monitorId=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

// binding values
			preparedStmt.setInt(1, Integer.parseInt(monitorId));

// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting power monitoring details."; 
			System.err.println(e.getMessage());
		}

		return output;

	}
}