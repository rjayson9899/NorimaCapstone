package policyTest;

import java.util.Scanner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PASApp {
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) throws ParseException {
		
		int choice = 0;
		
		do {
			//main menu user interface
			clrscrn();
			System.out.println("-------------------------------");
			System.out.println("          PAS System           ");
			System.out.println("-------------------------------");
			System.out.println("[1] Create Customer Account");
			System.out.println("[2] Get quote and Buy Policy");
			System.out.println("[3] Cancel Policy");
			System.out.println("[4] File Accident Claim");
			System.out.println("[5] Search Customer Account");
			System.out.println("[6] Search Policy");
			System.out.println("[7] Search Claim");
			System.out.println("[8] Exit");
			System.out.println("-------------------------------");
			System.out.print("Input number of choice: ");
			choice = input.nextInt();
			
			switch(choice) {
			
				case 1:
					clrscrn();
					createAccount();
					break;
					
				case 2:
					clrscrn();
					quotePolicy();
					break;
					
					
					
				case 3:
					clrscrn();
					cancelPolicy();
					break;
					
				case 4:
					clrscrn();
					fileClaim();
					break;
					
					
				case 5:
					clrscrn();
					searchCustomer();
					break;
					
					
				case 6:
					clrscrn();
					searchPolicy();
					break;
					
				case 7:
					clrscrn();
					searchClaim();
					break;
				
				default:
					clrscrn();
					
				
			}
			
		}while(choice!=8);
		
		input.close();
	}
	
	public static void clrscrn() {
		for(int x = 0; x < 30; x++) { // for clear screen on eclipse console
			System.out.println();
		}
	}
	
	public static void createAccount() {
		input.nextLine();
		System.out.println("\n-------------------------------");
		System.out.println("Creating account");
		System.out.println("-------------------------------");
		System.out.print("First Name: ");
		String fname = input.nextLine();
		System.out.print("Last Name: ");
		String lname = input.nextLine();
		System.out.print("Address: ");
		String address = input.nextLine();
		
		CustomerAccount customer = new CustomerAccount(fname, lname, address);
		System.out.println(customer.getFname());
		System.out.println(customer.getLname());
		
		System.out.println("Created account!");
	}
	
	public static void quotePolicy() throws ParseException {
		System.out.println("\n-------------------------------");
		System.out.println("Quoting policy");
		System.out.println("-------------------------------");
		System.out.print("Input account number: ");
		int accNum = input.nextInt();
		
		//Add checker if account exist
		
		
		//Policy Holder Details
		
		
		clrscrn();
		System.out.println("\n-------------------------------");
		System.out.println("Policy Holder Details");
		System.out.println("-------------------------------");

		System.out.print("Input effective date(dd/mm/yyyy): ");
		input.nextLine();
		String date = input.nextLine();
		Date effectDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		
		//Add checker if the one who creates policy is the account holder or different person
		
		System.out.print("First Name: ");
		String fnamePol = input.nextLine();
		System.out.print("Last Name: ");
		String lnamePol = input.nextLine();
		System.out.print("Input birth date(dd/mm/yyyy): ");
		String birthDateString = input.nextLine();
		Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(birthDateString);
		System.out.println("Input license number: ");
		String license = input.nextLine();
		System.out.print("Input license date issued(dd/mm/yyyy): ");
		String licenseDateString = input.nextLine();
		Date licenseDate = new SimpleDateFormat("dd/MM/yyyy").parse(licenseDateString);
		
		
		//Vehicle Details
		
		clrscrn();
		System.out.println("\n-------------------------------");
		System.out.println("Vehicle Details");
		System.out.println("-------------------------------");
		System.out.print("Make: ");
		String make = input.nextLine();
		System.out.print("Model: ");
		String model = input.nextLine();
		System.out.print("Year: ");
		int year = input.nextInt();
		
		System.out.println("Choose which type your vehicle is: ");
		System.out.println("[1] 4-door sedan");
		System.out.println("[2] 2-door sports car, SUV, or truck");
		System.out.println("-------------------------------");
		System.out.print("Your type of vehicle: ");
		int type = input.nextInt();
		
		System.out.println("Choose which type of fuel: ");
		System.out.println("[1] Diesel");
		System.out.println("[2] Electric");
		System.out.println("[3] Petrol");
		System.out.println("-------------------------------");
		System.out.print("Your type of fuel: ");
		int fuel = input.nextInt();
		
		System.out.print("Purchase price: ");
		double price = input.nextDouble();
		System.out.print("Color: ");
		input.nextLine();
		String color = input.nextLine();
		double premium = 200;
		
		System.out.println("Premium is " + premium);
		
		
		
	}
	
	public static void cancelPolicy() {
		System.out.println("\n-------------------------------");
		System.out.println("Cancel Policy");
		System.out.println("-------------------------------");
		System.out.print("Input Policy Number: ");
		int policy = input.nextInt();
		
		System.out.println("Successfully cancelled policy!");
	}
	
	public static void fileClaim() throws ParseException {

		System.out.println("\n-------------------------------");
		System.out.println("File Accident Claim");
		System.out.println("-------------------------------");
		System.out.print("Input Policy Number: ");
		int policy1 = input.nextInt();
		
		//Add condition if policy exist
		clrscrn();
		input.nextLine();
		System.out.println("\n-------------------------------");
		System.out.println("Claim Details");
		System.out.println("-------------------------------");
		System.out.print("Input accident date(dd/mm/yyyy): ");
		String accidentDateString = input.nextLine();
		Date accidentDate = new SimpleDateFormat("dd/MM/yyyy").parse(accidentDateString);
		System.out.print("Address of where the accident happened: ");
		String accidentAdd = input.nextLine();
		System.out.print("Description of the accident: ");
		String descriptionAccident = input.nextLine();
		System.out.print("Description of damage to vehicle: ");
		String descriptionDamage = input.nextLine();
		System.out.print("Estimated cost of repairs: ");
		double estCost = input.nextDouble();
		
		
		
		System.out.println("Successfully Claimed policy!");
	}
	
	public static void searchCustomer() {
		System.out.println("\n-------------------------------");
		System.out.println("Search Customer");
		System.out.println("-------------------------------");
		System.out.println("How would you like to search for customer?");
		System.out.println("[1] Using first and last name");
		System.out.println("[2] Using account number");
		System.out.println("-------------------------------");
		System.out.print("Input number of choice: ");
		int search = input.nextInt();
		
		if(search == 1) {
			input.nextLine();
			System.out.print("Input first name: ");
			String fNameSearch = input.nextLine();
			System.out.print("Input last name: ");
			String lNameSearch = input.nextLine();
			
		}
		
		else if(search == 2) {
			System.out.print("Input account number: ");
			int accountNum = input.nextInt();
		}
		
		else {
			System.out.println("Wrong Input!");
		}
	}
	
	public static void searchPolicy() {
		
		System.out.println("\n-------------------------------");
		System.out.println("Search Policy");
		System.out.println("-------------------------------");
		System.out.print("Input policy number: ");
		int policyNum = input.nextInt();
		System.out.println(policyNum);
		
	}
	
	public static void searchClaim() {
		System.out.println("\n-------------------------------");
		System.out.println("Search Claim");
		System.out.println("-------------------------------");
		System.out.print("Input claim number: ");
		int claimNum = input.nextInt();
		System.out.println(claimNum);
	}
	
}
