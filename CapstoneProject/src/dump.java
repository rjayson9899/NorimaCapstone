package CapStone;

import java.sql.*;

public class dump {
	try {
		Connection con = getConnection();
		Statement st = con.createStatement();
		
		ResultSet res = st.executeQuery("SELECT idCusAcc FROM capstoneproject.customeraccount;");
		while(res.next()) {
			custAccNum = res.getInt("idCusAcc");

		}	
	}
	catch (Exception e) {
		System.out.println(e);
	}
	
	private static Connection getConnection() throws Exception {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/capstoneproject";
			String username = "root";
			String password = "1234";
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url,username,password);
		    return con; 
		} 
		catch(SQLException ex) {
		    ex.printStackTrace();
		} 
		return null;
	}
	
	public Connection getConnection() throws Exception {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/capstoneproject";
			String username = "root";
			String password = "1234";
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url,username,password);
		    return con; 
		} 
		catch(SQLException ex) {
		    ex.printStackTrace();
		} 
		return null;
	}
	
	try {
		Connection con = getConnection();
		PreparedStatement posted = con.prepareStatement("INSERT INTO "
		+ "CustomerAccount (idCusAcc, firstName, lastName, Address)"
		+ "VALUES ('"+cusAccNum+"','"+fName+"','"+lName+"','"+address+"') ");	
		posted.executeUpdate();
		cusAccNum++;
	}
	catch (Exception e) {
		System.out.println(e);
	}
	
	try {
		Connection con = getConnection();
		Statement st = con.createStatement();
		
		ResultSet res = st.executeQuery("SELECT * FROM " + 
		"capstoneproject.customeraccount where firstName = \""
				+ fName + "\" AND lastName = \"" + lName + "\";");
		while(res.next()) {
			this.cusAccNum = res.getInt("idCusAcc");
			this.fName = res.getString("firstName");
			this.lName = res.getString("lastName");
			this.address = res.getString("address");
}
