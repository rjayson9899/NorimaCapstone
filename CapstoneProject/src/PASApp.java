import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.DateTimeException;
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
		String firstName, lastName, address, strIn;
		int choice, uniqueId, inputId;
		boolean foundHit, isExpired;
		
		in = new Scanner(System.in);
		
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
		
		do {
			printMenu();
			choice = getValidInt("Enter your choice: ");
			
			switch (choice) {
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
							System.out.println("No account found");
						}
					}
					else {
						System.out.println("No space left to add a new policy");
					}
					break;
				case 3:
					inputId = getValidInt("Input Policy Number to cancel: ");
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
					inputId = getValidInt("Input Policy Number to file claim: ");
					foundHit = false;
					isExpired = false;
					currentAccount = null;
					
					for (CustomerAccount custObj: customerList) {
						if (custObj.hasPolicy(inputId)) {
							if (custObj.getPolicyMatchingId(inputId).getExpirationDate().compareTo(LocalDate.now()) < 0) {
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
							tempClaim = new Claim(uniqueId);
							setClaimInputs(tempClaim);
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
				case 7:
					inputId = 0;
					do {
						System.out.print("Input Claim Number to find: ");
						strIn = in.nextLine();
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
	}
	
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
	
	private static PolicyHolder makeHolder(CustomerAccount custObj) {
		String licenseNumber;
		LocalDate birthDate, licenseDate;
		
		birthDate = getDate("birth");
		licenseNumber = getStringNonEmpty("Input driver's license number: ");
		licenseDate = getDate("license issue");
		
		return new PolicyHolder(custObj, birthDate, licenseNumber, licenseDate);
	}
	
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
	
	private static void setClaimInputs(Claim clmObj) {
		clmObj.setAccidentDate(getDate("accident"));
		clmObj.setAccidentAddress(getStringNonEmpty("Enter accident address: "));
		clmObj.setAccidentDescription(getStringNonEmpty("Enter accident description: "));
		clmObj.setAccidentDamage(getStringNonEmpty("Enter damage description: "));
		clmObj.setRepairCosts(getValidDouble("Enter repair costs: "));
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
	
	private static String getStringNonEmpty(String message) {
		String strIn;
		
		do {
			System.out.print(message);
			strIn = in.nextLine();
			if (strIn.equals("")) {
				System.out.println("Entry cannot be blank");
			}
		} while(strIn.equals(""));
		
		return strIn;
	}
	
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
		
		return getInt;
	}
	
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
		
		return getDouble;
	}
}
