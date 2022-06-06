import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
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
						customerList.add(new CustomerAccount(uniqueId, firstName, lastName, address));
						System.out.printf("Account Registered with account Number %04d\n", uniqueId);
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
						uniqueId = Policy.generateUniqueId();
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
					System.out.println("Input Account Number to find: ");
					inputId = in.nextInt();
					in.nextLine();
					foundHit = false;
					
					
					for (CustomerAccount custObj: customerList) {
						if (custObj.getAccountNumber() == inputId) {
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
					System.out.println("Input Claim Number to find: ");
					strChoice = in.nextLine();
					inputId = Integer.parseInt(strChoice.substring(1));
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
		int month, day, year;
		String licenseNumber;
		LocalDate birthDate, licenseDate;
		
		System.out.println("Enter birth year: ");
		year = in.nextInt();
		in.nextLine();
		System.out.println("Enter birth month: ");
		month = in.nextInt();
		in.nextLine();
		System.out.println("Enter birth day: ");
		day = in.nextInt();
		in.nextLine();
		birthDate = LocalDate.of(year, month, day);
		
		System.out.println("Input driver's license number: ");
		licenseNumber = in.nextLine();
		
		System.out.println("Enter license year: ");
		year = in.nextInt();
		in.nextLine();
		System.out.println("Enter license month: ");
		month = in.nextInt();
		in.nextLine();
		System.out.println("Enter license day: ");
		day = in.nextInt();
		in.nextLine();
		licenseDate = LocalDate.of(year, month, day);
		
		polObj.setHolder(custObj, birthDate, licenseNumber, licenseDate);
	}
	
	private static void setHolder(Policy polObj) {
		int month, day, year;
		String licenseNumber, firstName, lastName;
		LocalDate birthDate, licenseDate;
		
		System.out.println("Enter first name: ");
		firstName = in.nextLine();
		System.out.println("Enter last name: ");
		lastName = in.nextLine();
		
		System.out.println("Enter birth year: ");
		year = in.nextInt();
		in.nextLine();
		System.out.println("Enter birth month: ");
		month = in.nextInt();
		in.nextLine();
		System.out.println("Enter birth day: ");
		day = in.nextInt();
		in.nextLine();
		birthDate = LocalDate.of(year, month, day);
		
		System.out.println("Input license number: ");
		licenseNumber = in.nextLine();
		
		System.out.println("Enter license year: ");
		year = in.nextInt();
		in.nextLine();
		System.out.println("Enter license month: ");
		month = in.nextInt();
		in.nextLine();
		System.out.println("Enter license day: ");
		day = in.nextInt();
		in.nextLine();
		licenseDate = LocalDate.of(year, month, day);
		
		polObj.setHolder(firstName, lastName, birthDate, licenseNumber, licenseDate);
	}
	
	private static void setClaimInputs(Claim clmObj) {
		int month, day, year;
		
		System.out.println("Enter accident year: ");
		year = in.nextInt();
		in.nextLine();
		System.out.println("Enter accident month: ");
		month = in.nextInt();
		in.nextLine();
		System.out.println("Enter accident day: ");
		day = in.nextInt();
		in.nextLine();
		clmObj.setAccidentDate(LocalDate.of(year, month, month));
		
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
	
}
