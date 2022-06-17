package CapStone;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
/*
 * This is the main driver of the whole capstone project.
 * This program is able to create a customer account and a
 * accompanying policy that can  expire, be cancelled or claimed.
 * @author Macario N. Peralta V
 * Date created: June 6 2022
 */
public class PASApp {
	
   static Scanner userIn = new Scanner(System.in);
   static LocalDate date;
   static LocalDate eDate = LocalDate.now();
   static LocalDate legalDate;
   static boolean isWrongDate;
   
   //this method checks for the string inputs that are being prompted to the user.
   private static String stringVerifier(String message, String format) {
		String input = null;
	   do {
		   System.out.println(message);
		   input = userIn.nextLine();
		   input.trim();

		   if (!input.matches(format)) {
			   System.out.println("Please enter a correct input. ");
		   }
	   }
	   while(!input.matches(format));
	   return input;
	}
   
   //this method checks for the birth date inputs that are being prompted to the user.
   private static String birthDateVerifier(String message) {
	   String dateFormat = "LLL dd uuuu";
	   boolean isBefore = true;
	   String input = null;
	   LocalDate limitDate = eDate.minusYears(100);
	   do {
		   try {
			   isBefore = false;
			   isWrongDate = false;
			   System.out.println(message);
			   input = userIn.nextLine();
			   input.trim();
			   input = input.substring(0, 1).toUpperCase() + input.substring(1);
			   date = LocalDate.parse(input, DateTimeFormatter.ofPattern(dateFormat, Locale.US).withResolverStyle(ResolverStyle.STRICT));
			   legalDate = date.plusYears(18);
			   isBefore = legalDate.isAfter(eDate);
			   if(isBefore == true) {
				   System.out.println("You have to be 18 and above to be eligible for a policy. ");
			   }
		   }
		   catch(Exception e) {
				System.out.println("Please enter a valid input.");
				isWrongDate = true;
			}
	   }
	   while(isWrongDate == true || date.isBefore(limitDate) || eDate.isBefore(date) || isBefore == true);
	   return input;
   }
   
   //this method checks for the license date inputs that are being prompted to the user.
   private static String licenseDateVerifier(String message, String bDay) {
	   String dateFormat = "LLL dd uuuu";
	   boolean isBefore = true;
	   String input = null;
	   LocalDate limitDate = eDate.minusYears(100);
	   LocalDate birthDay;
	   do {
		   try {
			   isWrongDate = false;
			   System.out.println(message);
			   input = userIn.nextLine();
			   input.trim();
			   input = input.substring(0, 1).toUpperCase() + input.substring(1);
			   date = LocalDate.parse(input, DateTimeFormatter.ofPattern(dateFormat, Locale.US).withResolverStyle(ResolverStyle.STRICT));
			   birthDay = LocalDate.parse(bDay, DateTimeFormatter.ofPattern(dateFormat, Locale.US).withResolverStyle(ResolverStyle.STRICT));
			   legalDate = date.minusYears(18);
			   isBefore = legalDate.isBefore(birthDay);
			   if(isBefore == true) {
				   System.out.println("Your date does not correspond to your previous inputs (i.e Birthday) ");
			   }
		   }
		   catch(Exception e) {
				System.out.println("Please enter a valid input.");
				isWrongDate = true;
			}
	   }
	   while(isWrongDate == true || date.isBefore(limitDate) || eDate.isBefore(date) || isBefore == true);
	   return input;
   }
   
   //this method checks for the accident date inputs that are being prompted to the user.
   private static String accDateVerifier(String message, String dateLic) {
	   String dateFormat = "LLL dd uuuu";
	   boolean isBefore = true;
	   String input = null;
	   LocalDate limitDate = eDate.minusYears(100);
	   LocalDate verifier;
	   do {
		   try {
			   isWrongDate = false;
			   System.out.println(message);
			   input = userIn.nextLine();
			   input.trim();
			   input = input.substring(0, 1).toUpperCase() + input.substring(1);
			   date = LocalDate.parse(input, DateTimeFormatter.ofPattern(dateFormat, Locale.US).withResolverStyle(ResolverStyle.STRICT));
			   verifier = LocalDate.parse(dateLic, DateTimeFormatter.ofPattern(dateFormat, Locale.US).withResolverStyle(ResolverStyle.STRICT));
			   isBefore = date.isBefore(verifier);
			   if(isBefore == true) {
				   System.out.println("Your date does not correspond to your previous inputs (i.e Driver's License date) ");
			   }
		   }
		   catch(Exception e) {
				System.out.println("Please enter a valid input.");
				isWrongDate = true;
			}
	   }
	   while(isWrongDate == true || date.isBefore(limitDate) || eDate.isBefore(date) || isBefore == true);
	   return input;
   }
   
