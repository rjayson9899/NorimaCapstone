import java.io.File;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PASApp {
	static File filePath = new File("src/test.txt");
	private static Scanner in;
	
	public static void main(String[] args) {
		ArrayList<CustomerAccount> customerList = new ArrayList<CustomerAccount>();
		ArrayList<Claim> claimList = new ArrayList<Claim>();
		Policy tempPolicy;
		CustomerAccount currentAccount;
		Claim tempClaim;
		LocalDate effectiveDate;
		int choice, uniqueId, inputId;
		String firstName, lastName, address, strChoice;
		boolean foundHit;
		
		in = new Scanner(System.in);
		
		System.out.print("Test by file? [y for yes]: ");
		strChoice = in.nextLine();
		
		if (strChoice.equalsIgnoreCase("y")) {
			try {
				in = new Scanner(filePath);
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		do {
			printMenu();
			choice = in.nextInt();
			in.nextLine();
			
			switch (choice) {
				case 1:
					uniqueId = CustomerAccount.generateUniqueId(customerList);
					
					if (uniqueId >= 0) {
						System.out.print("Input First Name: ");
						firstName = in.nextLine();
						System.out.print("Input Last Name: ");
						lastName = in.nextLine();
						System.out.print("Input Address: ");
						address = in.nextLine();
						
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
				case 2:
					System.out.println("Input Account Number to create Policy in: ");
					inputId = in.nextInt();
					in.nextLine();
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
						uniqueId = Policy.generateUniqueId(customerList);
						if (uniqueId >= 0) {
							tempPolicy = new Policy(uniqueId);
							
							System.out.println("Set Current account as holder? [y for yes]: ");
							strChoice = in.nextLine();
							if (strChoice.equalsIgnoreCase("y")) {
								setHolder(tempPolicy, currentAccount);
							}
							else {
								setHolder(tempPolicy);
							}
							
							while (addPolicyVehicle(tempPolicy));
							tempPolicy.generateQuote();
							System.out.print("Will you get this policy? [y for yes]: ");
							strChoice = in.nextLine();
							if (strChoice.equalsIgnoreCase("y")) {
								effectiveDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue() + 1, 1);
								tempPolicy.setEffectiveDate(effectiveDate);
								currentAccount.addPolicy(tempPolicy);
								System.out.printf("Policy created with id %06d\n", uniqueId);
							}
							else {
								System.out.println("Policy buy cancelled.");
							}
						}
						else {
							System.out.println("No space left to add a new policy");
						}
					}
					else {
						System.out.println("No account found");
					}
					
					break;
				case 3:
					System.out.println("Input Policy Number to cancel: ");
					inputId = in.nextInt();
					in.nextLine();
					foundHit = false;
					
					for (CustomerAccount custObj: customerList) {
						if (custObj.hasPolicy(inputId)) {
							custObj.cancelAccountPolicy(inputId);
							System.out.printf("Policy %06d Cancelled\n", inputId);
							foundHit = true;
						}
					}
					
					if (!foundHit) {
						System.out.println("No match found");
					}
					
					break;
				case 4:
					System.out.println("Input Policy Number to file claim: ");
					inputId = in.nextInt();
					in.nextLine();
					foundHit = false;
					currentAccount = null;
					
					for (CustomerAccount custObj: customerList) {
						if (custObj.hasPolicy(inputId)) {
							currentAccount = custObj;
							foundHit = true;
						}
					}
					
					if (foundHit) {
						uniqueId = Claim.generateUniqueId(claimList);
						if (uniqueId >= 0) {
							tempClaim = new Claim(uniqueId);
							setClaimInputs(tempClaim);
							claimList.add(tempClaim);
							System.out.printf("Claim added with id %s\n", tempClaim.getClaimNumber());
						}
						else {
							System.out.println("No space left to add a new claim");
						}
					}
					else {
						System.out.println("No match found");
					}
					
					break;
				case 5:
					System.out.println("Input First Name: ");
					firstName = in.nextLine();
					System.out.println("Input Last Name: ");
					lastName = in.nextLine();
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
				case 6:
					System.out.println("Input Policy Number to find: ");
					inputId = in.nextInt();
					in.nextLine();
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
				case 7:
					inputId = 0;
					do {
						System.out.println("Input Claim Number to find: ");
						strChoice = in.nextLine();
						if ((strChoice.length() != 7) && (strChoice.charAt(0) != 'C')) {
							System.out.println("Invalid input. Follow the format Cxxxxxx where x is a value between 0 - 9.");
						}
						else {
							try {
								inputId = Integer.parseInt(strChoice.substring(1));
							}
							catch (NumberFormatException e) {
								System.out.println("Invalid Claim ID value");
								strChoice = "";
							}
						}
					} while((strChoice.length() != 7) && (strChoice.charAt(0) != 'C'));
					
					
					foundHit = false;
					
					for (Claim clmObj: claimList) {
						if (clmObj.getIntId() == inputId) {
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
		System.out.println("Data dump");
		CustomerAccount.printCustomerAccountHeader();
		for (CustomerAccount cstObj: customerList) {
			cstObj.printCustomerAccountDetails();
		}
		CustomerAccount.printPolicyHeader();
		for (CustomerAccount cstObj: customerList) {
			cstObj.printPolicies();
		}
		Claim.printClaimHeader();
		for (Claim clmObj: claimList) {
			clmObj.printClaimDetails();
		}
		
	}
	
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
		System.out.print("Enter your choice: ");
	}
	
	private static boolean addPolicyVehicle(Policy polObj) {
		String make;
		String model;
		int year;
		String type;
		String fuelType;
		double purchasePrice;
		String choice;
		
		System.out.print("Enter make: ");
		make = in.nextLine();
		System.out.print("Enter model: ");
		model = in.nextLine();
		System.out.print("Enter year: ");
		year = in.nextInt();
		in.nextLine();
		System.out.print("Enter type: ");
		type = in.nextLine();
		System.out.print("Enter fuel type: ");
		fuelType = in.nextLine();
		System.out.print("Enter purchase price: ");
		purchasePrice = in.nextDouble();
		in.nextLine();
		
		polObj.addVehicle(make, model, year, type, fuelType, purchasePrice);
		
		System.out.print("Input y to add another vehicle, else press enter to continue: ");
		choice = in.nextLine();
		if (choice.equalsIgnoreCase("y")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private static void setHolder(Policy polObj, CustomerAccount custObj) {
		String licenseNumber;
		LocalDate birthDate, licenseDate;
		
		birthDate = getDate("birth");
		
		System.out.print("Input driver's license number: ");
		licenseNumber = in.nextLine();
		
		licenseDate = getDate("license issue");
		
		polObj.setHolder(custObj, birthDate, licenseNumber, licenseDate);
	}
	
	private static void setHolder(Policy polObj) {
		int month, day, year;
		String licenseNumber, firstName, lastName;
		LocalDate birthDate, licenseDate;
		
		System.out.print("Enter first name: ");
		firstName = in.nextLine();
		System.out.print("Enter last name: ");
		lastName = in.nextLine();
		
		birthDate = getDate("birth");
		
		System.out.print("Input license number: ");
		licenseNumber = in.nextLine();
		
		licenseDate = getDate("license issue");
		
		polObj.setHolder(firstName, lastName, birthDate, licenseNumber, licenseDate);
	}
	
	private static void setClaimInputs(Claim clmObj) {
		clmObj.setAccidentDate(getDate("accident"));
		
		System.out.println("Enter accident address: ");
		clmObj.setAccidentAddress(in.nextLine());
		
		System.out.println("Enter accident description: ");
		clmObj.setAccidentDescription(in.nextLine());
		
		System.out.println("Enter damage description: ");
		clmObj.setAccidentDamage(in.nextLine());
		
		System.out.println("Enter repair costs: ");
		clmObj.setRepairCosts(in.nextDouble());
		in.nextLine();
	}
	
	private static LocalDate getDate(String type) {
		LocalDate date = LocalDate.now();
		int month = 0;
		int day = 0;
		int year = 0;
		boolean isInvalid = true;
		
		do {
			try {
				System.out.print("Enter " + type + " year: ");
				year = in.nextInt();
				if (year < 1900) {
					System.out.println("Only years beyond 1900 are valid");
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input");
				year = -1;
			}
			finally {
				in.nextLine();
			}
		} while (year < 1900);
		
		do {
			try {
				System.out.print("Enter " + type + " month: ");
				month = in.nextInt();
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
	
}
