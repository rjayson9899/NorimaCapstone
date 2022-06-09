import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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
		int policyIDInput;
		
		LocalDate effectiveDatePolicy;
		LocalDate licensedIssueDate;

		String firstName, lastName, customerAddress;
		String birthDate, driversLicenseNumber;
		String inputString;

		CustomerAccount customer;
		Policy policy;
		PolicyHolder policyHolderObj;
		Vehicle vehicleObj;

		boolean isMatch;

		ArrayList<Integer> customerIDList = new ArrayList<Integer>();
		ArrayList<CustomerAccount> customerList = new ArrayList<CustomerAccount>();

		Scanner input = new Scanner(System.in);

		do {
			mainMenu();
			choice = input.nextInt();
			input.nextLine(); //Flush
			switch (choice) {
			case 1:
				isMatch = false;
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
					System.out.println("\nCreating a Customer Account");
					System.out.print("Enter First Name: ");
					firstName = input.nextLine();
					System.out.print("Enter Last Name: ");
					lastName = input.nextLine();
					System.out.print("Enter Address: ");
					customerAddress = input.nextLine();

					for (CustomerAccount custObj : customerList) {
						if (custObj.getFirstName().equals(firstName) && custObj.getLastName().equals(lastName)) {
							System.out.println("Account already exist!");
							isMatch = true;
						}
					}

					if (!isMatch) {
						customerList.add(new CustomerAccount(tempID, firstName, lastName, customerAddress));
						System.out.println("\nAccount created succesfully!");
						System.out.printf("Your account number is: %04d\n", tempID);
					}

				} else {
					System.out.println("System is full of users cannot create a new account!");
				}

				break;
			case 2:
				customer = null;
				// Quote Policy

				// Validation if Customer ID already exists
				isMatch = false;
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
							System.out.print("Enter birth date: "); // Fix Format for consistency Convert LocalDate to
																	// String
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

							// Debug Code System.out.printf("%06d\n",policy.getPolicyNumber());
							// Debug Code System.out.println(policyHolderObj.toString());

						} else {
							System.out.println("Create a Policy Holder");
							System.out.print("Enter first name: ");
							firstName = input.nextLine();
							System.out.print("Enter last name: ");
							lastName = input.nextLine();
							System.out.print("Enter birth date: "); // Fix Format for consistency Convert LocalDate to
																	// String
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

							// Debug Code System.out.printf("%06d\n",policy.getPolicyNumber());
							// Debug Code System.out.println(policyHolderObj.toString());
						}
						// Create a Vehicle
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
							input.nextLine(); // Flush
							System.out.print("Input Vehicle Color: ");
							String vehicleColor = input.nextLine();

							vehicleObj = new Vehicle(vehicleMake, vehicleModel, vehicleYear, vehicleType, vehicleFuel,
									vehiclePrice, vehicleColor);
							policy.addVehicle(vehicleObj);

							System.out.print("Do you want to add another vehicle [y] to add more: ");
							inputString = input.nextLine();

						} while (inputString.equalsIgnoreCase("y"));

						// Issue a Policy and Create Quote (Premium)
						System.out.printf("%-20s \t%-20s \t%-20s\n", "Vehicle Make", "Vehicle Model", "Premium");
						policy.createPolicyQuote();
						// Verification if get policy or not (If Yes Issue Policy Date)
						System.out.print("Get the policy? [y] for yes: ");
						inputString = input.nextLine();

						if (inputString.equalsIgnoreCase("y")) {
							// Create a Policy Date
							System.out.println("\nCreate a Policy Date");
							System.out.print("Enter year (yyyy): ");
							year = input.nextInt();
							System.out.print("Enter month (mm): ");
							month = input.nextInt();
							System.out.print("Enter day (dd): ");
							day = input.nextInt();
							input.nextLine(); // Flush

							LocalDate customDate = LocalDate.of(year, month, day);
							effectiveDatePolicy = customDate;
							policy.setEffectiveDatePolicy(effectiveDatePolicy);
							customer.addPolicy(policy);
							System.out.println("\nPolicy is Created");
							System.out.printf("Policy Number is: %06d\n", tempID);
							// customDate = customDate.plusMonths(6);
							// expirationDatePolicy = customDate;

							// Create Status Validation Local Date
							

							// System.out.println("Policy Effective Date: " + effectiveDatePolicy);
							// System.out.println("Policy Expiration Date: " + expirationDatePolicy);

						} else {
							System.out.println("Policy Quote not taken");
						}
					} else {
						System.out.println("System is full of policies, cannot create a new policy!");
					}
				} else {
					System.out.println("No account found");
				}

				break;
			case 3: //Cancel a specific policy, Change the expiration date of a policy to an earlier date than specified
				System.out.println("Cancel a Policy");
				System.out.print("Input Policy Number: ");
				policyIDInput = input.nextInt();
				input.nextLine(); //Flush
				isMatch = false;

				for (CustomerAccount custObj : customerList) {
					if (custObj.getPolicy(policyIDInput)) {
						
						isMatch = true;
					}
				}

				if (!isMatch) {
					System.out.println("Policy does not exist!");
				}
				
				break;
			case 4: // Claim Functionality
				break;

			case 5: // Search Account Functionality
				isMatch = false;

				System.out.println("\nSearch for an Account");
				System.out.print("Search Account via name? [y] if yes: ");
				inputString = input.nextLine();
				
				if (inputString.equalsIgnoreCase("y")) {
					System.out.print("Input Customer's First Name: ");
					firstName = input.nextLine();
					System.out.print("Input Customer's Last Name: ");
					lastName = input.nextLine();

					for (CustomerAccount custObj : customerList) {
						if (custObj.getFirstName().equals(firstName) && custObj.getLastName().equals(lastName)) {
							System.out.printf("%-20s \t%-20s \t%-20s \t%-20s\n", "Account Number", "First Name",
									"Last Name", "Address");
							custObj.displayCustomerAccountInfo();
							isMatch = true;
						}
					}
				} else {
					System.out.print("Input Account Number: ");
					int acctIDInput = input.nextInt();
					input.nextLine(); // Flush

					for (CustomerAccount custObj : customerList) {
						if (custObj.getAccountNumber() == acctIDInput) {
							System.out.printf("%-20s \t%-20s \t%-20s \t%-20s\n", "Account Number", "First Name",
									"Last Name", "Address");
							custObj.displayCustomerAccountInfo();
							isMatch = true;
						}
					}
				}
				
				if (!isMatch) {
					System.out.println("Account does not exist!");
				}

				break;
			case 6: // Search Policy Functionality
				System.out.println("\nSearch for a Policy");
				System.out.print("Input Policy Number: ");
				policyIDInput = input.nextInt();
				input.nextLine(); // Flush
				isMatch = false;

				for (CustomerAccount custObj : customerList) {
					if (custObj.getPolicy(policyIDInput)) {
						System.out.printf("%-20s \t%-20s \t%-20s \t%-20s \t%-20s\n", "Policy Number", "Effective Date",
								"Expiration Date", "Policy Holder Name", "Premium Cost");
						custObj.displayCustomerPolicy(policyIDInput);
						isMatch = true;
					}
				}

				if (!isMatch) {
					System.out.println("Policy does not exist!");
				}
				break;
			case 7: //Search Claim Functionality
				break;
			case 8: // Exit
				break;
			default:
				System.out.println("Invalid choice!");
				break;

			}

		} while (choice != 8);

		// Debug Code
		System.out.printf("%-20s \t%-20s \t%-20s \t%-20s\n", "Account Number", "First Name", "Last Name", "Address");
		for (CustomerAccount custObj : customerList) {
			custObj.displayCustomerAccountInfo();
		}
		

	}

}