   //this method checks for the int inputs that are being prompted to the user.
   private static int intVerifier(String message) {
	   int input;
	   boolean isCorrect;
		do {
			isCorrect = true;
    		System.out.println(message);
			while(!userIn.hasNextInt()) {
				System.out.println("Please enter a valid input.");
				userIn.next();
			} 
    		input = userIn.nextInt();
    		if(input > 2022 || input <= 1886) {
    			System.out.println("Please enter a valid year.");
    			isCorrect = false;
    		}
		}while(isCorrect == false);
		return input;
   }
   
   //this method checks for the double inputs that are prompted to the user.
   private static double doubleVerifier(String message) {
	   double input;
	   boolean isCorrect;
		do {
			isCorrect = true;
    		System.out.println(message);
			while(!userIn.hasNextDouble()) {
				System.out.println("Please enter a valid input.");
				userIn.next();
			} 
    		input = userIn.nextInt();
    		
    		if (input <= 0) {
    			System.out.println("Please enter a positive input.");
    			isCorrect = false;
    		}
		}while(isCorrect == false);
		return input;
   }
   
	private static void clearScreen() {
		for(int x = 0; x < 50; x++) {
			System.out.println("                    ");
		}
	}
	
	public static void main(String[] args) {
		
		String choiceOne;
		String choiceTwo;
		int cusAccTwo = 0, getAccNumb = 0, carYear;
		int custAccNum = 0, numCar = 0;
		int policyNum = 0, polSear, clNum = 0, polClNum = 0;
		double totalPremium = 0, carPrice, estRep;
		String fName = null, lName = null, address = null, ok;
		String effDate = null, bDay = null, licNum = null, dateLic = null;
		String carMake, carModel, carType, carFuelType, carColor;
		String accDate, addDate, depAcc, depDmgV;
		boolean doesExist, isFound, isCancelled = true;
		
		ArrayList<CustomerAccount> cust = new ArrayList<>();
		ArrayList<Claim> claim = new ArrayList<>();	
		
		System.out.println("Hello! Welcome to the PAS system ");
		do {
			clearScreen();
			//main menu
			System.out.println("========================================================");
			System.out.println("                         PAS                        ");
			System.out.println("========================================================");
			System.out.println("1. Create a new Customer Account ");
			System.out.println("2. Get a policy quote and buy the policy. ");
			System.out.println("3. Cancel a specific policy. ");
			System.out.println("4. File an accident claim against a policy.");
			System.out.println("5. Search for a Customer account. ");
			System.out.println("6. Search for and display a specific policy. ");
			System.out.println("7. Search for and display a specific claim. ");
			System.out.println("8. Exit. ");
			System.out.println("========================================================");
			choiceOne = userIn.nextLine();
			switch(choiceOne){
			    case "":
			    	clearScreen();
			    	break;
			    case " ":
			    	clearScreen();
			    	break;	
				//create customer account
			    case "1":
			    	doesExist = false;
			    	if (custAccNum > cust.size()) {
			    		System.out.println("=============================================================");
			    		System.out.println("No vacant account available. ");
			    		System.out.println("=============================================================");
			    		System.out.println("Press any key to continue. ");
				    	userIn.nextLine();
			    	}
			    	else {
			    		clearScreen();
			    		do {
			    			System.out.println("=============================================================");
		    				System.out.println("                Customer account details                     ");
			    			System.out.println("=============================================================");
				    		fName = stringVerifier("Enter your first name: (You may add your second name)", "^[a-zA-Z][a-zA-Z ]*$");
				    		lName = stringVerifier("Enter your last name: ", "^[a-zA-Z][a-zA-Z ]*$");
					    	address = stringVerifier("Enter your address: (House number, Street, City, Province, Country, Zip code)","^[\\d]+[ ][a-zA-Z0-9,\\-\\. ]*$");
				    		System.out.println("Are you satisfied with your inputs? (Enter y if your inputs are okay)");
				    		System.out.println("Enter any key to re-do the inputs. ");
				    		ok = userIn.nextLine();
			    		 }while(!ok.equalsIgnoreCase("y"));	
			    		
			    		String accNum = String.format("%04d", custAccNum);
			    		if(custAccNum == 0) {
			    			System.out.println("Your Account number is: " + accNum);
			    			System.out.println("=============================================================");
				    		cust.add(new CustomerAccount(fName, lName, address, accNum));
					    	custAccNum++;
					    	System.out.println("Press any key to continue. ");
					    	userIn.nextLine();
					    	clearScreen();
			    		}
			    		else {
			    			for(CustomerAccount cus : cust) {
					    	    if (cus.getfName().equalsIgnoreCase(fName) && cus.getlName().equalsIgnoreCase(lName)) {
					    	      System.out.println("Account already exist. ");
					    	      System.out.println("Press any key to continue. ");
							      userIn.nextLine();
					    	      doesExist = true;
					    	      break;
					    	    }
			    			}
			    			
			    			if (doesExist != true) {
					    		System.out.println("Your Account number is: " + accNum);
					    		System.out.println("=============================================================");
					    		cust.add(new CustomerAccount(fName, lName, address, accNum));
						    	custAccNum++;
						    	System.out.println("Press any key to continue. ");
						    	userIn.nextLine();
						    	clearScreen();
						    	break;
			    			}
			    		}
			    	}
			    	break;
			    	
			    //quote and create policy
			    case "2":
			    	clearScreen();
			    	if(policyNum > 999999) {
			    		System.out.println("Policy already full.");
			    	}
			    	else {	
		    				isFound = false;
				    		System.out.println("========================================================");
			    			System.out.println("Enter the Customer's Account number: ");
			    			String cusAccString = userIn.nextLine();	 
					    	 for(CustomerAccount cus : cust) {
						    	    if (cus.getAccNum().equals(cusAccString)) {
						    	    	isFound = true;
						    	    	//policy holder details
							    		System.out.println("Is the policy holder the account holder? ");
							    		System.out.println("Press y if yes and any key if no.");
							    		choiceTwo = userIn.nextLine();
							    		if(choiceTwo.equalsIgnoreCase("y")) {
							    			fName = cust.get(cusAccTwo).getfName();
							    			lName = cust.get(cusAccTwo).getlName();
							    			address = cust.get(cusAccTwo).getAddress();
							    			do {
							    				System.out.println("=============================================================");
							    				System.out.println("                   Policy holder details                     ");
							    				System.out.println("=============================================================");
								    			bDay = birthDateVerifier("Enter the birthday: (format: Jan 01 1991) ");
								    			licNum = stringVerifier("Driver's license number: ", "^[a-zA-Z0-9][a-zA-Z0-9- ]*$");
								    			dateLic = licenseDateVerifier("Enter the date issued of the license: (format: Jan 01 1991) ", bDay); 
								    			System.out.println("=============================================================");
									    		System.out.println("Are you satisfied with your input? (Enter y if your inputs are okay)");
									    		System.out.println("Enter any key to re-do the inputs. ");
									    		ok = userIn.nextLine();
							    			}while(!ok.equalsIgnoreCase("y"));
							    		}
						    	    
							    	else { 
								    		//policy holder details
							    			do {
							    				System.out.println("=============================================================");
							    				System.out.println("                   Policy holder details                     ");
								    			System.out.println("============================================================");
								    			fName = stringVerifier("Enter your first name: (You may add your second name)", "^[a-zA-Z][a-zA-Z ]*$");
									    		lName = stringVerifier("Enter your last name: ", "^[a-zA-Z][a-zA-Z ]*$");
									    		bDay = birthDateVerifier("Enter the birthday: (format: Jan 01 1991)");
									    		address = stringVerifier("Enter your address: (House number, Street, City, Province, Country, Zip code)","^[\\d]+[ ][a-zA-Z0-9,\\.\\- ]*$");
									    		licNum = stringVerifier("Driver's license number: ", "^[a-zA-Z0-9][a-zA-Z0-9- ]*$");
									    		dateLic = licenseDateVerifier("Enter the date issued of the license: (format: Jan 01 1991) ", bDay);
									    		System.out.println("=============================================================");
									    		System.out.println("Are you satisfied with your input? (Enter y if your inputs are okay)");
									    		System.out.println("Enter any key to re-do the inputs. ");
									    		ok = userIn.nextLine();
							    			}while(!ok.equalsIgnoreCase("y"));
							    		}
							    		 	String polyNum = String.format("%06d", policyNum);
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
								    	 			System.out.println("=============================================================");
								    				System.out.println("                      Vehicle details                        ");
								    	 			System.out.println("=============================================================");
										    		carMake = stringVerifier("Enter the car make: ", "^[a-zA-Z][a-zA-Z ]*$");
										    		carModel = stringVerifier("Enter the car model: ", "^[a-zA-Z][a-zA-z0-9-. ]*$");
										    		carYear = intVerifier("Enter the year: ");											    		
										    		userIn.nextLine();
										    		carType = stringVerifier("Enter the car type: ", "^[a-zA-Z][a-zA-z0-9- ]*$");
										    		carFuelType = stringVerifier("Enter the fuel type: ", "^[a-zA-Z][a-zA-Z ]*$");
										    		carPrice = doubleVerifier("Enter the car price: ");
										    		userIn.nextLine();
										    		carColor = stringVerifier("Enter the car color: ", "^[a-zA-Z][a-zA-z0-9-. ]*$");
										    		System.out.println("=============================================================");
										    		System.out.println("Are you satisfied with your inputs? (Enter y if your inputs are okay)");
										    		System.out.println("Enter any key to re-do the inputs. ");
										    		ok = userIn.nextLine();
								    	 		 }while(!ok.equalsIgnoreCase("y"));
									    		cust.get(cusAccTwo).pol.get(cust.get(cusAccTwo).pol.size()-1).setCar(carMake, carModel, carYear, carType, carFuelType, carColor, carPrice);
									    		numCar--;
								    	 	}
								    		//quote policy
								    	 	totalPremium = cust.get(cusAccTwo).pol.get(cust.get(cusAccTwo).pol.size()-1).getTotalPremium();
								    		System.out.printf("The policy will cost about: $%.2f\n", totalPremium);
								    		System.out.println("========================================================");
								    		System.out.println("Would you like to buy the policy? ");
								    		System.out.println("Press y if yes and any key if no.");
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
								    			System.out.println("========================================================");
								    			System.out.println("Press any key to continue. ");
										    	userIn.nextLine();
										    	clearScreen();
								    			
								    		}
								    		else {
								    			System.out.println("Policy is cancelled. ");
								    			cust.get(cusAccTwo).pol.remove(policyNum);
								    			System.out.println("========================================================");
								    			System.out.println("Press any key to continue. ");
										    	userIn.nextLine();
										    	clearScreen();
								    		}
					    	    	  }
					    	 	}
					    	 if(isFound == false) {
					    		 System.out.println("No account exist");
					    		 System.out.println("========================================================");
					    		 System.out.println("Press any key to continue. ");
							     userIn.nextLine();
							     clearScreen();
					    	 }
					    		
					 	}
				    					    	
