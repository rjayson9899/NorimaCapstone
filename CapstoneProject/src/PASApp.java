package CapStone;

import java.util.ArrayList;
import java.util.Scanner;

public class PASApp {
	
	private static void clearScreen() {
		for(int x = 0; x < 50; x++) {
			System.out.println("                    ");
		}
	}
	
	public static void main(String[] args) {
		
		int choiceOne, z = 0;
		int cusAccTwo;
		int custAccNum = 0;
		int policyNum = 0;
		int clNum = 0;
		int pLAH;
		String accNum, fName = null, lName = null, address = null;
		String effDate = null, bDay = null, licNum = null, dateLic = null;
		String carMake, carModel, carYear, carType, carPrice, carFuelType, carColor;
		String accDate, addDate, depAcc, depDmgV, estRep;  
		
		Scanner userIn = new Scanner(System.in);
		ArrayList<CustomerAccount> cust = new ArrayList<>();
		Claim cl = new Claim();
		
		do {
			//main menu
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
				//create customer account
			    case 1:
			    	if (custAccNum > cust.size()) {
			    		System.out.println("No vacant account available. ");
			    	}
			    	else {
			    		clearScreen();
			    		userIn.nextLine();
			    		System.out.println("===================================");
			    		System.out.println("Enter your first name: ");
			    		fName = userIn.nextLine();
			    		System.out.println("Enter your last name: ");
			    		lName = userIn.nextLine();
			    		System.out.println("Enter your address: ");
			    		address = userIn.nextLine();
			    		accNum = String.format("%04d", custAccNum);
			    		if(custAccNum == 0) {
			    			System.out.println("Your Account number is: " + accNum);
				    		System.out.println("===================================");
				    		cust.add(new CustomerAccount(fName, lName, address, accNum));
					    	custAccNum++;
					    	userIn.nextLine();
					    	clearScreen();
			    		}
			    		else {
			    			for(CustomerAccount cus : cust) {
				    	    if (cus.getfName().equals(fName) && cus.getlName().equals(lName)) {
				    	      System.out.println("Account already exist. ");
				    	    }
				    	    else {
					    		System.out.println("Your Account number is: " + accNum);
					    		System.out.println("===================================");
					    		cust.add(new CustomerAccount(fName, lName, address, accNum));
						    	custAccNum++;
						    	userIn.nextLine();
						    	clearScreen();
				    	    	}
			    			}	
			    		}
			    	}
			    	break;
			    	
			    case 2:
			    	clearScreen();
			    	if(policyNum > 999999) {
			    		System.out.println("Policy already full.");
			    	}
			    	else {
				    	System.out.println("Enter the Customer's Account number: ");
				    	 cusAccTwo = userIn.nextInt();
				    	 String cuString = String.format("%04d", cusAccTwo);
				    	 for(CustomerAccount cus : cust) {
					    	    if (cus.getAccNum().equals(cuString)) {
					    	    	userIn.nextLine();
						    		System.out.println("Is the policy holder the account holder? ");
						    		pLAH = userIn.nextInt();
						    		if(pLAH == 1) {
						    			userIn.nextLine();
						    			fName = cust.get(cusAccTwo).getfName();
						    			lName = cust.get(cusAccTwo).getlName();
						    			address = cust.get(cusAccTwo).getAddress();
						    			System.out.println("===================================");
						    			System.out.println("Enter the birthday: ");
						    			bDay = userIn.nextLine();
						    			System.out.println("Driver's license number: ");
						    			licNum = userIn.nextLine();
							    		System.out.println("Enter the date issued of the license: ");
							    		dateLic = userIn.nextLine();
							    		System.out.println("===================================");
						    		}
						    		else { 
							    		//policy holder details
						    			System.out.println("===================================");
							    		System.out.println("Enter the first name: ");
							    		fName = userIn.nextLine();
							    		System.out.println("Enter the last name: ");
							    		lName = userIn.nextLine();
							    		System.out.println("Enter the birthday: ");
							    		bDay = userIn.nextLine();
							    		System.out.println("Enter the address: ");
							    		address = userIn.nextLine();
							    		System.out.println("Driver's license number: ");
							    		licNum = userIn.nextLine();
							    		System.out.println("Enter the date issued of the license: ");
							    		dateLic = userIn.nextLine();
							    		System.out.println("===================================");
						    		}
					    	    }
				    	 }	    
				 /*   	if(cusAccTwo != 0) {
				    		System.out.println("Invalid Customer account number. ");
				    		System.out.println("Would you like to try again or use a different verify method? ");
				    		System.out.println("==========================================");
				    		System.out.println("1.Enter customer account number ");
				    		System.out.println("2.Enter first name and last name ");
				    		System.out.println("3.Exit ");
				    		System.out.println("========================================== ");
				    		choiceTwo = userIn.nextInt();
				    		switch(choiceTwo) {
				    		case 1:
				    			break;
				    		}
				    	} */
				    		//vehicle details
				    		System.out.println("===================================");
				    		System.out.println("Enter the car make: ");
				    		carMake = userIn.nextLine();
				    		System.out.println("Enter the car model: ");
				    		carModel = userIn.nextLine();
				    		System.out.println("Enter the year: ");
				    		carYear = userIn.nextLine();
				    		System.out.println("Enter the car type: ");
				    		carType = userIn.nextLine();
				    		System.out.println("Enter the fuel type: ");
				    		carFuelType = userIn.nextLine();
				    		System.out.println("Enter the purchase price: ");
				    		carPrice = userIn.nextLine();
				    		System.out.println("Enter the color: ");
				    		carColor = userIn.nextLine();
				    		System.out.println("===================================");
				    		System.out.println("Will you add another car? ");
				    		
				    		//quote policy
				    		System.out.println("The policy will cost about: ");
				    		System.out.println("Would you like to buy the policy? ");
				    		pLAH = userIn.nextInt();
				    		if(pLAH == 1) {
				    			System.out.println("Policy cancelled. ");
				    		}
				    		else {
				    			System.out.println("Policy created. ");
				    	    	System.out.println("Enter effective date: (ex. Jan 01 1991) ");
					    		effDate = userIn.nextLine();
				    			cust.get(cusAccTwo).pol.add(new Policy());
				    			cust.get(cusAccTwo).pol.get(cust.get(cusAccTwo).pol.size()-1).setPolH(fName, lName, bDay, address, licNum, dateLic);
				    			cust.get(cusAccTwo).pol.get(cust.get(cusAccTwo).pol.size()-1).setCar(carMake, carModel, carYear, carType, carFuelType, carColor);
				    			cust.get(cusAccTwo).pol.get(cust.get(cusAccTwo).pol.size()-1).setExpDate(effDate);
				    			cust.get(cusAccTwo).pol.get(cust.get(cusAccTwo).pol.size()-1).polyHol.seetDeets();
				    			cust.get(cusAccTwo).pol.get(cust.get(cusAccTwo).pol.size()-1).car.get(cust.get(cusAccTwo).pol.get(cust.get(cusAccTwo).pol.size()-1).car.size()-1).seeDeets();
				    		}
				    	}	
			    	
		
			    	break;
			    	
			    case 3:
			    	System.out.println("Enter the policy number: ");
			    	policyNum = userIn.nextInt();
			    	//search for policy
			    	
			    	//delete policy from database
			    	break;	
			    	
			    case 4:
			    	if(clNum > 999999) {
			    		System.out.println("No claiming available right now. ");
			    	}
			    	else {
			    		//claim accident details
			    		System.out.println("Enter the policy number: ");
			    		policyNum = userIn.nextInt();
			    		System.out.println("Enter the date of accident: ");
			    		accDate = userIn.nextLine();
			    		cl.setDateAcc(accDate);
			    		System.out.println("Enter the address of the accident: ");
			    		addDate = userIn.nextLine();
			    		cl.setDesAcc(addDate);
			    		System.out.println("Description of the accident: ");
			    		depAcc = userIn.nextLine();
			    		cl.setDesAcc(depAcc);
			    		System.out.println("Description of the damage to vehicle: ");
			    		depDmgV = userIn.nextLine();
			    		cl.setDesDmgV(depDmgV);
			    		System.out.println("Estimated cost of repairs: ");
			    		estRep = userIn.nextLine();
			    		cl.setCost(estRep);
			    		System.out.println("Policy is claimed. ");
			    	}
			    	break;
			    //search customer account	
			    case 5:
			    	clearScreen();
			    	userIn.nextLine();
			    	System.out.println("Enter the customer's first name: ");
			    	fName = userIn.nextLine();
			    	System.out.println("Enter the customer's last name: ");
			    	lName = userIn.nextLine();
			    	clearScreen();
			    	for(CustomerAccount cus : cust) {
			    	    if (cus.getfName().equals(fName) && cus.getlName().equals(lName)) {
			    	       cus.showDets();
			    	    }
			    	    else {
			    	    	System.out.println("Cant find.");
			    	    }
			    	}
			    	break;
			    //search policy 	
			    case 6:
			    	clearScreen();
			    	System.out.println("Enter the policy number: ");
			    	policyNum = userIn.nextInt();
			    	break;
			    //search for claim	
			    case 7:
			    	clearScreen();
			    	System.out.println("Enter the claim number: ");
			    	clNum = userIn.nextInt();
		
			    	break;
			}
		}
		while(choiceOne != 8);
		userIn.close();
	}
	
}
