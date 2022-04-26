package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class User {
		
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
	
	
	public String readUsers() 
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
	 output = "<table border='1'><tr><th>User ID</th><th>User Name</th>" +
	 "<th>User NIC</th>" + 
	 "<th>User Address</th>" +
	 "<th>User Phone </th></tr>"; 
	 
	 String query = "select * from user"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String UserID = Integer.toString(rs.getInt("UserID")); 
	 String UserName = rs.getString("UserName"); 
	 String UserNIC = rs.getString("UserNIC"); 
	 String UserAddress = rs.getString("UserAddress"); 
	 String UserPhone = rs.getString("UserPhone"); 
	 // Add into the html table
	 output += "<tr><td>" + UserID + "</td>"; 
	 output += "<td>" + UserName + "</td>"; 
	 output += "<td>" + UserNIC + "</td>"; 
	 output += "<td>" + UserAddress + "</td>";
	 output += "<td>" + UserPhone + "</td></tr>"; 
	 
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
	
	//Insert Users
		public String insertUser(String userName, String userNIC, String userAddress, String userPhone) 
		 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for inserting."; } 
			 // create a prepared statement
			 String query = "insert into user (`UserID`,`UserName`,`UserNIC`,`UserAddress`,`UserPhone`) values (?, ?, ?, ?, ?)"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values 
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, userName); 
			 preparedStmt.setString(3, userNIC); 
			 preparedStmt.setString(4, userAddress); 
			 preparedStmt.setString(5, userPhone); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "User inserted successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while inserting the user."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 } 
	
	
	
}

