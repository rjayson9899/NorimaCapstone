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
		String firstName, lastName, customerAddress;

		Scanner input = new Scanner(System.in);
		CustomerAccount customer;
		ArrayList<Integer> customerIDList = new ArrayList<Integer>();
		ArrayList<CustomerAccount> customerList = new ArrayList<CustomerAccount>();

		Policy policy = new Policy();

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
				// Quote Policy

				// Validation if Customer ID already exists
				Boolean isMatch = false;
				System.out.print("Input Customer ID number: ");
				int inputCustomerID = input.nextInt();

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
						custObj.addPolicies(policyIDList);
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
				}

				// Validation if Full
				if (tempID <= Policy.POLICY_MAX) {

					policy = new Policy(tempID);

					// Valdation if Customer is Policy Holder

				}

				// Create a Policy
				// policy.createPolicyDate();
				int year, month, day;
				LocalDate effectiveDatePolicy, expirationDatePolicy;

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
						System.out.printf("%-20s \t%-20s \t%-20s \t%-20s\n","Account Number", "First Name", "Last Name", "Address");
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
