package CapStone;

import java.sql.*;
import java.util.Scanner;

public class PASApp {
	
	public static void clearScreen() {
		for(int x = 0; x < 50; x++) {
			System.out.println("                    ");
		}
	}
	
	public static Connection getConnection() throws Exception {
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
	
	public static void main(String[] args) {
		
		int choiceOne;
		int choiceTwo = 0;
		int custAccNum = 10000;
		int policyNum;
		int clNum = 0;
		String fName;
		String lName;
		String address;
		Scanner userIn = new Scanner(System.in);
		CustomerAccount cust = new CustomerAccount();
		
		do {
			System.out.println("===================================");
			System.out.println("Hello! Welcome to the PAS system ");
			System.out.println("1. Create a new Customer Account ");
			System.out.println("2. Get a policy quote and buy the policy. ");
			System.out.println("3. Cancel a specific policy (i.e change the expiration date of a policy to an earlier date than originally specified)  ");
			System.out.println("4. File an accident claim against a policy. All claims must be maintained by system and should be searchable. ");
			System.out.println("5. Search for a Customer account ");
			System.out.println("6. Search for and display a specific policy ");
			System.out.println("7. Search for and display a specific claim ");
			System.out.println("8. Exit ");
			System.out.println("===================================");
			choiceOne = userIn.nextInt();
			switch(choiceOne) {
			    case 1:
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
			    	if (custAccNum > 9999) {
			    		System.out.println("No Vacant account number available. ");
			    	}
			    	else {
			    		clearScreen();
			    		System.out.println("===================================");
			    		System.out.println("Enter your first name: ");
			    		fName = userIn.nextLine();
			    		System.out.println("Enter your last name: ");
			    		lName = userIn.nextLine();
			    		System.out.println("Enter your address: ");
			    		System.out.println("===================================");
			    		address = userIn.nextLine();
				    	cust.create(fName, lName, address);
				    	clearScreen();
			    	}
			    	break;
			    case 2:
			    	clearScreen();
			    	System.out.println("Enter the Customer's Account number: ");
			    	custAccNum = userIn.nextInt();
			    	if(custAccNum != 0) {
			    		System.out.println("Invalid Customer account number. ");
			    		System.out.println("Would you like to try again or use a different verify method? ");
			    		System.out.println("1.Enter customer account number ");
			    		System.out.println("2.Enter first name and last name ");
			    		System.out.println("3.Exit ");
			    		choiceTwo = userIn.nextInt();
			    		switch(choiceTwo) {
			    		case 1:
			    			break;
			    		}
			    	}
			    	else {
			    		System.out.println("Enter effective date:");
			    		System.out.println("Is the policy holder the account holder? ");
			    		if(choiceTwo != 0) {
			    			
			    		}
			    		else {
		    			System.out.println("===================================");
			    		System.out.println("Enter the first name: ");
			    		System.out.println("Enter the last name: ");
			    		System.out.println("Enter the birthday: ");
			    		System.out.println("Enter the address: ");
			    		System.out.println("Enter the date issued of the license: ");
			    		System.out.println("===================================");
			    		}
			    		System.out.println("===================================");
			    		System.out.println("Enter the car make: ");
			    		System.out.println("Enter the car model: ");
			    		System.out.println("Enter the year: ");
			    		System.out.println("Enter the car type: ");
			    		System.out.println("Enter the fuel type: ");
			    		System.out.println("Enter the purchase price: ");
			    		System.out.println("Enter the color: ");
			    		System.out.println("===================================");
			    		System.out.println("The policy will cost about: ");
			    		System.out.println("Would you like to buy the policy? ");
			    		System.out.println("Policy cancelled. ");
			    		System.out.println("Policy created. ");
			    	}
			    	break;
			    case 3:
			    	System.out.println("Enter the policy number: ");
			    	policyNum = userIn.nextInt();
			    	break;	
			    case 4:
			    	if(clNum > 999999) {
			    		System.out.println("No claiming available right now. ");
			    	}
			    	else {
			    		System.out.println("Enter the policy number: ");
			    		policyNum = userIn.nextInt();
			    		System.out.println("Enter the date of accident: ");
			    		System.out.println("Enter the address of the accident: ");
			    		System.out.println("Description of the accident: ");
			    		System.out.println("Description of the damage to vehicle: ");
			    		System.out.println("Estimated cost of repairs: ");
			    	}
			    	break;
			    case 5:
			    	clearScreen();
			    	userIn.nextLine();
			    	System.out.println("Enter the customer's first name: ");
			    	fName = userIn.nextLine();
			    	System.out.println("Enter the customer's last name: ");
			    	lName = userIn.nextLine();
			    	clearScreen();
			    	cust.searchCust(fName, lName);
			    	break;
			    case 6:
			    	System.out.println("Enter the policy number: ");
			    	
			    	break;
			    case 7:
			    	System.out.println("Enter the claim number: ");
			    	clNum = userIn.nextInt();
			    	break;
			}
		}
		while(choiceOne != 8);
		userIn.close();
	}
}
