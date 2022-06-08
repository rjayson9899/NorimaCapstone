import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import javax.naming.CannotProceedException;

public class PASApp {

	private static void mainMenu() {
		System.out.println("\nAutomobile Insurance Policy and Claims Administration System");
		System.out.println("[1] Create an Account");
		System.out.println("[2] Get Policy Quote and Buy");
		System.out.println("[3] Cancel Policy");
		System.out.println("[4] File a Claim");
		System.out.println("[5] Search Account");
		System.out.println("[6] Search Policy");
		System.out.println("[7] Search Claim");
		System.out.println("[8] Exit");
		System.out.print("Enter your choice: ");
	}

	public static void main(String[] args) {
		int choice;
		int tempID = 0;
		int year, month, day;
		LocalDate effectiveDatePolicy, expirationDatePolicy;
		LocalDate licensedIssueDate;

		String firstName, lastName, customerAddress;
		String birthDate, driversLicenseNumber, driversLicenseIssued;
		String inputString;

		CustomerAccount customer;
		Policy policy;
		PolicyHolder policyHolderObj;
		Vehicle vehicleObj;

		ArrayList<Integer> customerIDList = new ArrayList<Integer>();
		ArrayList<CustomerAccount> customerList = new ArrayList<CustomerAccount>();

		Scanner input = new Scanner(System.in);

		do {
			mainMenu();
			choice = input.nextInt();
			switch (choice) {
			case 1:
				// Generate a unique Customer ID
				for (CustomerAccount custObj : customerList) {
					customerIDList.add(Integer.valueOf(custObj.getAccountNumber()));
				}
				for (int i = 0; i <= CustomerAccount.CUSTOMER_MAX; i++) {
					if (!(customerIDList.contains(Integer.valueOf(i)))) {
						tempID = i;
						break;
					}
					if (i == CustomerAccount.CUSTOMER_MAX) {
						tempID = -1;
					}
				}

				// Validation if full
				if (tempID <= CustomerAccount.CUSTOMER_MAX) {
					// Create a Customer Account
					input.nextLine(); // Flush
					System.out.println("\nCreating a Customer Account");
					System.out.print("Enter First Name: ");
					firstName = input.nextLine();
					System.out.print("Enter Last Name: ");
					lastName = input.nextLine();
					System.out.print("Enter Address: ");
					customerAddress = input.nextLine();

					customerList.add(new CustomerAccount(tempID, firstName, lastName, customerAddress));

					System.out.println("Account created succesfully!");
					System.out.printf("Your account number is: %04d", tempID);
					System.out.println("\n");

				} else {
					System.out.println("System is full of users cannot create a new account!");
				}

				break;
			case 2:
				customer = null;
				// Quote Policy
				
				// Validation if Customer ID already exists
				Boolean isMatch = false;
				System.out.print("Input Customer ID number: ");
				int inputCustomerID = input.nextInt();
				input.nextLine(); // Flush

				for (CustomerAccount account : customerList) {
					if (inputCustomerID == account.getAccountNumber()) {
						isMatch = true;
						customer = account;
						break;
					}

				}

				if (isMatch) {
					// Generate a unique Policy Number
					ArrayList<Integer> policyIDList = new ArrayList<Integer>();

					for (CustomerAccount custObj : customerList) {
						custObj.addPolicyIDs(policyIDList);
					}

					for (int i = 0; i <= Policy.POLICY_MAX; i++) {
						if (!(policyIDList.contains(Integer.valueOf(i)))) {
							tempID = i;
							break;
						}
						if (i == Policy.POLICY_MAX) {
							tempID = -1;
						}
					}

					// Validation if Full
					if (tempID >= 0) {

						policy = new Policy(tempID);

						// Validation if Customer is Policy Holder
						System.out.print("Input [y] if Account Holder is same as Policy Holder: ");
						inputString = input.nextLine();
						if (inputString.equalsIgnoreCase("y")) {
							System.out.println("Account Holder is the same as Policy Holder");
							System.out.print("Enter birth date: "); //Fix Format for consistency Convert LocalDate to String
							birthDate = input.nextLine();
							System.out.print("Enter drivers License Number: ");
							driversLicenseNumber = input.nextLine();
							System.out.println("Enter the date of the license where it was first issued");
							System.out.print("Enter year (yyyy): ");
							year = input.nextInt();
							System.out.print("Enter month (mm): ");
							month = input.nextInt();
							System.out.print("Enter day (dd): ");
							day = input.nextInt();
							input.nextLine(); // Flush

							licensedIssueDate = LocalDate.of(year, month, day);

							policyHolderObj = new PolicyHolder(customer, birthDate, driversLicenseNumber,
									licensedIssueDate);
							policy.setPolicyHolder(policyHolderObj);
							
							//Debug Code System.out.printf("%06d\n",policy.getPolicyNumber());
							//Debug Code System.out.println(policyHolderObj.toString());

						} else {
							System.out.println("Create a Policy Holder");
							System.out.print("Enter first name: ");
							firstName = input.nextLine();
							System.out.print("Enter last name: ");
							lastName = input.nextLine();
							System.out.print("Enter birth date: "); //Fix Format for consistency Convert LocalDate to String
							birthDate = input.nextLine();
							System.out.print("Enter address: ");
							customerAddress = input.nextLine();
							System.out.print("Enter drivers License Number: ");
							driversLicenseNumber = input.nextLine();
							System.out.println("Enter the date of the license where it was first issued");
							System.out.print("Enter year (yyyy): ");
							year = input.nextInt();
							System.out.print("Enter month (mm): ");
							month = input.nextInt();
							System.out.print("Enter day (dd): ");
							day = input.nextInt();
							input.nextLine(); // Flush

							licensedIssueDate = LocalDate.of(year, month, day);

							policyHolderObj = new PolicyHolder(firstName, lastName, birthDate, customerAddress,
									driversLicenseNumber, licensedIssueDate);
							policy.setPolicyHolder(policyHolderObj);
							
							//Debug Code System.out.printf("%06d\n",policy.getPolicyNumber());
							//Debug Code System.out.println(policyHolderObj.toString());
						}
						//Create a Vehicle 
						do {
							System.out.println("Create a Vehicle");
							System.out.print("Input Car Make (Brand): ");
							String vehicleMake = input.nextLine();
							System.out.print("Input Car Model: ");
							String vehicleModel = input.nextLine();
							System.out.print("Input Car Year: ");
							int vehicleYear = input.nextInt();
							input.nextLine(); // Flush
							System.out.print("Input Car Type: ");
							String vehicleType = input.nextLine();
							System.out.print("Input Fuel Type: ");
							String vehicleFuel = input.nextLine();
							System.out.print("Input Vehicle Purchase Price: ");
							double vehiclePrice = input.nextDouble();
							input.nextLine(); //Flush
							System.out.print("Input Vehicle Color: ");
							String vehicleColor = input.nextLine();
							
							vehicleObj = new Vehicle(vehicleMake, vehicleModel, vehicleYear, vehicleType, vehicleFuel, vehiclePrice, vehicleColor);
							policy.addVehicle(vehicleObj);
							
							System.out.print("Do you want to add another vehicle [y] to add more: ");
							inputString = input.nextLine();
							
						} while(inputString.equalsIgnoreCase("y"));
						
						//Issue a Policy and Create Quote (Premium)
						policy.createPolicyQuote();
						//Verification if get policy or not (If Yes Issue Policy Date)
						
						// Create a Policy Date
						// policy.createPolicyDate();
						/*
						System.out.println("Create a Policy Date");
						System.out.print("Enter year (yyyy): ");
						year = input.nextInt();
						System.out.print("Enter month (mm): ");
						month = input.nextInt();
						System.out.print("Enter day (dd): ");
						day = input.nextInt();

						LocalDate customDate = LocalDate.of(year, month, day);
						effectiveDatePolicy = customDate;
						customDate = customDate.plusMonths(6);
						expirationDatePolicy = customDate;

						System.out.println("Policy Effective Date: " + effectiveDatePolicy);
						System.out.println("Policy Expiration Date: " + expirationDatePolicy);
						*/
					} else {
						System.out.println("System is full of policies, cannot create a new policy!");
					}
				} else {
					System.out.println("No account found");
				}

				break;
			case 3:
				break;
			case 4:
				break;

			case 5: // Search Account Functionality
				System.out.println("\nSearch for an Account");
				System.out.print("Input Account Number: ");
				int acctIDInput = input.nextInt();
				input.nextLine(); // Flush
				isMatch = false;

				for (CustomerAccount custObj : customerList) {
					if (custObj.getAccountNumber() == acctIDInput) {
						System.out.printf("%-20s \t%-20s \t%-20s \t%-20s\n", "Account Number", "First Name",
								"Last Name", "Address");
						custObj.displayCustomerAccountInfo();
						isMatch = true;
					}
				}

				if (!isMatch) {
					System.out.println("Account does not exist!");
				}

				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			default:
				System.out.println("Invalid choice!");
				break;

			}

		} while (choice != 8);

	}

}
