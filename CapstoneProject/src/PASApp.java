import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class PASApp {
	private static Scanner input = new Scanner(System.in);

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
	}

	public static void main(String[] args) {
		int choice;
		int tempID = 0;
		int policyIDInput, customerIDInput, claimIDInput;
		int vehicleYear;

		double vehiclePrice;
		double damageRepairCost;

		LocalDate effectiveDatePolicy, customDate;
		LocalDate licensedIssueDate;
		LocalDate birthDate;
		LocalDate dateOfAccident;

		String firstName, lastName, customerAddress;
		String vehicleMake, vehicleModel, vehicleType, vehicleFuel, vehicleColor;
		String driversLicenseNumber;
		String addressOfAccident, descriptionOfAccident, descriptionOfDamage;
		String inputString;

		CustomerAccount customer;
		Policy policy;
		PolicyHolder policyHolderObj;
		Vehicle vehicleObj;

		boolean isMatch;

		ArrayList<Integer> customerIDList = new ArrayList<Integer>();
		ArrayList<CustomerAccount> customerList = new ArrayList<CustomerAccount>();
		ArrayList<Claim> claimList = new ArrayList<Claim>();

		do {
			mainMenu();
			choice = getIntegerInput("Enter your choice: ");
			switch (choice) {
			case 1:
				isMatch = false;
				// Generate a unique Customer ID
				for (CustomerAccount custObj : customerList) {
					customerIDList.add(Integer.valueOf(custObj.getAccountNumber()));
				}
				for (int i = 1; i <= CustomerAccount.CUSTOMER_MAX; i++) {
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
					firstName = getStringInput("Enter First Name: ");
					lastName = getStringInput("Enter Last Name: ");
					customerAddress = getStringInput("Enter Address: ");

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
				// Quote Policy Functionality

				// Validation if Customer ID already exists
				isMatch = false;
				customerIDInput = getIntegerInput("Input Customer ID number: ");

				for (CustomerAccount account : customerList) {
					if (customerIDInput == account.getAccountNumber()) {
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

					for (int i = 1; i <= Policy.POLICY_MAX; i++) {
						if (!(policyIDList.contains(Integer.valueOf(i)))) {
							tempID = i;
							break;
						}
						if (i == Policy.POLICY_MAX) {
							tempID = -1;
						}
					}

					// Validation if Full
					if (tempID <= Policy.POLICY_MAX) {

						policy = new Policy(tempID);

						// Validation if Customer is Policy Holder
						inputString = getStringInput("Input [y] if Account Holder is same as Policy Holder: ");
						if (inputString.equalsIgnoreCase("y")) {
							System.out.println("Account Holder is the same as Policy Holder");
							System.out.println("Enter birth date (yyyy-mm-dd)");
							birthDate = getDateInput();
							driversLicenseNumber = getStringInput("Enter drivers License Number: ");
							System.out.println("Enter the date of the license where it was first issued");
							licensedIssueDate = getDateInput();

							policyHolderObj = new PolicyHolder(customer, birthDate, driversLicenseNumber,
									licensedIssueDate);
							policy.setPolicyHolder(policyHolderObj);
						} else {
							System.out.println("Create a Policy Holder");
							firstName = getStringInput("Enter first name: ");
							lastName = getStringInput("Enter last name: ");
							System.out.println("Enter birth date (yyyy-mm-dd) ");
							birthDate = getDateInput();
							customerAddress = getStringInput("Enter address: ");
							driversLicenseNumber = getStringInput("Enter drivers License Number: ");
							System.out.println("Enter the date of the license where it was first issued");
							licensedIssueDate = getDateInput();

							policyHolderObj = new PolicyHolder(firstName, lastName, birthDate, customerAddress,
									driversLicenseNumber, licensedIssueDate);
							policy.setPolicyHolder(policyHolderObj);
						}
						// Create a Vehicle
						do {
							System.out.println("Create a Vehicle");
							vehicleMake = getStringInput("Input Car Make (Brand): ");
							vehicleModel = getStringInput("Input Car Model: ");
							vehicleYear = getIntegerInput("Input Car Year: ");
							vehicleType = getStringInput("Input Car Type: ");
							vehicleFuel = getStringInput("Input Fuel Type: ");
							vehiclePrice = getDoubleInput("Input Vehicle Purchase Price: ");
							vehicleColor = getStringInput("Input Vehicle Color: ");

							vehicleObj = new Vehicle(vehicleMake, vehicleModel, vehicleYear, vehicleType, vehicleFuel,
									vehiclePrice, vehicleColor);
							policy.addVehicle(vehicleObj);

							inputString = getStringInput("Do you want to add another vehicle [y] to add more: ");

						} while (inputString.equalsIgnoreCase("y"));

						// Issue a Policy and Create Quote (Premium)
						policy.createPolicyQuote();

						// Verification if get policy or not (If Yes Issue Policy Date)
						inputString = getStringInput("Get the policy? [y] for yes: ");

						if (inputString.equalsIgnoreCase("y")) {
							// Create a Policy Date
							// Add validations that the user cannot create Backlog Dates
							System.out.println("\nCreate a Policy Date");
							customDate = getDateInput();
							effectiveDatePolicy = customDate;
							policy.setEffectiveDatePolicy(effectiveDatePolicy);
							customer.addPolicy(policy);
							System.out.println("\nPolicy is Created");
							System.out.printf("Policy Number is: %06d\n", tempID);
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
			case 3: // Cancel a specific policy, Change the expiration date of a policy to an
					// earlier date than specified
				System.out.println("Cancel a Policy");
				policyIDInput = getIntegerInput("Input Policy Number: ");
				isMatch = false;

				for (CustomerAccount custObj : customerList) {
					if (custObj.getPolicy(policyIDInput)) {
						if (custObj.cancelAccountPolicy(policyIDInput)) {
							System.out.printf("Policy Number %06d is cancelled.\n", policyIDInput);
							isMatch = true;
						}
					}
				}
				if (!isMatch) {
					System.out.println("Policy does not exist!");
				}

				break;
			case 4: // Claim Functionality
				System.out.println("File a Claim");
				policyIDInput = getIntegerInput("Input Policy Number: ");
				isMatch = false;

				ArrayList<Integer> claimIDList = new ArrayList<Integer>();

				// Validity if policy is not cancel/expired;
				for (CustomerAccount custObj : customerList) {
					if (custObj.getPolicy(policyIDInput)) {
						if (custObj.isPolicyCancelled(policyIDInput)) {
							System.out.println("Cannot Claim Policy that is Scheduled/Expired/Cancelled");
						} else {
							isMatch = true;
						}
					}
				}

				if (isMatch) {
					for (Claim claimObj : claimList) {
						claimIDList.add(Integer.valueOf(claimObj.getClaimNumber()));
					}

					// if can claim generate unique claim ID
					for (int i = 1; i < Claim.CLAIM_MAX; i++) {
						if (!(claimIDList.contains(Integer.valueOf(i)))) {
							tempID = i;
							break;
						}
						if (i == Claim.CLAIM_MAX) {
							tempID = -1;
						}
					}
					if (tempID >= 0) {
						// Create a Claim
						System.out.println("\nFiling an Accident Claim against a Policy");
						System.out.println("Enter date of accident (yyyy-mm-dd)");
						dateOfAccident = getDateInput();
						addressOfAccident = getStringInput("Enter the address of where the accident happened: ");
						descriptionOfAccident = getStringInput("Enter the description of the accident: ");
						descriptionOfDamage = getStringInput("Enter the description of damage to vehicle: ");
						damageRepairCost = getDoubleInput("Enter estimated cost of repairs: ");

						claimList.add(new Claim(tempID, dateOfAccident, addressOfAccident, descriptionOfAccident,
								descriptionOfDamage, damageRepairCost));

						System.out.println("Claim created succesfully!");
						System.out.printf("Your claim number is: C%06d\n", tempID);
					} else {
						System.out.println("System is full of claims, cannot create a new claim!");
					}
				} 
				
				if (!isMatch) {
					System.out.println("Policy does not exist!");
				}

				break;

			case 5: // Search Account Functionality
				isMatch = false;

				System.out.println("\nSearch for an Account");
				inputString = getStringInput("Search Account via name? [y] if yes: ");

				if (inputString.equalsIgnoreCase("y")) {
					System.out.print("Input Customer's First Name: ");
					firstName = input.nextLine();
					System.out.print("Input Customer's Last Name: ");
					lastName = input.nextLine();

					for (CustomerAccount custObj : customerList) {
						if (custObj.getFirstName().equals(firstName) && custObj.getLastName().equals(lastName)) {
							custObj.displayCustomerAccountInfo();
							isMatch = true;
						}
					}
				} else {
					customerIDInput = getIntegerInput("Input Account Number: ");

					for (CustomerAccount custObj : customerList) {
						if (custObj.getAccountNumber() == customerIDInput) {
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
				policyIDInput = getIntegerInput("Input Policy Number: ");
				isMatch = false;

				for (CustomerAccount custObj : customerList) {
					if (custObj.getPolicy(policyIDInput)) {
						custObj.displayCustomerPolicy(policyIDInput);
						isMatch = true;
					}
				}

				if (!isMatch) {
					System.out.println("Policy does not exist!");
				}
				break;

			case 7: // Search Claim Functionality
				System.out.println("Search for a Claim");
				claimIDInput = getIntegerInput("Input Claim Number: C");
				isMatch = false;

				for (Claim claimObj : claimList) {
					if (claimObj.getClaimNumber() == claimIDInput) {
						claimObj.displayClaimDetails();
						isMatch = true;
					}
				}

				if (!isMatch) {
					System.out.println("Claim does not exist!");
				}
				break;

			case 8: // Exit
				break;

			default:
				System.out.println("Invalid choice!");
				break;
			}
		} while (choice != 8);
	}

	private static String getStringInput(String displayMessage) {
		String[] special = { "\'", "\"", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "+", "{", "}", "|", "[", "]",
				"\\", ":", ";", "<", ">", "/", "?", "`", "~", "_", "=" };
		String inputString = "";

		do {
			try {
				System.out.print(displayMessage);
				inputString = input.nextLine();
				inputString = inputString.trim();

				Double.parseDouble(inputString);
				System.out.println("Input cannot be a number only");
				inputString = "";
			} catch (NumberFormatException e) {
				if (inputString.equals("")) {
					System.out.println("Invalid input, cannot be blank!");
				} else {
					for (int i = 0; i < special.length; i++) {
						if (inputString.contains(special[i])) {
							System.out.println("Input cannot contain any special characters!");
							inputString = "";
							break;
						}
					}
				}
			}
		} while (inputString.equals(""));
		return inputString;
	}

	private static int getIntegerInput(String displayMessage) {
		String inputString = "";
		int parsedInt = 0;

		do {
			try {
				System.out.print(displayMessage);
				inputString = input.nextLine();
				inputString = inputString.trim();
				parsedInt = Integer.parseInt(inputString);
			} catch (NumberFormatException e) {
				if (inputString.equals("")) {
					System.out.println("Invalid input, cannot be blank!");
				} else {
					System.out.println("Invalid input!");
					inputString = "";
				}
			}
		} while (inputString.equals(""));
		return parsedInt;
	}

	private static LocalDate getDateInput() {
		LocalDate inputDate = LocalDate.now();
		int year = 0;
		int month = 0;
		int day = 0;

		do {
			year = getIntegerInput("Enter year (yyyy): ");

			if (year < 1950) {
				System.out.println("Year cannot be less than 1950");
			} else if (year > 9999)
				System.out.println("Year cannot be greater than 9999");
		} while (year <= 1950 || year > 9999);

		do {
			month = getIntegerInput("Enter month (mm): ");

			if (month > 12 || month < 1) {
				System.out.println("Enter month ranging from (1-12)");
			}
		} while (month > 12 || month < 1);

		do {
			day = getIntegerInput("Enter day (dd): ");

			try {
				inputDate = LocalDate.of(year, month, day);
			} catch (DateTimeException e) {
				System.out.println("Invalid day for inputted day and month");
				System.out.println(e.getMessage());
				day = -1;
			}
		} while (day == -1);

		return inputDate;
	}

	private static double getDoubleInput(String displayMessage) {
		String inputString = "";
		double parsedDouble = 0.0;

		do {
			try {
				System.out.print(displayMessage);
				inputString = input.nextLine();
				inputString = inputString.trim();
				parsedDouble = Double.parseDouble(inputString);
			} catch (NumberFormatException e) {
				if (inputString.equals("")) {
					System.out.println("Invalid input, cannot be blank!");
				} else {
					System.out.println("Invalid input!");
					inputString = "";
				}
			}
		} while (inputString.equals(""));
		return parsedDouble;
	}

}
