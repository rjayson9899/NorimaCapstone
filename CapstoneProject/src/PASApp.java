/*
 * This is the main driver of the whole capstone project.
 * This program is able to create a customer account and a
 * accompanying policy that can  expire, be cancelled or claimed.
 * @author Macario N. Peralta V
 * Date created: June 6 2022
 */
package CapStone;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class PASApp {
	
/*	private static  void verifierz() {
		do {
			
		}
		while();
	}
*/	
	private static void clearScreen() {
		for(int x = 0; x < 50; x++) {
			System.out.println("                    ");
		}
	}
	
	public static void main(String[] args) {
		
		int choiceOne;
		String choiceTwo;
		int cusAccTwo = 0, getAccNumb = 0, carYear;
		int custAccNum = 0, numCar = 0;
		int policyNum = 0, polSear;
		int clNum = 0;
		int polClNum = 0;
		double totalPremium = 0, carPrice;
		String accNum, fName = null, lName = null, address = null , ok;
		String polyNum = null, effDate = null, bDay = null, licNum = null, dateLic = null;
		String carMake, carModel, carType, carFuelType, carColor;
		String accDate, addDate, depAcc, depDmgV, estRep;
		boolean doesExist, isFound, isCancelled = true, isWrongDate = false, isWrongAcc, isCorrect;
		Scanner userIn = new Scanner(System.in);
		LocalDate date;
		ArrayList<CustomerAccount> cust = new ArrayList<CustomerAccount>();
		ArrayList<Claim> claim = new ArrayList<>();	
		
		System.out.println("Hello! Welcome to the PAS system ");
		do {
			
			//main menu
			System.out.println("==================================================");
			System.out.println("                       PAS                        ");
			System.out.println("==================================================");
			System.out.println("1. Create a new Customer Account ");
			System.out.println("2. Get a policy quote and buy the policy. ");
			System.out.println("3. Cancel a specific policy ");
			System.out.println("4. File an accident claim against a policy.");
			System.out.println("5. Search for a Customer account ");
			System.out.println("6. Search for and display a specific policy ");
			System.out.println("7. Search for and display a specific claim ");
			System.out.println("8. Exit ");
			System.out.println("=================================================");
			while(!userIn.hasNextInt()) {
				System.out.println("Please enter the number corresponding to your choice.");
				userIn.next();
			} 
			choiceOne = userIn.nextInt();
			switch(choiceOne) {
				//create customer account
			    case 1:
			    	doesExist = false;
			    	if (custAccNum > cust.size()) {
			    		System.out.println("No vacant account available. ");
			    	}
			    	else {
			    		clearScreen();
			    		userIn.nextLine();
			    		do {
				    		System.out.println("===================================");
				    		System.out.println("Enter your first name: (You may add your second name)");
				    		fName = userIn.nextLine();
				    		fName = fName.trim();
				    		System.out.println("Enter your last name: ");
				    		lName = userIn.nextLine();
				    		lName = lName.trim();
				    		System.out.println("Enter your address: (House number, Street, City, Province, Country, Zip code)");
				    		address = userIn.nextLine();
				    		address = address.trim();
				    		System.out.println("Are you satisfied with your input? (Enter y if your inputs are okay)");
				    		ok = userIn.nextLine();
			    		}while(!ok.equalsIgnoreCase("y"));	
			    		
			    		accNum = String.format("%04d", custAccNum);
			    		if(custAccNum == 0) {
			    			System.out.println("Your Account number is: " + accNum);
				    		System.out.println("===================================");
				    		cust.add(new CustomerAccount(fName, lName, address, accNum));
					    	custAccNum++;
					    	clearScreen();
			    		}
			    		else {
			    			for(CustomerAccount cus : cust) {
					    	    if (cus.getfName().equalsIgnoreCase(fName) && cus.getlName().equalsIgnoreCase(lName)) {
					    	      System.out.println("Account already exist. ");
					    	      doesExist = true;
					    	    }
			    			}
			    			
			    			if (doesExist != true) {
					    		System.out.println("Your Account number is: " + accNum);
					    		System.out.println("===================================");
					    		cust.add(new CustomerAccount(fName, lName, address, accNum));
						    	custAccNum++;
						    	clearScreen();
						    	break;
			    			}
			    		}
			    	}
			    	break;
			    	
			    case 2:
			    	//create policy
			    	clearScreen();
			    	if(policyNum > 999999) {
			    		System.out.println("Policy already full.");
			    	}
			    	else {
		    				isFound = false;
			    			System.out.println("Enter the Customer's Account number: ");
			    			while(!userIn.hasNextInt()) {
			    				System.out.println("Please enter a valid input.");
			    				userIn.next();
			    			}  
			    			cusAccTwo = userIn.nextInt();
					    	 String cusAccString = String.format("%04d", cusAccTwo);	 
					    	 for(CustomerAccount cus : cust) {
						    	    if (cus.getAccNum().equals(cusAccString)) {
						    	    	userIn.nextLine();
						    	    	isFound = true;
							    		System.out.println("Is the policy holder the account holder? (Y/N)");
							    		choiceTwo = userIn.nextLine();
							    		if(choiceTwo.equalsIgnoreCase("y")) {
							    			fName = cust.get(cusAccTwo).getfName();
							    			lName = cust.get(cusAccTwo).getlName();
							    			address = cust.get(cusAccTwo).getAddress();
							    			System.out.println("===================================");
							    			do {
							    				try {
							    					isWrongDate = false;
									    			System.out.println("Enter the birthday: (ex. Jan 01 1991)");
									    			bDay = userIn.nextLine();
									    			date = LocalDate.parse(bDay, DateTimeFormatter.ofPattern("LLL dd yyyy"));
							    				}
							    				catch(DateTimeParseException e) {
							    					System.out.println("Please enter a valid input.");
							    					isWrongDate = true;
							    				}
							    			}while(isWrongDate == true); 
							    			System.out.println("Driver's license number: ");
							    			licNum = userIn.nextLine();
							    			do {
							    				try {
								    				isWrongDate = false;
										    		System.out.println("Enter the date issued of the license: (ex. Jan 01 1991) ");
										    		dateLic = userIn.nextLine();
										    		date = LocalDate.parse(dateLic,DateTimeFormatter.ofPattern("LLL dd yyyy"));
							    				}
							    				catch(DateTimeParseException e) {
							    					System.out.println("Please enter a valid input.");
							    					isWrongDate = true;
							    				}
	
							    			}while(isWrongDate == true); 
								    		System.out.println("===================================");
							    		}
						    	    
							    	else { 
								    		//policy holder details
							    			System.out.println("===================================");
								    		System.out.println("Enter the first name: ");
								    		fName = userIn.nextLine();
								    		System.out.println("Enter the last name: ");
								    		lName = userIn.nextLine();
								    		do {
							    				try {
							    					isWrongDate = false;
										    		System.out.println("Enter the birthday: (ex. Jan 01 1991)");
										    		bDay = userIn.nextLine();
										    		date = LocalDate.parse(bDay,DateTimeFormatter.ofPattern("LLL dd yyyy"));
							    				}catch(DateTimeParseException e) {
						    					System.out.println("Please enter a valid input.");
						    					isWrongDate = true;
						    				}

								    		}while(isWrongDate == true); 
								    		System.out.println("Enter the address: ");
								    		address = userIn.nextLine();
								    		System.out.println("Driver's license number: ");
								    		licNum = userIn.nextLine();
							    			do {
							    				try {
							    					isWrongDate = false;
										    		System.out.println("Enter the date issued of the license: (ex. Jan 01 1991) ");
										    		dateLic = userIn.nextLine();
								    				}
								    				catch(DateTimeParseException e) {
								    					System.out.println("Please enter a valid input.");
								    					date = LocalDate.parse(dateLic,DateTimeFormatter.ofPattern("LLL dd yyyy"));
								    					isWrongDate = true;
								    				}
	
							    			}while(isWrongDate == true); 
								    		System.out.println("===================================");
							    		}
							    		 polyNum = String.format("%06d", policyNum);
							    			cust.get(cusAccTwo).pol.add(new Policy(polyNum));
							    			cust.get(cusAccTwo).pol.get(cust.get(cusAccTwo).pol.size()-1).setPolH(fName, lName, bDay, address, licNum, dateLic);
							    			while(numCar == 0) {
								    			System.out.println("How many cars will you add? ");
								    			while(!userIn.hasNextInt()) {
								    				System.out.println("Please enter a valid input.");
								    				userIn.next();
								    			} 
									    	 	numCar = userIn.nextInt();
								    		}
									    	 	userIn.nextLine();
									    	 	while(numCar > 0) {
										    		//vehicle details
									    	 		do {
											    		System.out.println("===================================");
											    		System.out.println("Enter the car make: ");
											    		carMake = userIn.nextLine();
											    		System.out.println("Enter the car model: ");
											    		carModel = userIn.nextLine();
											    		do {
											    			isCorrect = true;
												    		System.out.println("Enter the year: ");
															while(!userIn.hasNextInt()) {
											    				System.out.println("Please enter a valid input.");
											    				userIn.next();
											    			} 
												    		carYear = userIn.nextInt();
												    		if(carYear > 9999 || carYear <= 1886) {
												    			System.out.println("Please enter a valid year.");
												    			isCorrect = false;
												    		}
											    		}while(isCorrect == false);
											    		userIn.nextLine();
											    		System.out.println("Enter the car type: ");
											    		carType = userIn.nextLine();
											    		System.out.println("Enter the fuel type: ");
											    		carFuelType = userIn.nextLine();
											    		System.out.println("Enter the purchase price: ");
														while(!userIn.hasNextDouble()) {
										    				System.out.println("Please enter a valid input.");
										    				userIn.next();
										    			} 
											    		carPrice = userIn.nextDouble();
											    		userIn.nextLine();
											    		System.out.println("Enter the color: ");
											    		carColor = userIn.nextLine();
											    		System.out.println("===================================");
											    		System.out.println("Are you satisfied with your inputs? (Enter y if your inputs are okay)");
											    		ok = userIn.nextLine();
									    	 		 }while(!ok.equalsIgnoreCase("y"));
										    		cust.get(cusAccTwo).pol.get(cust.get(cusAccTwo).pol.size()-1).setCar(carMake, carModel, carYear, carType, carFuelType, carColor, carPrice);
										    		numCar--;
									    	 	}
									    		//quote policy
									    	 	totalPremium = cust.get(cusAccTwo).pol.get(cust.get(cusAccTwo).pol.size()-1).getTotalPremium();
									    		System.out.println("The policy will cost about: " + totalPremium);
									    		System.out.println("Would you like to buy the policy? (Y/N)");
									    		choiceTwo = userIn.nextLine();
									    		if(choiceTwo.equalsIgnoreCase("y")) {
									    			do {
									    				try {
									    					isWrongDate = false;
											    	    	System.out.println("Enter effective date: (ex. Jan 01 1991) ");
												    		effDate = userIn.nextLine();	
											    			isWrongDate = cust.get(cusAccTwo).pol.get(cust.get(cusAccTwo).pol.size()-1).verifyEffDate(effDate);
									    				}catch(DateTimeParseException e) {
									    					System.out.println("Please follow the format of: Jan 01 1991");
									    					isWrongDate = true;
									    				}
									    			}while(isWrongDate == true); 
									    			System.out.println("Policy created. ");
									    			System.out.println("Your policy number is: " + polyNum);
									    			policyNum++;
									    			
									    		}
									    		else {
									    			System.out.println("Policy is cancelled. ");
									    			cust.get(cusAccTwo).pol.remove(policyNum);				    			
									    		}
						    	    	  }
					    	 	}
					    	 if(isFound == false) {
					    		 System.out.println("No account exist");
					    	 }
					    		
					 		}
				    					    	
			    	break;
			    	
			    case 3:
			    	//search for policy
			    	try {
				    	isFound = false;
				    	System.out.println("Enter the policy number: ");
				    	while(!userIn.hasNextInt()) {
		    				System.out.println("Please enter a valid input.");
		    				userIn.next();
		    			}  
				    	polSear = userIn.nextInt();
				    	for(CustomerAccount cus : cust) {
				    	    if (polSear == cus.pol.get(polSear).getPolNum()) {
				    	      cus.pol.get(polSear).cancelPol();
				    	      isFound = true;
				    	    }
				    	    else {
				    	    	System.out.println("No policy exist. ");
				    	    }
				    	}
				    	if(isFound == false){
				    		System.out.println("No policy exist. ");
			    	    }
			    	}catch(IndexOutOfBoundsException e) {
			    		System.out.println("No policy exist.");
			    	}

			    	break;	
			    	
			    case 4:
			    	if(clNum > 999999) {
			    		System.out.println("Claiming is not available right now. ");
			    	}
			    	else {
			    		//claim accident details
			    		try {
				    		isFound = false;
				    		System.out.println("Enter the policy number: ");
					    	while(!userIn.hasNextInt()) {
			    				System.out.println("Please enter a valid input.");
			    				userIn.next();
			    			} 
				    		polSear = userIn.nextInt();
				    		for(CustomerAccount cus : cust) {
					    	    if (polSear == cus.pol.get(polSear).getPolNum()) {
					    	    	getAccNumb = cus.getAccNumb();
					    	    	isFound = true;
					    	    	if(isCancelled == cus.pol.get(polSear).checkStatus()) {
					    	    		System.out.println("Sorry, this policy has already been cancelled/expired. ");
					    	    		isFound = false;
					    	    	}
					 
					    	    }
				    		}
				    		if(isFound == true) {
			    	    		cust.get(getAccNumb).pol.get(polSear).setClaimCounter(++polClNum);
				    	    	userIn.nextLine();
				    	    	System.out.println("Enter the date of accident: ");
					    		accDate = userIn.nextLine();
					    		System.out.println("Enter the address of the accident: ");
					    		addDate = userIn.nextLine();
					    		System.out.println("Description of the accident: ");
					    		depAcc = userIn.nextLine();
					    		System.out.println("Description of the damage to vehicle: ");
					    		depDmgV = userIn.nextLine();
					    		System.out.println("Estimated cost of repairs: ");
					    		estRep = userIn.nextLine();
					    		claim.add(new Claim(accDate, addDate, depAcc, depDmgV,estRep,clNum));
					    		System.out.println("Policy is claimed. ");
					    		clNum++;
			    	    	}
				    	    else {
				    	    	System.out.println("No policy exist. ");
				    	    }
			    		}catch(IndexOutOfBoundsException e) {
			    			System.out.println("No policy exist. ");
			    		}
			    	}
			    	break;
			    	
			    case 5:
			    	//search customer account
			    	try {
				    	isFound = false;
				    	clearScreen();
				    	userIn.nextLine();
				    	System.out.println("Enter the customer's first name: ");
				    	fName = userIn.nextLine();
				    	fName = fName.trim();
				    	System.out.println("Enter the customer's last name: ");
				    	lName = userIn.nextLine();
				    	lName.trim();
				    	clearScreen();
				    	for(CustomerAccount cus : cust) {
				    	    if (cus.getfName().equalsIgnoreCase(fName) && cus.getlName().equalsIgnoreCase(lName)) {	 
				    	    	isFound = true;
				    	    	cus.seeDetails();
				    	    }
				    	}
			    	    if(isFound == false){
			    	    	System.out.println("No account exist. ");
			    	    }
			    	}catch(IndexOutOfBoundsException e) {
			    		System.out.println("No account exist.");
			    	}
			    	break;
			    	
			    case 6:
			    	//search policy 
			    	try {
				     	isFound = false;
				    	clearScreen();
				    	System.out.println("Enter the policy number: ");
				    	while(!userIn.hasNextInt()) {
		    				System.out.println("Please enter a valid input.");
		    				userIn.next();
		    			} 
				    	polSear = userIn.nextInt();
				    	for(CustomerAccount cus : cust) {
				    	    if (polSear == cus.pol.get(polSear).getPolNum()) {
				    	    	isFound = true;
				    	    	cus.pol.get(polSear).checkStatus();
				    	    	cus.pol.get(polSear).seeDetails();
				    	    }
				    	}
				    	if(isFound == false){
				    		System.out.println("No policy exist. ");
			    	    }
			    	}
			    	catch(IndexOutOfBoundsException e) {
			    		System.out.println("No claim exist. ");
			    	}
			    	break;
			    	
			    case 7:
			    	//search for claim
			    	try {
				    	isFound = false;
				    	clearScreen();
				    	System.out.println("Enter the claim number: ");
				    	while(!userIn.hasNextInt()) {
		    				System.out.println("Please enter a valid input.");
		    				userIn.next();
		    			} 
				    	clNum = userIn.nextInt();
				    	for(Claim cl : claim) {
				    	   if (clNum == cl.getClaimNum()) {
				    		   isFound = true;
				    	        cl.seeDetails();
				    	    }
				    	}
				    	if(isFound == false){
				    		System.out.println("No claim exist. ");
			    	    }
			    	}
			    	catch(IndexOutOfBoundsException e) {
			    		System.out.println("No claim exist. ");
			    	}
			    	break;
			}
		}
		while(choiceOne != 8);
		userIn.close();
	}
	
}
