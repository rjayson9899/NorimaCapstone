import java.util.ArrayList;
import java.util.Scanner;

public class CustomerAccount {
	private int accountNumber;
	private String firstName;
	private String lastName;
	private String customerAddress;
	public static final int CUSTOMER_MAX = 9999;
	private ArrayList<Policy> policyAccountList;
	private ArrayList<PolicyHolder> policyHolderList;
	Scanner input = new Scanner(System.in);
	
	public CustomerAccount() {
		
	}
	
	public CustomerAccount(int accountNumber, String firstName, String lastName, String customerAddress) {
		this.accountNumber = accountNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.customerAddress = customerAddress;
	}
	
	public void createCustomerAccount() {
		System.out.print("Enter First Name: ");
		this.firstName = input.nextLine();
		System.out.print("Enter Last Name: ");
		this.lastName = input.nextLine();
		System.out.print("Enter Address: ");
		this.customerAddress = input.nextLine();
		
		System.out.println("Account created succesfully");

	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	
	public String toString() {
		String customerString;
		
		customerString = "Account Created Successfully! \n";
		customerString += "First Name: " + firstName + "\n";
		customerString += "Last Name: " + lastName + "\n";
		customerString += "Address: " + customerAddress;
		return(customerString);
	}

}
