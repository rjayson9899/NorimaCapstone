import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerAccount {
	
	private final int accountNumber;
	private String firstName;
	private String lastName;
	private String address;
	private ArrayList<Policy> policyList = new ArrayList<Policy>();
	private ArrayList<PolicyHolder> policyHolderList = new ArrayList<PolicyHolder>();
	
	public CustomerAccount(int accountNumber, String firstName, String lastName, String address) {
		this.accountNumber = accountNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
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
	
	public void addPolicy(Policy polObj) {
		this.policyList.add(polObj);
	}
	
	public boolean hasPolicy(int policyId) {
		for (Policy polObj: policyList) {
			if (polObj.getPolicyNumber() == policyId) {
				return true;
			}
		}
		
		return false;
	}
	
	public void cancelAccountPolicy(int policyId) {
		for (Policy polObj: policyList) {
			if (polObj.getPolicyNumber() == policyId) {
				polObj.cancelPolicy();
			}
		}
	}
	
	public static int generateUniqueId(List<CustomerAccount> customerList) {
		int limit = 9999;
		ArrayList<Integer> idList = new ArrayList<Integer>();
		
		for (CustomerAccount i: customerList) {
			idList.add(Integer.valueOf(i.getAccountNumber()));
		}
		
		
		for (int i = 0; i <= limit; i++) {
			if (!(idList.contains(Integer.valueOf(i)))) {
				return i;
			}
		}
		
		return -1;
	}
	
	public static void printCustomerAccountHeader() {
		System.out.printf("\n%-20s\t%-20s\t%-20s\t%-20s\n", "Account Number", "First Name", "Last Name", "Address");
	}
	
	public void printCustomerAccountDetails() {
		String accountNumberString = String.format("%04d", this.accountNumber);
		System.out.printf("%-20s\t%-20s\t%-20s\t%-20s\n", accountNumberString, this.firstName, this.lastName, this.address);
	}
	
	public static void printPolicyHeader() {
		System.out.printf("\n%-20s\t%-20s\t%-20s\t%-20s\t%-20s\t%-20s\n", "Policy Number", "Effective Date", "Expiration Date", "Policy Holder Name", "Premium", "Valid?");
	}
	
	public void printPolicies() {
		String policyIdString, validString;
		for (Policy polObj: policyList) {
			policyIdString = String.format("%06d", polObj.getPolicyNumber());
			if ((polObj.getExpirationDate().compareTo(LocalDate.now())) < 0) {
				validString = "No";
			}
			else {
				validString = "Yes";
			}
			System.out.printf("%-20s\t%-20s\t%-20s\t%-20s\t%-20.2f\t%-20s\n", policyIdString, polObj.getEffectiveDate(), polObj.getExpirationDate(), polObj.getHolderName(), polObj.getPremium(), validString);
		}
	}
	
	public void printPolicyMatchingId(int id) {
		String policyIdString, validString;
		for (Policy polObj: policyList) {
			if (polObj.getPolicyNumber() == id) {
				policyIdString = String.format("%06d", polObj.getPolicyNumber());
				if ((polObj.getExpirationDate().compareTo(LocalDate.now())) < 0) {
					validString = "No";
				}
				else {
					validString = "Yes";
				}
				System.out.printf("%-20s\t%-20s\t%-20s\t%-20s\t%-20.2f\t%-20s\n", policyIdString, polObj.getEffectiveDate(), polObj.getExpirationDate(), polObj.getHolderName(), polObj.getPremium(), validString);
			}
		}
	}
	
}
