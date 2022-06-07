import java.util.ArrayList;

public class CustomerAccount {
	private int accountNumber;
	private String firstName;
	private String lastName;
	private String customerAddress;
	public static final int CUSTOMER_MAX = 9999;
	
	private ArrayList<Policy> policyList;
	private ArrayList<PolicyHolder> policyHolderList;
	
	public CustomerAccount(int accountNumber, String firstName, String lastName, String customerAddress) {
		this.accountNumber = accountNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.customerAddress = customerAddress;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void addPolicies(ArrayList<Integer> policyIDList) {
		for(Policy polObj : policyList) {
			policyIDList.add(Integer.valueOf(polObj.getPolicyNumber()));
		}
	}
	
	public void displayCustomerAccountInfo () {
		System.out.printf("%04d \t\t\t%-20s \t%-20s \t%-20s\n", this.accountNumber, this.firstName, this.lastName, this.customerAddress);
	}
	
}
