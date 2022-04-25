package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Supply {
	//A common method to connect to the DB
		private Connection connect() 
		 { 
		 Connection con = null; 
		 try
		 { 
		 Class.forName("com.mysql.jdbc.Driver"); 
		 
		 //Provide the correct details: DBServer/DBName, username, password 
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogride", "root", "#Group20");
		 } 
		 catch (Exception e) 
		 {e.printStackTrace();} 
		 return con; 
		 } 
		
		
		public String readSupplyDetails() 
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
		 output = "<table border='1'><tr><th>Power Supply ID</th><th>User's Account No</th>" +
		 "<th>User's Supply Type</th>" + 
		 "<th>User's Suppplied Date </th>" +
		 "<th>User's Supply Status</th>"+
		 "<th>Customer ID</th></tr></tr>"; 
		 
		 String query = "select * from powersupply"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String psupplyID = Integer.toString(rs.getInt("psupplyID")); 
		 String accountNo = rs.getString("accountNo"); 
		 String psupplyType = rs.getString("psupplyType"); 
		 String psupplyDate = rs.getString("psupplyDate"); 
		 String psupplyStatus = Boolean.toString(rs.getBoolean("psupplyStatus"));
		 String customerID = Integer.toString(rs.getInt("customerID")); 
		 // Add into the html table
		 output += "<tr><td>" + psupplyID + "</td>"; 
		 output += "<td>" + accountNo + "</td>"; 
		 output += "<td>" + psupplyType + "</td>"; 
		 output += "<td>" + psupplyDate + "</td>";
		 output += "<td>" + psupplyStatus + "</td>";
		 output += "<td>" + customerID + "</td></tr>"; 
		 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while reading the power supply details."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		
		
}