			    	break;
			    	
			    //search for policy	
			    case "3":
			    	try {
			    		clearScreen();
				    	isFound = false;
				    	System.out.println("=============================================================");
				    	System.out.println("Enter the policy number: ");
				    	String polFind = userIn.nextLine();
			    		polSear = Integer.parseInt(polFind);
				    	for(CustomerAccount cus : cust) {
				    	    if (polFind.equals(cus.pol.get(polSear).getPolNum())) {
				    	      cus.pol.get(polSear).cancelPol();
				    	      System.out.println("=============================================================");
					    	  System.out.println("Press any key to continue. ");
						      userIn.nextLine();
				    	      isFound = true;
				    	    }
				    	}
				    	if(isFound == false){
				    		System.out.println("No policy exist. ");
				    		System.out.println("========================================================");
				    		System.out.println("Press any key to continue. ");
					    	userIn.nextLine();
					    	clearScreen();
			    	    }
			    	}catch(IndexOutOfBoundsException e) {
			    		System.out.println("No policy exist.");
			    		System.out.println("========================================================");
			    		System.out.println("Press any key to continue. ");
				    	userIn.nextLine();
				    	clearScreen();
			    	}
		    		catch(NumberFormatException e) {
		    			System.out.println("Invalid input ");
		    			System.out.println("========================================================");
		    			System.out.println("Press any key to continue. ");
				    	userIn.nextLine();
				    	clearScreen();
		    		}

