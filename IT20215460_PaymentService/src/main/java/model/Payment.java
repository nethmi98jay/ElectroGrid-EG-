package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class Payment {
		
	 //A common method to connect to the DB
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogride", "root", "Inukshi99*");
	 //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment?autoReconnect=true&useSSL=false", "root", "Inukshi99*");
	 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	
	
	
	
	public String readPayments() 
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
	 output = "<table border='1'><tr><th>PaymentID</th><th>CardNumber</th>" +
	 "<th>CardName</th>" + 
	 "<th>CVV</th>" +
	 "<th>Date</th></tr>"; 
	 
	 String query = "select * from payment"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String PaymentID = Integer.toString(rs.getInt("PaymentID")); 
	 String CardNumber = rs.getString("CardNumber"); 
	 String CardName = rs.getString("CardName"); 
	 String Cvv = rs.getString("Cvv"); 
	 String ExpDate = rs.getString("ExpDate"); 
	 // Add into the html table
	 output += "<tr><td>" + PaymentID + "</td>"; 
	 output += "<td>" + CardNumber + "</td>"; 
	 output += "<td>" + CardName + "</td>"; 
	 output += "<td>" + Cvv + "</td>";
	 output += "<td>" + ExpDate + "</td></tr>"; 
	 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the payments."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	
	//Insert Payments
		public String insertPayment(String CardNumber, String CardName, String Cvv, String ExpDate) 
		 { 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for inserting."; } 
			 // create a prepared statement
			 String query = "insert into payment(`CardNumber`,`CardName`,`Cvv`,`ExpDate`) values (?, ?, ?, ?)"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values 
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(1, CardNumber); 
			 preparedStmt.setString(3, CardName); 
			 preparedStmt.setString(4, Cvv); 
			 preparedStmt.setString(5, ExpDate); 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "	Payment inserted successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while inserting the Payment."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 } 
		
		public String updatePayment(String PaymentID,String CardNumber, String CardName, String Cvv, String ExpDate) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE payment SET CardNumber=?,CardName=?,Cvv=?,ExpDate=? WHERE PaymentID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, CardNumber); 
		 preparedStmt.setString(2, CardName);
		 preparedStmt.setString(3, Cvv);
		 preparedStmt.setString(4, ExpDate);
		 preparedStmt.setInt(5, Integer.parseInt(PaymentID));
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Updated successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while updating the Payment."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	
}