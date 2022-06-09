/**
 * Norima Capstone Project, PASApp Client Code
 * 
 * For the Norima Capstone project, the student is assigned to create a console-based
 * Policy and Claims Administration System based on the specifications provided in the 
 * Robertson Brightspace Java 102 Part 2 course.
 * 
 * This class file covers the main functionality of the project. It is from here where 
 * the program will interface with other designed classes that cover the various aspects
 * of the PAS system.
 * 
 * @author Roger Jayson M. Mendez III
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PASApp {
	static File filePath = new File("src/test.txt");
	private static Scanner in = new Scanner(System.in);;
	
	public static void main(String[] args) {
		ArrayList<CustomerAccount> customerList = new ArrayList<CustomerAccount>();
		ArrayList<Claim> claimList = new ArrayList<Claim>();
		Policy tempPolicy;
		CustomerAccount currentAccount;
		Claim tempClaim;
		LocalDate effectiveDate;
		String firstName, lastName, address, strIn;
		int choice, uniqueId, inputId;
		boolean foundHit, isExpired;
		
		// Debug Function: Enable testing program via inputs from a file
		// ====================================================================================================
		System.out.print("Test by file? [y for yes]: ");
		strIn = in.nextLine();
		
		if (strIn.equalsIgnoreCase("y")) {
			try {
				in = new Scanner(filePath);
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		// ====================================================================================================
		
		do {
			printMenu();
			choice = getValidInt("Enter your choice: ");
			
			switch (choice) {
				/*
				 * Generates a new account and stores in program
				 * 
				 * Before taking inputs, the program will generate a unique ID
				 * based on what is available in the system. This ID is generated
				 * through a class method in the CustomerAccount class. It's behavior
				 * will be thoroughly explained in the class file. The program will
				 * proceed if a valid unique ID is returned. The only time it will
				 * return an invalid ID is when the system has accounts occupying all
				 * ID spaces. In this case, account creation will be aborted. Otherwise,
				 * the program will proceed as follows:
				 * 
				 * Takes 3 inputs:
				 * 		(String)	First Name	- first name of customer to register, cannot be blank
				 * 		(String)	Last Name	- last name of customer to register, cannot be blank
				 * 		(String)	Address		- address of customer to register, cannot be blank
				 * 
				 * Inputed first name and last name will be compared to 
				 * names already present in the system. If a match for both
				 * is found, the program will abort the creation of the new
				 * account. Otherwise, a new account will be made along with
				 * an automated unique ID.
				 */
				case 1:
					uniqueId = CustomerAccount.generateUniqueId(customerList);
					
					if (uniqueId >= 0) {
						firstName = getStringNonEmpty("Input First Name: ");
						lastName = getStringNonEmpty("Input Last Name: ");
						address = getStringNonEmpty("Input Address: ");
						
						foundHit = false;
						
						for (CustomerAccount custObj: customerList) {
							if (custObj.getFirstName().equalsIgnoreCase(firstName) && custObj.getLastName().equalsIgnoreCase(lastName)) {
								System.out.println("Account name taken");
								foundHit = true;
							}
						}
						
						if (!foundHit) {
							customerList.add(new CustomerAccount(uniqueId, firstName, lastName, address));
							System.out.printf("Account Registered with account Number %04d\n", uniqueId);
						}
					}
					else {
						System.out.println("No space left to add a new account");
					}
					
					break;
					
					
				/*
				 * Quotes and/or Creates a Policy based on a series of inputs
				 * 
				 * Will first generate a unique ID for the policy. Unique ID is generated
				 * through a class method available in the Policy class. If the method 
				 * does not return a valid ID, policy creation will be aborted. This would
				 * mean the unique ID space for policies has been fully occupied and can no
				 * longer accommodate another entry.
				 * 
				 * To create a Policy, a customer account is required. The program will ask
				 * for the ID of the account where the Policy will be created on. Failure to
				 * provide a valid ID will result in the program aborting this process.
				 * 
				 * The first step in the creation of the policy is to set the policy holder.
				 * While the customer account itself can be the holder, the program also
				 * accommodates custom named account holders. The decision whether the customer
				 * itself or a custom name will be used as the holder will be decided by
				 * user input. Inputting 'Y' regardless of case will result in the customer
				 * account being the basis of the policy holder name. Any other input, including
				 * blank string, will result in the program asking for a custom name. The handling
				 * of inputs for creating a policy holder instance will be done through helper methods
				 * found after the main method.
				 * 
				 * Inputs for the above process:
				 * 		(String) choice	- input if to use customer account or custom name
				 * For information on the inputs for policy holder, refer to the following helper method:
				 * 		> makeHolder()			- For creating a policy holder with custom name
				 * 		> makeHolder(custObj)	- For creating a policy holder using customer account name
				 * 
				 * After creating a policy holder, the program will require the creation of one vehicle
				 * to tie the policy with. The first vehicle is mandatory. For inputs, refer to the respective
				 * helper method. After inputting the first vehicle, the program will ask if another vehicle
				 * is to be added. An input of "Y", regardless of case, will result in another loop to get
				 * the details of the next vehicle. Any other inputs, including whitespace, will allow the
				 * program to proceed.
				 * 
				 * Inputs for the above process:
				 * 		(String)	choice	- input if to add another vehicle
				 * For information on the inputs for policy holder, refer to the following helper method:
				 * 		> makeVehicle()			- For creating a vehicle
				 * 
				 * Finally, a quote is generated. For logic on how the quote is generated, refer to
				 * documentation on Policy instance method generateQuote(). A prompt will ask the user
				 * if they wish to get the policy. An input of "Y", regardless of case, will register the 
				 * Policy into the account. Any other inputs, will result in the disposal of the generated
				 * Policy so far. In the event that the user accepts the policy, the effective date is
				 * immediately set to the current system date.
				 * 
				 * Inputs for the above process:
				 * 		(String)	choice	- input if to accept quoted policy
				 */
				case 2:
					uniqueId = Policy.generateUniqueId(customerList);
					if (uniqueId >= 0) {
						inputId = getValidInt("Input Account Number to create Policy in: ");
						foundHit = false;
						currentAccount = null;
						
						for (CustomerAccount acct: customerList) {
							if (acct.getAccountNumber() == inputId) {
								currentAccount = acct;
								foundHit = true;
								break;
							}
						}
					
						if (foundHit) {
							tempPolicy = new Policy(uniqueId);
							
							System.out.println("Set Current account as holder? [y for yes]: ");
							strIn = in.nextLine();
							if (strIn.equalsIgnoreCase("y")) {
								tempPolicy.setPolicyHolder(makeHolder(currentAccount));
							}
							else {
								tempPolicy.setPolicyHolder(makeHolder());
							}
							
							do  {
								tempPolicy.addVehicle(makeVehicle());
								System.out.print("Input y to add another vehicle, else press enter to continue: ");
								strIn = in.nextLine();
							} while (strIn.equalsIgnoreCase("y"));
							
							tempPolicy.generateQuote();
							System.out.print("Will you get this policy? [y for yes]: ");
							strIn = in.nextLine();
							
							if (strIn.equalsIgnoreCase("y")) {
								effectiveDate = LocalDate.now();
								tempPolicy.setEffectiveDate(effectiveDate);
								currentAccount.addPolicy(tempPolicy);
								System.out.printf("Policy created with id %06d\n", uniqueId);
							}
							else {
								System.out.println("Policy buy cancelled.");
							}
						}
						else {
							System.out.println("No account found");
						}
					}
					else {
						System.out.println("No space left to add a new policy");
					}
					break;
					
				/*
				 * Cancel an active policy
				 * 
				 * Input an ID number corresponding to the Policy to cancel. If a match
				 * is found, the program will verify its status. If the Policy is still valid,
				 * The program will proceed with canceling it. If the Policy is already expired,
				 * The program will abort the cancellation process. If no match is found, the
				 * program will return back to the main menu.
				 * 
				 * Inputs:
				 * 		(int)	inputID - The ID of the Policy to cancel
				 */
				case 3:
					inputId = getValidInt("Input Policy Number to cancel: ");
					foundHit = false;
					
					for (CustomerAccount custObj: customerList) {
						if (custObj.hasPolicy(inputId)) {
							if (custObj.cancelAccountPolicy(inputId)) {
								System.out.printf("Policy %06d Cancelled\n", inputId);
							}
							else {
								System.out.println("Policy already expired");
							}
							foundHit = true;
						}
					}
					
					if (!foundHit) {
						System.out.println("No match found");
					}
					
					break;
					
				/*
				 * File a claim using Policy ID
				 * 
				 * Input an ID number corresponding to the Policy to file the claim with.
				 * If no match is found, the program returns back to the main menu. Otherwise,
				 * the program will then check if the corresponding policy has expired. If the
				 * Policy is expired, program will return back to the main menu. Else, program
				 * will proceed to generate a unique claim ID. Unique ID is generated through
				 * class method available in Claim class. if a valid ID is returned, the program
				 * will begin gathering necessary details before registering claim into the system.
				 * Otherwise, the program will abort since an invalid ID indicates that the ID space
				 * of the system has run out.
				 * 
				 * Inputs:
				 * 		(int)	inputID - ID of Pollicy to file claim against
				 * For information on the inputs to make a claim, refer to the following helper method:
				 * 		> makeClaim();
				 */
				case 4:
					inputId = getValidInt("Input Policy Number to file claim: ");
					foundHit = false;
					isExpired = false;
					currentAccount = null;
					
					for (CustomerAccount custObj: customerList) {
						if (custObj.hasPolicy(inputId)) {
							if (custObj.getPolicyMatchingId(inputId).isExpired()) {
								isExpired = true;
							}
							else {
								currentAccount = custObj;
								foundHit = true;
							}
						}
					}
					
					if (foundHit) {
						uniqueId = Claim.generateUniqueId(claimList);
						if (uniqueId >= 0) {
							tempClaim = makeClaim(uniqueId);
							claimList.add(tempClaim);
							System.out.printf("Claim added with id %s\n", tempClaim.getClaimNumber());
						}
						else {
							System.out.println("No space left to add a new claim");
						}
					}
					else if (isExpired) {
						System.out.println("Policy selected is expired");
					}
					else {
						System.out.println("No match found");
					}
					
					break;
					
				/*
				 * Find Customer Account by First Name and Last Name
				 * 
				 * Takes 2 Inputs:
				 * 		(String)	First Name	- First name of account to find
				 * 		(String)	Last Name	- Last name of account to find
				 * 	
				 * Using these inputs, the program will search all customer accounts
				 * for a match. If a match is found, complete details matching the 
				 * account will be printed. Otherwise, program will output "no match."
				 */
				case 5:
					firstName = getStringNonEmpty("Input First Name: ");
					lastName = getStringNonEmpty("Input Last Name: ");
					foundHit = false;
					
					
					for (CustomerAccount custObj: customerList) {
						if (custObj.getFirstName().equalsIgnoreCase(firstName) && custObj.getLastName().equalsIgnoreCase(lastName)) {
							CustomerAccount.printCustomerAccountHeader();
							custObj.printCustomerAccountDetails();
							foundHit = true;
						}
					}
					
					if(!foundHit) {
						System.out.println("No match found");
					}
					break;
					
				/*
				 * Find Policy by ID
				 * 
				 * Takes 1 input:
				 * 		(int)	inputID	- ID of policy to find
				 * 
				 * Program will check all customer accounts for a matching policy ID.
				 * If a match is found, all related information to the policy will be 
				 * printed. Otherwise, program will output "no match."
				 */
				case 6:
					inputId = getValidInt("Input Policy Number to find: ");
					foundHit = false;
					
					for (CustomerAccount custObj: customerList) {
						if (custObj.hasPolicy(inputId)) {
							CustomerAccount.printPolicyHeader();
							custObj.printPolicyMatchingId(inputId);
							foundHit = true;
						}
					}
					
					if(!foundHit) {
						System.out.println("No match found");
					}
					break;
				
				/*
				 * Find claim by ID
				 * 
				 * Takes 1 Input
				 * 		(String)	strIn - Claim ID of claim to find
				 * 
				 * Input must match the format Cxxxxxx where x is a digit from 0-9.
				 * If the input does not match the format, the program will require
				 * the user to input again until a valid input is received. The program
				 * will then search for a claim matching the ID. If a match is found, all
				 * information related to the claim is printed. Otherwise, program will 
				 * output "no match."
				 */
				case 7:
					do {
						System.out.print("Input Claim Number to find: ");
						strIn = in.nextLine();
						// Debug
						//System.out.println(strIn);
						if ((strIn.length() != 7) && (strIn.charAt(0) != 'C')) {
							System.out.println("Invalid input. Follow the format Cxxxxxx where x is a value between 0 - 9.");
						}
						else {
							try {
								inputId = Integer.parseInt(strIn.substring(1));
							}
							catch (NumberFormatException e) {
								System.out.println("Invalid Claim ID value");
								strIn = "";
							}
						}
					} while((strIn.length() != 7) && (strIn.charAt(0) != 'C'));
					
					
					foundHit = false;
					
					for (Claim clmObj: claimList) {
						if (clmObj.getClaimNumber().equals(strIn)) {
							Claim.printClaimHeader();
							clmObj.printClaimDetails();
							foundHit = true;
						}
					}
					
					if(!foundHit) {
						System.out.println("No match found");
					}
					break;
				case 8:
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Invalid Choice");
			}
			
			if (choice != 8) {
				System.out.println("Press enter to continue...");
				in.nextLine();
				System.out.println();
			}
			
		} while (choice != 8);
		
		//DEBUG
		// ===========================================================================================
		System.out.println("Data dump");
		CustomerAccount.printCustomerAccountHeader();
		for (CustomerAccount cstObj: customerList) {
			cstObj.printCustomerAccountDetails();
		}
		CustomerAccount.printPolicyHeader();
		for (CustomerAccount cstObj: customerList) {
			cstObj.printPolicies();
		}
		CustomerAccount.printVehicleHeader();
		for(CustomerAccount cstObj: customerList) {
			cstObj.printVehicles();
		}
		CustomerAccount.printPolicyHolderHeader();
		for(CustomerAccount cstObj: customerList) {
			cstObj.printPolicyHolders();
		}
		Claim.printClaimHeader();
		for (Claim clmObj: claimList) {
			clmObj.printClaimDetails();
		}
		// ===========================================================================================
		
	}
	
	/**
	 * Prints the menu of the PAS System
	 * 
	 * @param none
	 */
	private static void printMenu() {
		System.out.println("==================================");
		System.out.println("||          PAS System          ||");
		System.out.println("==================================");
		System.out.println("1. Create New Customer Account");
		System.out.println("2. Get Policy Quote and Buy Policy");
		System.out.println("3. Cancel Policy");
		System.out.println("4. File Accident Claim");
		System.out.println("5. Search Customer Account");
		System.out.println("6. Search Policy");
		System.out.println("7. Search Claim");
		System.out.println("8. Exit");
		System.out.println("==================================");
	}
	
	/**
	 * Gets input from users and creates an instance of a Vehicle
	 * 
	 * Inputs:
	 * 		(String)	make			- Maker of the vehicle
	 * 		(String)	model 			- Model of the vehicle
	 * 		(int)		year			- Year vehicle was made
	 * 		(String)	type			- Vehicle type
	 * 		(String)	fuel type		- Fuel type of vehicle
	 * 		(double)	purchase price	- Price the vehicle was purchased
	 * 
	 * Uses input validator methods. See following for documentation
	 * 		> getStringNonEmpty(message)
	 * 		> getValidInt(message)
	 * 		> getValidDouble(message)
	 * 
	 * @return Vehicle instance
	 */
	private static Vehicle makeVehicle() {
		String make;
		String model;
		int year;
		String type;
		String fuelType;
		double purchasePrice;
		
		make = getStringNonEmpty("Enter make: ");
		model = getStringNonEmpty("Enter model: ");
		year = getValidInt("Enter year: ");
		type = getStringNonEmpty("Enter type: ");
		fuelType = getStringNonEmpty("Enter fuel type: ");
		purchasePrice = getValidDouble("Enter purchase price: ");
		
		return new Vehicle(make, model, year, type, fuelType, purchasePrice);
	}
	
	/**
	 * Gets Input from user and creates a PolicyHolder instance.
	 * 
	 * First name and last name of instance is retrieved from CustomerAccount
	 * object passed as parameter.
	 * 
	 * Inputs:
	 * 		(LocalDate)	birth date		- Date of birth of customer, ref. to getDate(message) method.
	 * 		(String)	license number	- License number of customer
	 * 		(LocalDate)	license date	- Date customer license was issued
	 * 
	 * Uses input validator methods. See following for documentation
	 * 		> getDate(message)
	 * 		> getStringNonEmpty(message)
	 * 
	 * @param custObj - CustomerAccount instance where name is taken
	 * @return PolicyHolder instance
	 */
	private static PolicyHolder makeHolder(CustomerAccount custObj) {
		String licenseNumber;
		LocalDate birthDate, licenseDate;
		
		birthDate = getDate("birth");
		licenseNumber = getStringNonEmpty("Input driver's license number: ");
		licenseDate = getDate("license issue");
		
		return new PolicyHolder(custObj, birthDate, licenseNumber, licenseDate);
	}
	
	/**
	 * Gets Input from user and creates a PolicyHolder instance.
	 * 
	 * Inputs:
	 * 		(String)	first name		- Get first name of custom policy holder
	 * 		(String)	first name		- Get last name of custom policy holder
	 * 		(LocalDate)	birth date		- Date of birth of policy holder to be set, ref. to getDate(message) method.
	 * 		(String)	license number	- License number of customer
	 * 		(LocalDate)	license date	- Date policy holder license was issued, ref. to getDate(message) method.
	 * 
	 * Uses input validator methods. See following for documentation
	 * 		> getDate(message)
	 * 		> getStringNonEmpty(message)
	 * 
	 * @return PolicyHolder instance
	 */
	private static PolicyHolder makeHolder() {
		String licenseNumber, firstName, lastName;
		LocalDate birthDate, licenseDate;
		
		firstName = getStringNonEmpty("Enter first name: ");
		lastName = getStringNonEmpty("Enter last name: ");
		birthDate = getDate("birth");
		licenseNumber = getStringNonEmpty("Input license number: ");
		licenseDate = getDate("license issue");
		
		return new PolicyHolder(firstName, lastName, birthDate, licenseNumber, licenseDate);
	}
	
	/**
	 * Gets inputs from user and creates a Claim instance
	 * 
	 * Inputs:
	 * 		(LocalDate)	accident date			- Date accident took place, ref. to getDate(message) method.
	 * 		(String)	accident address		- location of accident
	 * 		(String)	accident description	- description of how accident occurred
	 * 		(String)	accident damage			- description of damage
	 * 		(Double)	repair costs			- Repair costs of damaged vehicle
	 * 		
	 * Uses input validator methods. See following for documentation
	 * 		> getDate(message)
	 * 		> getStringNonEmpty(message)
	 * 		> getValidDouble(message)
	 * 
	 * @param uniqueId - ID of claim to be instantiated
	 * @return Claim instance
	 */
	private static Claim makeClaim(int uniqueId) {
		LocalDate accidentDate;
		String accidentAddress, accidentDescription, accidentDamage;
		double repairCosts;
		
		accidentDate = getDate("accident");
		accidentAddress = getStringNonEmpty("Enter accident address: ");
		accidentDescription = getStringNonEmpty("Enter accident description: ");
		accidentDamage = getStringNonEmpty("Enter damage description: ");
		repairCosts = getValidDouble("Enter repair costs: ");
		
		return new Claim(uniqueId, accidentDate, accidentAddress, accidentDescription, accidentDamage, repairCosts);
	}
	
	/**
	 * Generates a LocalDate instance based on user input
	 * 
	 * Inputs:
	 * 		(int)	year	-	Year of date to create, limited to 1900 to present year
	 * 		(int)	month	-	Month of date to create, limited to 1 - 12
	 * 		(int)	day		-	Day of date to create, limited to valid days of month inputed
	 * 
	 * Input validation via exception handling and conditionals is present for
	 * each input. Input order follow year then month then finally day. Additionally,
	 * day validity is determined by attempting to instantiate a LocalDate instance
	 * using prior year and month input. The day is invalid if LocalDate throws an
	 * exception. For each step in the input chain, invalid inputs will force the user
	 * to input another value until a valid value is placed.
	 * 
	 * The message prompt shown to the user is when asking for input is customized through 
	 * the String parameter "type."
	 * 
	 * i.e. type = "new", then output is "Enter new year: "
	 * 
	 * @param type - Description of date type, in string
	 * @return LocalDate instance
	 */
	private static LocalDate getDate(String type) {
		LocalDate date = LocalDate.now();
		int month = 0;
		int day = 0;
		int year = 0;
		int yearNow = LocalDate.now().getYear();
		boolean isInvalid = true;
		
		do {
			try {
				System.out.print("Enter " + type + " year: ");
				year = in.nextInt();
				// Debug
				//System.out.println(year);
				if (year < 1900 || year > yearNow) {
					System.out.println("Only years between 1900 and " + yearNow + " are valid");
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input");
				year = -1;
			}
			finally {
				in.nextLine();
			}
		} while (year < 1900 || year > yearNow);
		
		do {
			try {
				System.out.print("Enter " + type + " month: ");
				month = in.nextInt();
				// Debug
				//System.out.println(month);
				if (month > 12 || month < 1) {
					System.out.println("Only values 1 - 12 are valid");
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input");
			}
			finally {
				in.nextLine();
			}
		} while(month > 12 || month < 1);
		
		do {
			try {
				System.out.print("Enter " + type + " day: ");
				day = in.nextInt();
				// Debug
				//System.out.println(day);
				date = LocalDate.of(year, month, day);
				isInvalid = false;
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input");
			}
			catch (DateTimeException e) {
				System.out.println("Invalid day for month");
			}
			finally {
				in.nextLine();
			}
			
		} while (isInvalid);		
		
		return date;
	}
	
	/**
	 * Creates a string instance after verifying if input is not blank.
	 * Can display custom message requesting what type of input string is desired.
	 * 
	 * Input:
	 * 		(String)	strIn	- String to be verified.
	 * 
	 * Program will ask for a new input if a blank string is inputed.
	 * 
	 * @param message - Custom message to display for every input attempt
	 * @return String instance
	 */
	private static String getStringNonEmpty(String message) {
		String strIn;
		
		do {
			System.out.print(message);
			strIn = in.nextLine();
			if (strIn.equals("")) {
				System.out.println("Entry cannot be blank");
			}
		} while(strIn.equals(""));
		
		// Debug
		//System.out.println(strIn);
		
		return strIn;
	}
	
	/**
	 * Return inputed integer after verifying if input is valid.
	 * Can display custom message requesting what type of input is desired.
	 * 
	 * Input:
	 * 		(int)	getInt	- integer to be verified.
	 * 
	 * Verification is done via exception handling. Invalid inputs involve values
	 * that will trigger a InputMismatchException whenever Scanner.nextInt() is called
	 * 
	 * @param message
	 * @return int - validated integer
	 */
	private static int getValidInt(String message) {
		boolean isInvalid = true;
		int getInt = 0;
		
		do {
			System.out.print(message);
			try {
				getInt = in.nextInt();
				isInvalid = false;
			}
			catch(InputMismatchException e) {
				System.out.println("Input is not an integer");
			}
			finally {
				in.nextLine();
			}
		} while (isInvalid);
		
		// Debug
		//System.out.println(getInt);
		
		return getInt;
	}
	
	/**
	 * Return inputed double after verifying if input is valid.
	 * Can display custom message requesting what type of input is desired.
	 * 
	 * Input:
	 * 		(double)	getInt	- integer to be verified.
	 * 
	 * Verification is done via exception handling. Invalid inputs involve values
	 * that will trigger a InputMismatchException whenever Scanner.nextDouble() is called
	 * 
	 * @param message
	 * @return double - validated double
	 */
	private static double getValidDouble(String message) {
		boolean isInvalid = true;
		double getDouble = 0.0;
		
		do {
			System.out.print(message);
			try {
				getDouble = in.nextInt();
				isInvalid = false;
			}
			catch(InputMismatchException e) {
				System.out.println("Input is not valid");
			}
			finally {
				in.nextLine();
			}
		} while (isInvalid);
		
		// Debug
		//System.out.println(getDouble);
		
		return getDouble;
	}
}