			    	break;	
			    	
			    //claim accident details	
			    case "4":
			    	if(clNum > 999999) {
			    		clearScreen();
			    		System.out.println("========================================================");
			    		System.out.println("         Claiming is not available right now.           ");
			    		System.out.println("========================================================");
			    	}
			    	else {
			    		try {
			    			clearScreen();
				    		isFound = false;
				    		doesExist = false;
				    		System.out.println("========================================================");
				    		System.out.println("Enter the policy number: ");
				    		String polFind = userIn.nextLine();
				    		polSear = Integer.parseInt(polFind);
				    		for(CustomerAccount cus : cust) {
					    	    if (polFind.equals(cus.pol.get(polSear).getPolNum())) {
					    	    	getAccNumb = cus.getAccNumb();
					    	    	isFound = true;
					    	    	cus.pol.get(polSear).checkStatus();
					    	    	if(isCancelled == cus.pol.get(polSear).checkStatus()) {
					    	    		isFound = false;
					    	    		doesExist = true;
					    	    		break;
					    	    	}
					 
					    	    }
				    		}
				    		if(isFound == true) {
				    			System.out.println("=============================================================");
			    				System.out.println("                      Claim details                          ");
			    				System.out.println("=============================================================");
			    	    		cust.get(getAccNumb).pol.get(polSear).setClaimCounter(++polClNum);
				    	    	String clNumb = String.format("%05d", clNum);
				    	    	dateLic = cust.get(getAccNumb).pol.get(polSear).polyHol.getDateLic();
				    	    	accDate = accDateVerifier("Enter the date of accident: (format: Jan 01 1991) ", dateLic);
				    	    	addDate = stringVerifier("Enter the address of the accident: ", "^[a-zA-Z0-9][a-zA-z0-9-.!%@# ]*$");
					    		depAcc = stringVerifier("Description of the accident: ", "^[a-zA-Z0-9][a-zA-z0-9-.!%@# ]*$");
					    		depDmgV = stringVerifier("Description of the damage to vehicle: ", "^[a-zA-Z0-9][a-zA-z0-9-.!%@# ]*$");
					    		estRep = doubleVerifier("Estimated cost of repairs: ");
					    		userIn.nextLine();
					    		claim.add(new Claim(accDate, addDate, depAcc, depDmgV, estRep, clNumb));
					    		System.out.println("Policy is claimed. ");
					    		claim.get(clNum).accidentClaim();
					    		clNum++;
				    		    System.out.println("========================================================");
				    		    System.out.println("Press any key to continue. ");
						        userIn.nextLine();
						        clearScreen();
			    	    	}
				    	    else if (isFound == false && doesExist == true){
				    	    	System.out.println("Sorry, this policy has already been cancelled/expired or to be scheduled. ");
				    	    	System.out.println("========================================================");
				    	    	System.out.println("Press any key to continue. ");
						    	userIn.nextLine();
						    	clearScreen();
				    	    }
				    	    else if (isFound == false && doesExist == false){
				    	    	System.out.println("No policy exist. ");
				    	    	System.out.println("========================================================");
				    	    	System.out.println("Press any key to continue. ");
						    	userIn.nextLine();
						    	clearScreen();
				    	    }
			    		}catch(IndexOutOfBoundsException e) {
			    			System.out.println("No policy exist. ");
			    			System.out.println("========================================================");
			    			System.out.println("Press any key to continue. ");
					    	userIn.nextLine();
					    	clearScreen();
			    		}
			    		catch(NumberFormatException e) {
			    			System.out.println("Invalid input ");
			    			System.out.println("========================================================");
			    			System.out.println("Press any key to continue. ");
					    	userIn.nextLine();
					    	clearScreen();
			    		}
			    	}
			    	break;
			    	
