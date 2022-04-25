package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class employee {
		
	 //A common method to connect to the DB
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "mysql");
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	
	
	public String readEmployees() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect();
	 
	 if (con == null) 
	 {
		 return "Error while connecting to the database for reading."; 
      } 
	 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Employee ID</th><th>Employee Name</th>" +
	 "<th>Employee area</th>" + 
	 "<th>Employee Phone Number</th>" +
	 "<th>Employee email </th></tr>"; 
	 
	 String query = "select * from employee"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String employeeNumber = Integer.toString(rs.getInt("employeeNumber")); 
	 String employeeName = rs.getString("employeeName"); 
	 String employeeArea = rs.getString("employeeArea"); 
	 String employeePnumber = rs.getString("employeePnumber"); 
	 String employeeMail = rs.getString("employeeMail"); 
	 // Add into the html table
	 output += "<tr><td>" + employeeNumber + "</td>"; 
	 output += "<td>" +employeeName + "</td>"; 
	 output += "<td>" + employeeArea + "</td>"; 
	 output += "<td>" + employeePnumber + "</td>";
	 output += "<td>" + employeeMail+ "</td></tr>"; 
	 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
}
