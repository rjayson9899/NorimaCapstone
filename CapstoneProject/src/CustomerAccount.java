package CapStone;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerAccount {
	private int cusAccNum;
	private String fName;
	private String lName;
	private String address;
	
	Scanner userIn = new Scanner(System.in);
	
	ArrayList<Policy> pol = new ArrayList<>();
	ArrayList<PolicyHolder> polHold = new ArrayList<>();
	
	public CustomerAccount() {
		
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
	
	public void create(String fName, String lName, String address) {

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

	}

	public void buyPolicy() {
		
	}
	
	public void searchCust(String fName, String lName) {
		
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
				System.out.println("================================");
				System.out.println("Account number: " + cusAccNum);
				System.out.println("First Name: " + this.fName);
				System.out.println("Last Name: " + this.lName);
				System.out.println("Address: " + this.address);
				/*
				 *System.out.println("Policies: ");
				  System.out.println("Policy holders: ");
				 */
				System.out.println("================================");
				
			}
			
		}
		catch (Exception e) {
			System.out.println("There is no matching Account in the database. ");
		}
	}
	
}