			    //search customer account	
			    case "5":
			    	try {
				    	isFound = false;
				    	clearScreen();
				    	System.out.println("========================================================");
				    	fName = stringVerifier("Enter the customer's first name: ", "^[a-zA-Z][a-zA-Z ]*$");
				    	lName = stringVerifier("Enter the customer's last name: ", "^[a-zA-Z][a-zA-Z ]*$");
				    	clearScreen();
				    	for(CustomerAccount cus : cust) {
				    	    if (cus.getfName().equalsIgnoreCase(fName) && cus.getlName().equalsIgnoreCase(lName)) {	 
				    	    	isFound = true;
				    	    	cus.seeDetails();
				    	    	System.out.println("Press any key to continue. ");
						    	userIn.nextLine();
						    	clearScreen();
				    	    }
				    	}
			    	    if(isFound == false){
			    	    	System.out.println("No account exist. ");
			    	    	System.out.println("========================================================");
			    	    	System.out.println("Press any key to continue. ");
					    	userIn.nextLine();
					    	clearScreen();
			    	    }
			    	}catch(IndexOutOfBoundsException e) {
			    		 System.out.println("No account exist.");
			    		 System.out.println("========================================================");
			    		 System.out.println("Press any key to continue. ");
				    	 userIn.nextLine();
				    	 clearScreen();
			    	}
			    	catch(NumberFormatException e) {
		    			 System.out.println("Invalid input ");
			    		 System.out.println("========================================================");
			    		 System.out.println("Press any key to continue. ");
					     userIn.nextLine();
					     clearScreen();
		    		}
			    	break;
			    	
