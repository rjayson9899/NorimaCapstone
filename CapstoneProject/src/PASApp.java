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
		int choiceTwo = 0;
		int custAccNum;
		int policyNum;
		int clNum = 0;
		int pLAH;
		String fName, lName, address;
		String effDate, bDay, licNum, dateLic;
		String carMake, carModel, carYear, carType, carPrice, carFuelType, carColor;
		String accDate, addDate, depAcc, depDmgV, estRep;  
		
		Scanner userIn = new Scanner(System.in);
		ArrayList<CustomerAccount> cust = new ArrayList<>();
		Policy policy = new Policy();
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
			    		System.out.println("No Vacant account number available. ");
			    	}
			    	else {
			    		clearScreen();
			    		cust.add(new CustomerAccount());
			    		System.out.println("===================================");
			    		System.out.println("Enter your first name: ");
			    		fName = userIn.nextLine();
			    		System.out.println("Enter your last name: ");
			    		lName = userIn.nextLine();
			    		System.out.println("Enter your address: ");
			    		System.out.println("===================================");
			    		address = userIn.nextLine();
			    		System.out.println("Your Account number is: " + cust.size());
				    	cust.get(z).createAcc(fName, lName, address);
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
			    	}
			    	else {
			    		System.out.println("Enter effective date: (ex. Jan 01 1991) ");
			    		effDate = userIn.nextLine();
			    		cust.get(z).pol.get(0).setExpDate(effDate);
			    		System.out.println("Is the policy holder the account holder? ");
			    		pLAH = userIn.nextInt();
			    		if(pLAH == 1) {
			    			System.out.println("===================================");
			    			System.out.println("Enter the birthday: ");
			    			bDay = userIn.nextLine();
			    			cust.get(z).pol.get(0).polyHol.setBDay(bDay);
			    			System.out.println("Driver's license number: ");
			    			licNum = userIn.nextLine();
			    			cust.pol.get(0).polyHol.setdLicNum(licNum);
				    		System.out.println("Enter the date issued of the license: ");
				    		System.out.println("===================================");
				    		dateLic = userIn.nextLine();
				    		cust.pol.get(0).polyHol.setDateLic(dateLic);
			    		}
			    		else {
				    		//policy holder details
			    			System.out.println("===================================");
				    		System.out.println("Enter the first name: ");
				    		fName = userIn.nextLine();
				    		cust.pol.get(0).polyHol.setfName(fName);
				    		System.out.println("Enter the last name: ");
				    		lName = userIn.nextLine();
				    		cust.pol.get(0).polyHol.setlName(lName);
				    		System.out.println("Enter the birthday: ");
				    		bDay = userIn.nextLine();
				    		cust.pol.get(0).polyHol.setBDay(bDay);
				    		System.out.println("Enter the address: ");
				    		address = userIn.nextLine();
				    		cust.pol.get(0).polyHol.setAddress(address);
				    		System.out.println("Driver's license number: ");
				    		licNum = userIn.nextLine();
				    		cust.pol.get(0).polyHol.setdLicNum(licNum);
				    		System.out.println("Enter the date issued of the license: ");
				    		System.out.println("===================================");
				    		dateLic = userIn.nextLine();
				    		cust.pol.get(0).polyHol.setDateLic(dateLic);
			    		}
			    		//vehicle details
			    		System.out.println("===================================");
			    		System.out.println("Enter the car make: ");
			    		carMake = userIn.nextLine();
			    		cust.pol.get(0).car.setMake(carMake);
			    		System.out.println("Enter the car model: ");
			    		carModel = userIn.nextLine();
			    		cust.pol.get(0).car.setModel(carModel);
			    		System.out.println("Enter the year: ");
			    		carYear = userIn.nextLine();
			    		cust.pol.get(0).car.setYear(carYear);
			    		System.out.println("Enter the car type: ");
			    		carType = userIn.nextLine();
			    		cust.pol.get(0).car.setType(carType);
			    		System.out.println("Enter the fuel type: ");
			    		carFuelType = userIn.nextLine();
			    		cust.pol.get(0).car.setFuelType(carFuelType);
			    		System.out.println("Enter the purchase price: ");
			    		carPrice = userIn.nextLine();
			    		cust.pol.get(0).car.setPrice(carPrice);
			    		System.out.println("Enter the color: ");
			    		System.out.println("===================================");
			    		carColor = userIn.nextLine();
			    		cust.pol.get(0).car.setColor(carColor);
			    		
			    		//quote policy
			    		System.out.println("The policy will cost about: ");
			    		System.out.println("Would you like to buy the policy? ");
			    		pLAH = userIn.nextInt();
			    		if(pLAH == 1) {
			    			System.out.println("Policy cancelled. ");
			    		}
			    		else {
			    			System.out.println("Policy created. ");
			    			//send data to database
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
			    	cust.searchCust(fName, lName);
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
