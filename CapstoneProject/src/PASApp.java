import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Locale;
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
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		ArrayList<CustomerAccount> customerList = new ArrayList<CustomerAccount>();
		ArrayList<Claim> claimList = new ArrayList<Claim>();
		Policy tempPolicy;
		PolicyHolder tempHolder;
		CustomerAccount currentAccount;
		Claim tempClaim;
		LocalDate effectiveDate, nowDate, maxDate;
		String firstName, lastName, address, strIn;
		int choice, uniqueId, inputId;
		boolean foundHit;
		
		do {
			printMenu();
			choice = getPositiveIntLimitedInput("Enter your choice: ", 1, true);
			
			switch (choice) {
				/* Generates a new account and stores in program
				 * 
				 * Before taking inputs, the program will generate a unique ID based on what 
				 * is available in the system. This ID is generated through a class method in 
				 * the CustomerAccount class. Refer to CustomerAccount.generateUniqueId() for 
				 * more details on how it works. 
				 * 
				 * If the system finds a vacant ID, the program will proceed to taking 3 inputs
				 * described below.
				 * 
				 * Inputs:
				 * 		(String)	First Name	- first name of customer to register
				 * 		(String)	Last Name	- last name of customer to register
				 * 		(String)	Address		- address of customer to register
				 * 
				 * The user is given an opportunity to review their inputs if they desire to
				 * change certain details.
				 * 
				 * If the user is satisfied with their inputs, the first name and last names inputs are
				 * compared against all registered users in the system. If an identical match is found,
				 * account creation is aborted.
				 * 
				 * This case made use of the following helper methods:
				 * 		> getStringWord()
				 */
				case 1:
					uniqueId = CustomerAccount.generateUniqueId(customerList);
					
					// Get input from user
					if (uniqueId > 0) {
						do {
							System.out.println("\nInput customer details");
							System.out.println("==================================");
							firstName = getStringWord("Input First Name: ");
							lastName = getStringWord("Input Last Name: ");
							address = getStringWord("Input Address: ");
							System.out.println("==================================\n");
							
							System.out.println("\nReview the following details:");
							System.out.println("First Name: " + firstName);
							System.out.println("Last Name: " + lastName);
							System.out.println("Address: " + address);
							System.out.print("\nWill you change customer details? [y for yes, anything else for no]: ");
							strIn = in.nextLine();
						} while (strIn.equalsIgnoreCase("y"));
						
						// Search in list if name is taken
						foundHit = false;
						for (CustomerAccount custObj: customerList) {
							if (custObj.getFirstName().equalsIgnoreCase(firstName) && custObj.getLastName().equalsIgnoreCase(lastName)) {
								System.out.println("\nAccount name taken");
								foundHit = true;
							}
						}
						
						// Create new entry if name is not taken
						if (!foundHit) {
							customerList.add(new CustomerAccount(uniqueId, firstName, lastName, address));
							System.out.printf("\nAccount Registered with account Number %04d\n", uniqueId);
						}
					}
					else {
						System.out.println("\nNo space left to add a new account");
					}
					
					break;
					
					
				/*
				 * Quotes and/or Creates a Policy based on a series of inputs
				 * 
				 * To create a Policy, the following information is taken by the program in order:
				 * 		> Account Number to file Policy in
				 * 		> Policy Holder Information
				 * 		> Vehicle Information - 1st in mandatory, others are optional
				 * 		> Effective date - optional, can be automatically set to current date
				 * 
				 * For the account number, the input must match an existing account. Input must be
				 * 4 digits long and is verified via custom method getPositiveIntLimitedInput().
				 * Incorrect inputs will result in the program demanding another input until it is
				 * valid. Input is compared to all CustomerAccount instances in the system. If no
				 * match is found, the program will return to the main menu.
				 * 
				 * When inputting policy holder information, the user can decide if the holder will
				 * be the customer themselves or a custom named entity. Information to collect will
				 * be adjusted based on their decision. Refer to makeHolder() helper methods for how
				 * input will be handled. For a policy holder to be valid, the following
				 * conditions must be met:
				 * 		> Age is 18 or greater based on birth date
				 * 		> License issue date must be on or after the date they became 18 years of age
				 * The program will warn the user for any conditions it fails. The user is also given the
				 * opportunity to review their inputs. If the user chooses to continue with invalid details,
				 * policy creation will be aborted.
				 * 
				 * If the policy holder is valid, the program will immediately collect information on
				 * the vehicles to be insured. Refer to helper method makeVehicle() for details on
				 * how inputs are handled. After the first, the user is given the option to add another
				 * vehicle.
				 * 
				 * Once the user is done inputing the vehicle/s, a quote is generated and the user is
				 * asked if they will take the policy. If they do,  the user can decide to set the 
				 * current date as effective date or have it be a defined date. If they choose for a 
				 * defined date, valid inputs will only range between now and 5 years from now.
				 * 
				 * If the user decides to not get the policy, the policy creation process is cancelled.
				 * 
				 * Case makes use of the following helper methods:
				 * 		> getPositiveIntLimitedInput()
				 * 		> makeHolder()
				 * 		> makeVehicle()
				 * 		> getDate()
				 */
				case 2:
					nowDate = LocalDate.now();
					uniqueId = Policy.generateUniqueId(customerList);
					
					// Prevent progression if no unique ID can be generated
					if (uniqueId > 0) {
						inputId = getPositiveIntLimitedInput("Input Account Number to create Policy in: ", 4, true);
						
						// Search for a matching account
						currentAccount = null;
						for (CustomerAccount acct: customerList) {
							if (acct.getAccountNumber() == inputId) {
								currentAccount = acct;
								break;
							}
						}
					
						// Prevent progression if no user was found matching inputed account number
						if (currentAccount != null) {
							tempPolicy = new Policy(uniqueId);
							
							// Create a Policy Holder for the policy
							do {
								//Ask if user wants account holder as policy holder
								System.out.print("\nSet Current account as holder? [y for yes, anything else for no]: ");
								strIn = in.nextLine();
								if (strIn.equalsIgnoreCase("y")) {
									tempHolder = makeHolder(currentAccount);
								}
								else {
									tempHolder = makeHolder();
								}
								
								// Checks for date validity
								if (!(tempHolder.hasValidBirthDate()) || !(tempHolder.hasValidLicenseDate())) {
									System.out.println("ERROR: Inputted details of policy holder violates the following rules:");
									if (!(tempHolder.hasValidBirthDate())) {
										System.out.println("\t> Age must be 18+");
									}
									if (!(tempHolder.hasValidLicenseDate())) {
										System.out.println("\t> License must be issued when holder is at least 18 years old");
									}
									
									// Output information, notifies which fields are invalid
									System.out.println();
									System.out.println("Review the following information:");
									System.out.println("First Name: " + tempHolder.getFirstName());
									System.out.println("Last Name: " + tempHolder.getLastName());
									System.out.println("Birth Date: " + tempHolder.getBirthDate()
														+ (tempHolder.hasValidBirthDate()? "": " (Invalid)"));
									System.out.println("License No.: " + tempHolder.getDriverLicenseNumber());
									System.out.println("License Issue Date: " + tempHolder.getLicenseDate()
														+ (tempHolder.hasValidLicenseDate()? "": " (Invalid)"));
								}
								else {
									// Output information
									System.out.println();
									System.out.println("Review the following information:");
									System.out.println("First Name: " + tempHolder.getFirstName());
									System.out.println("Last Name: " + tempHolder.getLastName());
									System.out.println("Birth Date: " + tempHolder.getBirthDate());
									System.out.println("License No.: " + tempHolder.getDriverLicenseNumber());
									System.out.println("License Issue Date: " + tempHolder.getLicenseDate());
								}
								
								// Get user input if policy holder is to be changed
								System.out.print("\nEnter a different Policy holder? [y for yes, anything else for no]: ");
								strIn = in.nextLine();
							} while (strIn.equalsIgnoreCase("y"));
							
							// Prevent progression if the policy holder details are invalid
							if (tempHolder.hasValidBirthDate() || tempHolder.hasValidLicenseDate()) {
								tempPolicy.setPolicyHolder(tempHolder);
								
								// Add cars to policy
								do  {
									tempPolicy.addVehicle(makeVehicle());
									System.out.print("Input y to add another vehicle. Enter anything else to continue: ");
									strIn = in.nextLine();
								} while (strIn.equalsIgnoreCase("y"));
								
								// Generate a quote and asks the user if they will get the policy
								tempPolicy.generateQuote();
								System.out.print("\nWill you get this policy? [y for yes, anything else for no]: ");
								strIn = in.nextLine();
								
								// Prevent progression if the user will not get the policy
								if (strIn.equalsIgnoreCase("y")) {
									// Ask if user wants current date as effective date
									System.out.print("\nSet policy to be effective immediately? [y for yes, anything else for no]: ");
									strIn = in.nextLine();
									
									if (strIn.equalsIgnoreCase("y")) {
										// Sets current system date as effective date
										effectiveDate = LocalDate.now();
									}
									else {
										maxDate = nowDate.plusYears(5);
										// Get effective policy date from user. Forces user to retry if date is not between
										// current system date and system date 5 years from now.
										do {
											effectiveDate = getDate("policy effective");
											if (effectiveDate.isBefore(nowDate) || effectiveDate.isAfter(maxDate)) {
												System.out.println("\nDate can only be between " + nowDate + " and " + maxDate + "\n");
											}
										} while (effectiveDate.isBefore(nowDate) || effectiveDate.isAfter(maxDate));
									}
									
									// Adds policy to system
									tempPolicy.setEffectiveDate(effectiveDate);
									currentAccount.addPolicy(tempPolicy);
									System.out.printf("\nPolicy created with id %06d\n", uniqueId);
								}
								else {
									System.out.println("\nPolicy buy cancelled.");
								}
							}
							else {
								System.out.println("\nNo valid policy holder inputed, policy creation aborted.");
							}
						}
						else {
							System.out.println("\nNo account found");
						}
					}
					else {
						System.out.println("\nNo space left to add a new policy");
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
				 * 
				 * Case makes use of the following helper methods:
				 * 		> getPositiveIntLimitedInput()
				 */
				case 3:
					inputId = getPositiveIntLimitedInput("Input Policy Number to cancel: ", 6, true);
					
					// Search customer accounts for a matching policy
					foundHit = false;
					for (CustomerAccount custObj: customerList) {
						// Check if customer account has policy
						if (custObj.hasPolicy(inputId)) {
							// Cancels found policy or notifies user if policy is already cancelled
							if (custObj.cancelAccountPolicy(inputId)) {
								System.out.printf("\nPolicy %06d Cancelled\n", inputId);
							}
							else {
								System.out.println("\nPolicy already expired");
							}
							foundHit = true;
						}
					}
					
					if (!foundHit) {
						System.out.println("\nNo match found");
					}
					
					break;
					
				/*
				 * File a claim using Policy ID
				 * 
				 * Input an ID number corresponding to the Policy to file the claim with.
				 * If no match is found, the program returns back to the main menu. 
				 * 
				 * Otherwise, the program will begin checking the following criteria:
				 * 		> The found policy is not expired, checked via Policy.isExpired() method
				 * 		> The found policy is currently in force, checked via Policy.isInForce() method
				 * If the policy fails either condition, the program will return back to the main menu.
				 * 
				 * 
				 * Once the program confirms the conditions are met, it will proceed to generate a unique 
				 * claim ID. Unique ID is generated by calling Claim.generateUniqueId() method. If a valid 
				 * ID is returned, the program will call makeClaim() method to create a claim instance.
				 * 
				 * The claim instance accident date is then compared against the policy effective date.
				 * If the accident date occurred before the policy effective date, the claim making
				 * process will be aborted.
				 * 
				 * Inputs:
				 * 		(int)	inputID - ID of Policy to file claim against
				 * 
				 * Case makes use of the following helper methods:
				 * 		> getPositiveIntLimitedInput()
				 * 		> makeClaim();
				 */
				case 4:
					inputId = getPositiveIntLimitedInput("Input Policy Number to file claim: ", 6, true);
					
					// Search customer accounts for a matching policy
					currentAccount = null;
					for (CustomerAccount custObj: customerList) {
						if (custObj.hasPolicy(inputId)) {
							currentAccount = custObj;
						}
					}
					
					// Checks if a matching account was found
					if (currentAccount!=null) {
						// Verifies if policy is expired or not in force
						if (currentAccount.getPolicyMatchingId(inputId).isExpired()) {
							System.out.println("\nPolicy selected is expired");
						} 
						else if (!(currentAccount.getPolicyMatchingId(inputId).isInForce())) {
							System.out.println("\nPolicy not in force");
						}
						else {
							uniqueId = Claim.generateUniqueId(claimList);
							
							// Prevent progress if no unique ID can be generated
							if (uniqueId > 0) {
								tempClaim = makeClaim(uniqueId);
								effectiveDate = currentAccount.getPolicyMatchingId(inputId).getEffectiveDate();
								
								// Verify if accident date falls inside policy effective range
								if (tempClaim.getAccidentDate().isBefore(effectiveDate)) {
									System.out.println("\nCannot file claim for accident that occured before effective date: " + effectiveDate);
								}
								else {
									claimList.add(tempClaim);
									System.out.printf("\nClaim added with id %s\n", tempClaim.getClaimNumber());
								}
							}
							else {
								System.out.println("\nNo space left to add a new claim");
							}
						}
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
				 * 
				 * Case makes use of the following helper methods:
				 * 		> getStringWord()
				 */
				case 5:
					firstName = getStringWord("Input First Name: ");
					lastName = getStringWord("Input Last Name: ");
					
					// Search for matching customer account, print details if found
					foundHit = false;
					for (CustomerAccount custObj: customerList) {
						if (custObj.getFirstName().equalsIgnoreCase(firstName) && custObj.getLastName().equalsIgnoreCase(lastName)) {
							CustomerAccount.printCustomerAccountHeader();
							custObj.printCustomerAccountDetails();
							foundHit = true;
						}
					}
					
					if(!foundHit) {
						System.out.println("\nNo match found");
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
				 * 
				 * Case makes use of the following helper methods:
				 * 		> getPositiveIntLimitedInput()
				 */
				case 6:
					inputId = getPositiveIntLimitedInput("Input Policy Number to find: ", 6, true);
					foundHit = false;
					
					// Search for a matching policy, print details if found
					for (CustomerAccount custObj: customerList) {
						if (custObj.hasPolicy(inputId)) {
							CustomerAccount.printPolicyHeader();
							custObj.printPolicyMatchingId(inputId);
							foundHit = true;
						}
					}
					
					if(!foundHit) {
						System.out.println("\nNo match found");
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
					// Get claim number from user, repeats while input is not in proper format
					do {
						System.out.print("Input Claim Number to find: ");
						strIn = in.nextLine();
						
						if (!(strIn.matches("^C[\\d]{6}$"))) {
							System.out.println("\nInvalid input. Follow the format Cxxxxxx where x is a value between 0 - 9.\n");
						}
						else {
							inputId = Integer.parseInt(strIn.substring(1));
						}
					} while(!(strIn.matches("^C[\\d]{6}$")));
					
					// Search for a matching claim, print details if found
					foundHit = false;
					for (Claim clmObj: claimList) {
						if (clmObj.getClaimNumber().equals(strIn)) {
							Claim.printClaimHeader();
							clmObj.printClaimDetails();
							foundHit = true;
						}
					}
					
					if(!foundHit) {
						System.out.println("\nNo match found");
					}
					break;
				case 8:
					System.out.println("\nExiting...");
					break;
				default:
					System.out.println("\nInvalid Choice");
			}
			
			if (choice != 8) {
				System.out.println("Press enter to continue...");
				in.nextLine();
				System.out.println();
			}
			
		} while (choice != 8);
	}
	
	// HELPER METHODS
	// =================================================================================================================================================
	
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
	 * 		> getIntLimitedInput()
	 * 		> getValidDouble()
	 * 
	 * @return Vehicle instance
	 */
	private static Vehicle makeVehicle() {
		String make,model, type, fuelType, strIn;
		int yearNow = LocalDate.now().getYear();
		int year;
		double purchasePrice;
		
		do {
			System.out.println("\nInput vehicle details");
			System.out.println("==================================");
			make = getStringWord("Enter make: ");
			model = getStringWord("Enter model: ");
			
			// Gets Year of vehicle, requires input to be between 1900 and current year
			do {
				year = getIntLimitedInput("Enter year: ", 4, true);
				if (year > yearNow || year < 1900) {
					System.out.println("\nCar year must be between 1900 and " + yearNow + "\n");
				}
			} while (year > yearNow || year < 1900);
			
			
			type = getStringWord("Enter type: ");
			fuelType = getStringWord("Enter fuel type: ");
			purchasePrice = getMoneyInput("Enter purchase price: ");
			System.out.println("==================================\n");
			
			System.out.println("\nReview the following information:");
			System.out.println("Vehicle make: " + make);
			System.out.println("Vehicle model: " + model);
			System.out.println("Vehicle year: " + year);
			System.out.println("Vehicle type: " + type);
			System.out.println("Vehicle fuel type: " + fuelType);
			System.out.println("Vehicle purchase price: " + NumberFormat.getCurrencyInstance(Locale.US).format(purchasePrice));
			System.out.print("\nWill you change details of vehicle? [y for yes, anything else for no]: ");
			strIn = in.nextLine();
		} while (strIn.equalsIgnoreCase("y"));
		
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
	 * User can review their inputs and confirm if they want to use it for their claim.
	 * 
	 * Inputs:
	 * 		(LocalDate)	accident date			- Date accident took place, refer to getDate() method.
	 * 		(String)	accident address		- location of accident
	 * 		(String)	accident description	- description of how accident occurred
	 * 		(String)	accident damage			- description of damage
	 * 		(Double)	repair costs			- Repair costs of damaged vehicle
	 * 		
	 * Uses input validator methods. See following for documentation:
	 * 		> getDateRequirePast()
	 * 		> getStringWord()
	 * 		> getMoneyInput()
	 * 
	 * @param uniqueId - ID of claim to be instantiated
	 * @return Claim instance
	 */
	private static Claim makeClaim(int uniqueId) {
		LocalDate accidentDate;
		String accidentAddress, accidentDescription, accidentDamage, strIn;
		double repairCosts;
		
		do {
			System.out.println("\nInput claim details");
			System.out.println("==================================");
			accidentDate = getDateRequirePast("accident");
			accidentAddress = getStringWord("Enter accident address: ");
			accidentDescription = getStringWord("Enter accident description: ");
			accidentDamage = getStringWord("Enter damage description: ");
			repairCosts = getMoneyInput("Enter repair costs: ");
			System.out.println("==================================\n");
			
			System.out.println("\nReview the following information: ");
			System.out.println("Accident Date: " + accidentDate);
			System.out.println("Accident Address: " + accidentAddress);
			System.out.println("Accident Description: " + accidentDescription);
			System.out.println("Accident Damage: " + accidentDamage);
			System.out.println("Repair costs: " + NumberFormat.getCurrencyInstance(Locale.US).format(repairCosts));
			System.out.print("\nWill you change details of claim? [y for yes, anything else for no]: ");
			strIn = in.nextLine();
		} while (strIn.equalsIgnoreCase("y"));
		
		
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
	 * All inputs for each date component makes use getIntLimitedInput() method. 
	 * This ensures that each input has a limited amount of digits when inputting.
	 * Description of how input length is limited will be described further down below. 
	 * For more information on getIntLimitedInput() method functionality, refer to 
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
			year = getIntLimitedInput("Enter " + type + " year: ", 4, true);
			if (year < 1900) {
				System.out.println("\nYear must be beyond 1900\n");
			}
			else {
				isInvalid = false;
			}
		} while (year < 1900);
		
		// Get Month
		do {
			month = getIntLimitedInput("Enter " + type + " month (01-12): ", 2, false);
			if (month > 12 || month < 1) {
				System.out.println("\nOnly values 1 - 12 are valid\n");
			}
		} while(month > 12 || month < 1);
		
		//Get Day
		isInvalid = true;
		do {
			try {
				day = getIntLimitedInput("Enter " + type + " day: ", 2, false);
				date = LocalDate.of(year, month, day);
				isInvalid = false;
			}
			catch (DateTimeException e) {
				System.out.println("\nInvalid day for month\n");
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
			if (newDate.isAfter(nowDate)) {
				System.out.println("\nDate cannot be after " + nowDate + "\n");
			}
		} while (newDate.isAfter(nowDate));
		
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
			
			strIn = strIn.trim();
			if (strIn.equals("")) {
				System.out.println("\nEntry cannot be blank\n");
			}
			else if (strIn.matches("^[,\\.\\- ]*$")) {
				System.out.println("\nInput cannot purely be \".\", \"-\", and \",\"\n");
				strIn = "";
			}
			else if (strIn.matches("^[,\\d\\.\\- ]*$")) {
				System.out.println("\nInput cannot purely be numbers\n");
				strIn = "";
			}
			else if (!(strIn.matches("^[a-zA-Z0-9,\\.\\- ]*$"))) {
				System.out.println("\nInput cannot contain special characters\n");
				strIn = "";
			}
		} while(strIn.equals(""));
		
		return strIn;
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
				
				parsedDouble = Double.parseDouble(getDoubleString);
				
				if (BigDecimal.valueOf(parsedDouble).scale() > 2) {
					System.out.println("\nInput must only have 2 digits in decimal place\n");
				}
				else if (parsedDouble < 0) {
					System.out.println("\nInput cannot be negative\n");
				}
				else if (parsedDouble == 0) {
					System.out.println("\nInput cannot be 0\n");
				}
				else {
					isInvalid = false;
				}
			}
			catch (NumberFormatException e) {
				if (getDoubleString.equals("")) {
					System.out.println("\nInput cannot be blank\n");
				}
				else {
					System.out.println("\nInvalid input! Input must consist of purely numbers\n");
				}
			}
		} while (isInvalid);
		
		return parsedDouble;
	}
	
	/**
	 * Returns inputed int value after verification.
	 * Can display custom message that repeats for every input attempt.
	 * 
	 * Verification is done via exception handling when an input string is
	 * parsed into an integer. Should an exception be thrown by Integer.parseInt(),
	 * the input is recognized as invalid and the user must input another string.
	 * 
	 * Additionally, the length of an input is limited based on the value of the
	 * limit param. this sets the maximum length a string can be. The minimum length
	 * will still remain as 1.
	 * 
	 * If the requireLimitAsMinimum param is true, the minimum length will be 
	 * the same as the value set in limit.
	 * 
	 * Limit only applies to the numeric portion of the input which means when
	 * a negative number is inputed, the negative sign will not count as an
	 * additional character.
	 * 
	 * @param message - Custom message to display for every input attempt
	 * @param limit - Maximum length input string can be
	 * @param requireLimitAsMinimum - Enforces maximum limit as minimum if true
	 * @return int - validated integer
	 */
	private static int getIntLimitedInput(String message, int limit, boolean requireLimitAsMinimum) throws IllegalArgumentException {
		boolean isInvalid = true;
		String getIntString = "";
		int parsedInt = 0;
		
		if (limit <= 0) {
			throw new IllegalArgumentException("\nLimit cannot less than or equal to 0");
		}
		
		do {
			System.out.print(message);
			try {
				getIntString = in.nextLine();
				getIntString = getIntString.trim();
				parsedInt = Integer.parseInt(getIntString);
				
				// Removes negative sign from integer string if parsed input is negative
				// This is done so that program can identify the length of the digits alone
				if (parsedInt < 0) {
					getIntString = getIntString.substring(1);
				}
				
				if (requireLimitAsMinimum) {
					if (parsedInt < 0 && getIntString.length() == limit) {
						isInvalid = false;
					}
					else if (getIntString.length() != limit) {
						System.out.println("\nInput must be " + limit + " digit/s long\n");
					}
					else {
						isInvalid = false;
					}
				}
				else {
					if (parsedInt < 0 && getIntString.length() <= limit) {
						isInvalid = false;
					}
					else if (getIntString.length() > limit) {
						System.out.println("\nInput can only have maximum of " + limit + " digit/s\n");
					}
					else {
						isInvalid = false;
					}
				}
			}
			catch(NumberFormatException e) {
				if (getIntString.equals("")) {
					System.out.println("\nInput cannot be blank\n");
				}
				else {
					System.out.println("\nInput is not valid\n");
				}
			}
		} while (isInvalid);
		
		return parsedInt;
	}
	
	/**
	 * Returns inputed int value after verification.
	 * Can display custom message that repeats for every input attempt.
	 * 
	 * Makes use of the behavior of getIntLimitedInput but adds an additional
	 * condition. Program will also check if the value inputed is positive.
	 * If it is not positive, the program will force the user to input another
	 * value.
	 * 
	 * @param message - Custom message to display for every input attempt
	 * @param limit - Maximum length input string can be
	 * @param requireLimitAsMinimum - Enforces maximum limit as minimum if true
	 * @return int - positive validated integer
	 */
	private static int getPositiveIntLimitedInput(String message, int limit, boolean requireLimitAsMinimum) {
		int parsedInt;
		
		do {
			parsedInt = getIntLimitedInput(message, limit, requireLimitAsMinimum);
			if (parsedInt < 0) {
				System.out.println("\nInput cannot be negative\n");
			}
		} while (parsedInt < 0);
		
		return parsedInt;
	}
}