			    //search for specific policy	
			    case "6": 
			    	try {
				     	isFound = false;
				    	clearScreen();
				    	System.out.println("========================================================");
				    	System.out.println("Enter the policy number: ");
				    	String polFind = userIn.nextLine();
			    		polSear = Integer.parseInt(polFind);
				    	for(CustomerAccount cus : cust) {
				    	    if (polFind.equals(cus.pol.get(polSear).getPolNum())) {
				    	    	isFound = true;
				    	    	cus.pol.get(polSear).checkStatus();
				    	    	cus.pol.get(polSear).seeDetails();
				    	    	System.out.println("Press any key to continue. ");
						    	userIn.nextLine();
						    	clearScreen();
				    	    }
				    	}
				    	if(isFound == false){
				    		 System.out.println("No policy exist. ");
				    		 System.out.println("========================================================");
				    		 System.out.println("Press any key to continue. ");
					    	 userIn.nextLine();
					    	 clearScreen();
			    	    }
			    	}
			    	catch(IndexOutOfBoundsException e) {
			    		 System.out.println("No policy exist. ");
			    		 System.out.println("========================================================");
			    		 System.out.println("Press any key to continue. ");
				    	 userIn.nextLine();
				    	 clearScreen();
			    	}
		    		catch(NumberFormatException e) {
		    			 System.out.println("Invalid input ");
			    		 System.out.println("========================================================");
			    		 System.out.println("Press any key to continue. ");
					     userIn.nextLine();
					     clearScreen();
		    		}
			    	break;
			   
			    //search for specific claim
			    case "7":
			    	try {
				    	isFound = false;
				    	clearScreen();
				    	System.out.println("========================================================");
				    	System.out.println("Enter the claim number: ");
				    	String clNumFind = userIn.nextLine();
				    	for(Claim cl : claim) {
				    	   if (clNumFind.equalsIgnoreCase(cl.getClaimNum())) {
				    		   isFound = true;
				    	        cl.seeDetails();
				    	        System.out.println("Press any key to continue. ");
						    	userIn.nextLine();
				    	    }
				    	}
				    	if(isFound == false){
				    		System.out.println("No claim exist. ");
				    		System.out.println("========================================================");
				    		System.out.println("Press any key to continue. ");
					    	userIn.nextLine();
					    	clearScreen();
			    	    }
			    	}
			    	catch(IndexOutOfBoundsException e) {
			    		 System.out.println("No claim exist. ");
			    		 System.out.println("========================================================");
			    		 System.out.println("Press any key to continue. ");
				    	 userIn.nextLine();
				    	 clearScreen();
			    	}
		    		catch(NumberFormatException e) {
		    			 System.out.println("Invalid input ");
			    		 System.out.println("========================================================");
			    		 System.out.println("Press any key to continue. ");
					     userIn.nextLine();
					     clearScreen();
		    		}
			    	break;
			}
		}
		while(choiceOne != "8");
		userIn.close();
	}
	
}
