import java.util.ArrayList;
import java.util.Scanner;

public class PASApp {
	
	private static void mainMenu() {
	System.out.println("1. Create an Account");
	System.out.println("2. Get Policy Quote and Buy");
	System.out.println("3. Cancel Policy");
	System.out.println("4. File a Claim");
	System.out.println("5. Search Account");
	System.out.println("6. Search Policy");
	System.out.println("7. Search Claim");
	System.out.println("8. Exit");
	System.out.print("Enter your choice: ");
	}

	public static void main(String[] args) {
		int choice;
		int uniqueID;
		Scanner input = new Scanner(System.in);
		CustomerAccount customer = new CustomerAccount();
		ArrayList<Integer> uniqueIDList = new ArrayList<Integer>();
		ArrayList<CustomerAccount> customerList = new ArrayList<CustomerAccount>();
		Policy tempPolicy = new Policy();
		

		do {
			mainMenu();
			choice = input.nextInt();
			switch (choice) {
				case 1: 
					//Generate a unique ID
					for(CustomerAccount custObj : customerList) {
						uniqueIDList.add(Integer.valueOf(custObj.getAccountNumber()));
					}
					for(int i = 0; i < CustomerAccount.CUSTOMER_MAX; i++) {
						if(!(uniqueIDList.contains(Integer.valueOf(i)))) {
							uniqueID = i;
							break;
						}
					}
					
					//Create a Customer Account
					customer.createCustomerAccount();
					customerList.add(customer);
					//System.out.println(customer.toString());
					break;
				case 2:
					
					//System.out.print("Input Customer ID number: ");
					//Input checker
					//System.out.print("");
					//Generate a unique Policy Number
					
					
					//Create a Policy
					tempPolicy.createPolicyDate();
					
					
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
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
