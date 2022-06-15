import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Norima Capstone Project, PASApp Client Code.
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

public class PASApp {
	private static File filePath = new File("src/test.txt");
	private static Scanner in = new Scanner(System.in);
	private static boolean byFile = false;
	
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
				byFile = true;
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
				return;
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
						System.out.println("\nInput customer details");
						System.out.println("==================================");
						firstName = getStringWord("Input First Name: ");
						lastName = getStringWord("Input Last Name: ");
						address = getStringWord("Input Address: ");
						System.out.println("==================================\n");
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
						inputId = getValidBoundedInt("Input Account Number to create Policy in: ", 4, true);
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
							
							System.out.print("Set Current account as holder? [y for yes]: ");
							strIn = in.nextLine();
							// Debug
							if (byFile) System.out.println(strIn);
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
								// Debug
								if (byFile) System.out.println(strIn);
							} while (strIn.equalsIgnoreCase("y"));
							
							tempPolicy.generateQuote();
							System.out.print("Will you get this policy? [y for yes]: ");
							strIn = in.nextLine();
							
							// Debug
							if (byFile) System.out.println(strIn);
							
							if (strIn.equalsIgnoreCase("y")) {
								System.out.print("Set policy to be effective immediately? [y for yes]: ");
								strIn = in.nextLine();
								
								// Debug
								if (byFile) System.out.println(strIn);
								
								if (strIn.equalsIgnoreCase("y")) {
									effectiveDate = LocalDate.now();
									tempPolicy.setEffectiveDate(effectiveDate);
									currentAccount.addPolicy(tempPolicy);
								}
								else {
									do {
										effectiveDate = getDate("policy effective");
										if (effectiveDate.compareTo(LocalDate.now()) < 0) {
											System.out.println("Date cannot be before " + LocalDate.now());
										}
									} while (effectiveDate.compareTo(LocalDate.now()) < 0);
									tempPolicy.setEffectiveDate(effectiveDate);
									currentAccount.addPolicy(tempPolicy);
								}
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
					inputId = getValidBoundedInt("Input Policy Number to cancel: ", 6, true);
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
				 * 		(int)	inputID - ID of Policy to file claim against
				 * For information on the inputs to make a claim, refer to the following helper method:
				 * 		> makeClaim();
				 */
				case 4:
					inputId = getValidBoundedInt("Input Policy Number to file claim: ", 6, true);
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
					firstName = getStringWord("Input First Name: ");
					lastName = getStringWord("Input Last Name: ");
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
					inputId = getValidBoundedInt("Input Policy Number to find: ", 6, true);
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
						if (byFile) System.out.println(strIn);
						if ((strIn.length() != 7) || (strIn.charAt(0) != 'C')) {
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
					} while((strIn.length() != 7) || (strIn.charAt(0) != 'C'));
					
					
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
	 * 		> getStringNonEmpty()
	 * 		> getValidInt()
	 * 		> getValidDouble()
	 * 
	 * @return Vehicle instance
	 */
	private static Vehicle makeVehicle() {
		int yearNow = LocalDate.now().getYear();
		String make;
		String model;
		int year;
		String type;
		String fuelType;
		double purchasePrice;
		
		System.out.println("\nInput vehicle details");
		System.out.println("==================================");
		make = getStringWord("Enter make: ");
		model = getStringWord("Enter model: ");
		
		do {
			year = getValidInt("Enter year: ");
			if (year > yearNow) {
				System.out.println("Car year cannot be further from current year (" + yearNow + ")");
			}
		} while (year > yearNow);
		
		
		type = getStringWord("Enter type: ");
		fuelType = getStringWord("Enter fuel type: ");
		purchasePrice = getMoneyInput("Enter purchase price: ");
		System.out.println("==================================\n");
		
		return new Vehicle(make, model, year, type, fuelType, purchasePrice);
	}
	
	/**
	 * Gets Input from user and creates a PolicyHolder instance.
	 * 
	 * First name and last name of instance is retrieved from CustomerAccount
	 * object passed as parameter.
	 * 
	 * Inputs:
	 * 		(LocalDate)	birth date		- Date of birth of customer, refer to getDate(message) method.
	 * 		(String)	license number	- License number of customer
	 * 		(LocalDate)	license date	- Date customer license was issued
	 * 
	 * Uses input validator methods. See following for documentation
	 * 		> getDate()
	 * 		> getStringNonEmpty()
	 * 
	 * @param custObj - CustomerAccount instance where name is taken
	 * @return PolicyHolder instance
	 */
	private static PolicyHolder makeHolder(CustomerAccount custObj) {
		String licenseNumber;
		LocalDate birthDate, licenseDate;
		
		System.out.println("\nInput additional Policy Holder details");
		System.out.println("==================================");
		birthDate = getDateRequirePast("birth");
		licenseNumber = getStringWord("Input driver's license number: ");
		licenseDate = getDateRequirePast("license issue");
		System.out.println("==================================\n");
		
		return new PolicyHolder(custObj, birthDate, licenseNumber, licenseDate);
	}
	
	/**
	 * Gets Input from user and creates a PolicyHolder instance.
	 * 
	 * Inputs:
	 * 		(String)	first name		- Get first name of custom policy holder
	 * 		(String)	first name		- Get last name of custom policy holder
	 * 		(LocalDate)	birth date		- Date of birth of policy holder to be set, refer to getDate() method.
	 * 		(String)	license number	- License number of customer
	 * 		(LocalDate)	license date	- Date policy holder license was issued, refer to getDate() method.
	 * 
	 * Uses input validator methods. See following for documentation
	 * 		> getDate()
	 * 		> getStringNonEmpty()
	 * 
	 * @return PolicyHolder instance
	 */
	private static PolicyHolder makeHolder() {
		String licenseNumber, firstName, lastName;
		LocalDate birthDate, licenseDate;
		
		System.out.println("\nInput Policy Holder details");
		System.out.println("==================================");
		firstName = getStringWord("Enter first name: ");
		lastName = getStringWord("Enter last name: ");
		birthDate = getDateRequirePast("birth");
		licenseNumber = getStringWord("Input license number: ");
		licenseDate = getDateRequirePast("license issue");
		System.out.println("==================================\n");
		
		return new PolicyHolder(firstName, lastName, birthDate, licenseNumber, licenseDate);
	}
	
	/**
	 * Gets inputs from user and creates a Claim instance
	 * 
	 * Inputs:
	 * 		(LocalDate)	accident date			- Date accident took place, refer to getDate() method.
	 * 		(String)	accident address		- location of accident
	 * 		(String)	accident description	- description of how accident occurred
	 * 		(String)	accident damage			- description of damage
	 * 		(Double)	repair costs			- Repair costs of damaged vehicle
	 * 		
	 * Uses input validator methods. See following for documentation
	 * 		> getDate()
	 * 		> getStringNonEmpty()
	 * 		> getValidDouble()
	 * 
	 * @param uniqueId - ID of claim to be instantiated
	 * @return Claim instance
	 */
	private static Claim makeClaim(int uniqueId) {
		LocalDate accidentDate;
		String accidentAddress, accidentDescription, accidentDamage;
		double repairCosts;
		
		System.out.println("\nInput claim details");
		System.out.println("==================================");
		accidentDate = getDateRequirePast("accident");
		accidentAddress = getStringWord("Enter accident address: ");
		accidentDescription = getStringWord("Enter accident description: ");
		accidentDamage = getStringWord("Enter damage description: ");
		repairCosts = getMoneyInput("Enter repair costs: ");
		System.out.println("==================================\n");
		
		return new Claim(uniqueId, accidentDate, accidentAddress, accidentDescription, accidentDamage, repairCosts);
	}
	
	/**
	 * Generates a LocalDate instance based on user input
	 * 
	 * Inputs:
	 * 		(int)	year	-	Year of date to create, limited to 1900 to present year
	 * 		(int)	month	-	Month of date to create, limited to 1 - 12
	 * 		(int)	day		-	Day of date to create, limited to valid days of month and year inputed
	 * 
	 * All inputs for each date component makes use getValidBoundedInt() method. 
	 * This ensures that each input has a limited amount of digits when inputting.
	 * Description of how input length is limited will be described further down below. 
	 * For more information on getValidBoundedInt() method functionality, refer to 
	 * respective documentation.
	 * 
	 * The year method is limited to a minimum year of 1900. All inputs for year require
	 * an input that is absolutely 4 digits in length.
	 * 
	 * For month, values 01 to 12 are the only valid values which present Jan - Dec.
	 * Input requires only a maximum of 2 digits.
	 * 
	 * The day component can have a maximum of 2 digits input length. Input is validated base on 
	 * if a LocalDate instance can be generated from input. If LocalDate.of(year, month, dayOfMonth) 
	 * throws an exception, the program catches it and forces the user to input another.
	 * 
	 * The message prompt shown to the user when asking for input is customized through 
	 * the String parameter "type."
	 * i.e. if type = "new", then output is "Enter new year: "
	 * 
	 * @param type - Description of date type, in string
	 * @return LocalDate - with defined values
	 */
	private static LocalDate getDate(String type) {
		LocalDate date = LocalDate.now();
		int month = 0;
		int day = 0;
		int year = 0;
		boolean isInvalid;
		
		// Get Year
		do {
			year = getValidBoundedInt("Enter " + type + " year: ", 4, true);
			if (year < 1900) {
				System.out.println("Year must be beyond 1900");
			}
			else {
				isInvalid = false;
			}
		} while (year < 1900);
		
		// Get Month
		do {
			month = getValidBoundedInt("Enter " + type + " month (01-12): ", 2, false);
			if (month > 12 || month < 1) {
				System.out.println("Only values 1 - 12 are valid");
			}
		} while(month > 12 || month < 1);
		
		//Get Day
		isInvalid = true;
		do {
			try {
				day = getValidBoundedInt("Enter " + type + " day: ", 2, false);
				date = LocalDate.of(year, month, day);
				isInvalid = false;
			}
			catch (DateTimeException e) {
				System.out.println("Invalid day for month");
			}
		} while (isInvalid);
		
		return date;
	}
	
	/**
	 * Returns a LocalDate instance of a date from the past with current date inclusive.
	 * 
	 * Makes use of getDate() method for generating a date. The output of this method
	 * will be checked against an instance of the current date. If the date is greater
	 * than the current date, the program will require the user to input a new date.
	 * 
	 * @param type - description of date to print, refer to getDate() for how this will be used
	 * @return LocalDate - date from the past
	 */
	private static LocalDate getDateRequirePast(String type) {
		LocalDate nowDate = LocalDate.now();
		LocalDate newDate;
		
		do {
			newDate = getDate(type);
			if (newDate.compareTo(nowDate) > 0) {
				System.out.println("Date cannot be after " + nowDate);
			}
		} while (newDate.compareTo(nowDate) > 0);
		
		return newDate;
	}
	
	/**
	 * Returns a String instance after verifying if it meets the following requirements:
	 * 		> Is not blank
	 * 		> Does not consist of only whitespace
	 * 		> Does not consist of only numbers with ".", "-", and "," characters
	 * 		> Does not consist of only ".", "-", and "," characters
	 * 		> Must consist of alphabetic values. May contain digits, whitespace, ".", "-", and "," characters.
	 * 
	 * Inputed String will always be trimmed before processing.
	 * 
	 * Can display custom message that repeats for every input attempt.
	 * 
	 * Input:
	 * 		(String)	strIn	- String to be verified.
	 * 
	 * Program will ask for a new input if a blank string or a string that only is inputed.
	 * 
	 * @param message - Custom message to display for every input attempt
	 * @return String instance
	 */
	private static String getStringWord(String message) {
		String strIn;
		
		do {
			System.out.print(message);
			strIn = in.nextLine();
			
			// Debug
			if (byFile) System.out.println(strIn);
			
			strIn = strIn.trim();
			if (strIn.equals("")) {
				System.out.println("Entry cannot be blank");
			}
			else if (strIn.matches("^[,\\.\\- ]*$")) {
				System.out.println("Input cannot purely be \".\", \"-\", and \",\"");
				strIn = "";
			}
			else if (strIn.matches("^[,\\d\\.\\- ]*$")) {
				System.out.println("Input cannot purely be numbers");
				strIn = "";
			}
			else if (!(strIn.matches("^[a-zA-Z0-9,\\.\\- ]*$"))) {
				System.out.println("Input cannot contain special characters");
				strIn = "";
			}
		} while(strIn.equals(""));

		return strIn;
	}
	
	/**
	 * Return inputed integer after verifying if input is valid.
	 * Can display custom message that repeats for every input attempt.
	 * 
	 * Input:
	 * 		(String)	getIntString	- Input to be verified.
	 * 
	 * Verification is done via exception handling. Invalid inputs involve values
	 * that cannot be normally parsed by Integer.parseInt()
	 * 
	 * @param message - Custom message to display for every input attempt
	 * @return int - validated integer
	 */
	private static int getValidInt(String message) {
		boolean isInvalid = true;
		String getIntString = "";
		int parsedInt = 0;
		
		do {
			System.out.print(message);
			try {
				getIntString = in.nextLine();
				getIntString = getIntString.trim();
				// Debug
				if (byFile) System.out.println(getIntString);
				parsedInt = Integer.parseInt(getIntString);
				isInvalid = false;
			}
			catch(NumberFormatException e) {
				if (getIntString.equals("")) {
					System.out.println("Input cannot be blank");
				}
				else {
					System.out.println("Input is not valid");
				}
			}
		} while (isInvalid);
		
		return parsedInt;
	}
	
	/**
	 * Return double value that is valid for currency.
	 * Can display custom message that repeats for every input attempt.
	 * 
	 * Input:
	 * 		(String)	getDoubleString	- Input to be verified.
	 * 
	 * For an input to be valid, it must meet the following criteria:
	 * 		> Input can be parsed to double
	 * 		> Input only has 2 digits for decimal portion
	 * 		> Is not negative in value
	 * 
	 * Verification is done via exception handling and BigDecimal.scale()
	 * 
	 * @param message - Custom message to display for every input attempt
	 * @return double - validated double
	 */
	private static double getMoneyInput(String message) {
		boolean isInvalid = true;
		String getDoubleString = "";
		double parsedDouble = 0.0;
		
		do {
			System.out.print(message);
			
			try {
				getDoubleString = in.nextLine();
				getDoubleString = getDoubleString.trim();
				// Debug
				if (byFile) System.out.println(getDoubleString);
				
				parsedDouble = Double.parseDouble(getDoubleString);
				
				if (BigDecimal.valueOf(parsedDouble).scale() > 2) {
					System.out.println("Input must only have 2 digits in decimal place");
				}
				else if (parsedDouble < 0) {
					System.out.println("Input cannot be negative");
				}
				else {
					isInvalid = false;
				}
			}
			catch (NumberFormatException e) {
				if (getDoubleString.equals("")) {
					System.out.println("Input cannot be blank");
				}
				else {
					System.out.println("Invalid input! Input must consist of purely numbers");
				}
			}
			
		} while (isInvalid);
		
		return parsedDouble;
	}
	
	/**
	 * Returns inputed int value after verification.
	 * Can display custom message that repeats for every input attempt.
	 * 
	 * Has the same behavior as getValidInt() but with input length limitations.
	 * Length limit is set based on value of limit param.
	 * 
	 * If the requireLimitAsMinimum param is true, the minimum length will be 
	 * the same as the value set in limit. Otherwise, program will allow inputs
	 * with length less than or equal to the value of limit param.
	 * 
	 * @param message - Custom message to display for every input attempt
	 * @param limit - Maximum length input string can be
	 * @param requireLimitAsMinimum - Enforces maximum limit as minimum if true
	 * @return int - validated integer
	 */
	private static int getValidBoundedInt(String message, int limit, boolean requireLimitAsMinimum) throws IllegalArgumentException {
		boolean isInvalid = true;
		String getIntString = "";
		int parsedInt = 0;
		
		if (limit <= 0) {
			throw new IllegalArgumentException("Limit cannot less than or equal to 0");
		}
		
		do {
			System.out.print(message);
			try {
				getIntString = in.nextLine();
				getIntString = getIntString.trim();
				// Debug
				if (byFile) System.out.println(getIntString);
				parsedInt = Integer.parseInt(getIntString);
				
				if (!(requireLimitAsMinimum) && (getIntString.length() > limit)) {
					System.out.println("Input can only have maximum of " + limit + " digits");
				}
				else if (requireLimitAsMinimum && getIntString.length() != limit) {
					System.out.println("Input must be " + limit + " digits long");
				}
				else {
					isInvalid = false;
				}
			}
			catch(NumberFormatException e) {
				if (getIntString.equals("")) {
					System.out.println("Input cannot be blank");
				}
				else {
					System.out.println("Input is not valid");
				}
			}
		} while (isInvalid);
		
		return parsedInt;
	}
}
