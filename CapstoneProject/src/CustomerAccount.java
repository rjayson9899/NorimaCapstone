import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
	
	public Policy getPolicyMatchingId(int id) throws IllegalArgumentException {
		for (Policy polObj: policyList) {
			if (polObj.getPolicyNumber() == id) {
				return polObj;
			}
		}
		
		throw new IllegalArgumentException("No Policy found with matching ID");
	}
	
	public void addPolicy(Policy polObj) {
		this.policyList.add(polObj);
		this.policyHolderList.add(polObj.getHolder());
	}
	
	public boolean hasPolicy(int policyId) {
		for (Policy polObj: policyList) {
			if (polObj.getPolicyNumber() == policyId) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean cancelAccountPolicy(int policyId) throws IllegalArgumentException {
		for (Policy polObj: policyList) {
			if (polObj.getPolicyNumber() == policyId) {
				if (polObj.isExpired()) {
					return false;
				}
				else {
					polObj.cancelPolicy();
					return true;
				}
			}
		}
		
		throw new IllegalArgumentException("No Policy found with matching ID");
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
		System.out.printf("\n%-20s\t%-20s\t%-20s\t%-20s\t%20s\t%20s\n", "Policy Number", "Effective Date", "Expiration Date", "Policy Holder Name", "Premium", "Valid?");
	}
	
	public void printPolicies() {
		NumberFormat money = NumberFormat.getCurrencyInstance(Locale.US);
		String policyIdString, validString, premiumString;
		for (Policy polObj: policyList) {
			policyIdString = String.format("%06d", polObj.getPolicyNumber());
			premiumString = money.format(polObj.getPremium());
			if (polObj.isExpired()) {
				validString = "No";
			}
			else {
				validString = "Yes";
			}
			System.out.printf("%-20s\t%-20s\t%-20s\t%-20s\t%20s\t%20s\n", policyIdString, polObj.getEffectiveDate(), polObj.getExpirationDate(), polObj.getHolderName(), premiumString, validString);
		}
	}
	
	public void printPolicyMatchingId(int id) {
		NumberFormat money = NumberFormat.getCurrencyInstance(Locale.US);
		String policyIdString, validString, premiumString;
		for (Policy polObj: policyList) {
			if (polObj.getPolicyNumber() == id) {
				policyIdString = String.format("%06d", polObj.getPolicyNumber());
				premiumString = money.format(polObj.getPremium());
				if ((polObj.getExpirationDate().compareTo(LocalDate.now())) < 0) {
					validString = "No";
				}
				else {
					validString = "Yes";
				}
				System.out.printf("%-20s\t%-20s\t%-20s\t%-20s\t%20s\t%20s\n", policyIdString, polObj.getEffectiveDate(), polObj.getExpirationDate(), polObj.getHolderName(), premiumString, validString);
			}
		}
	}
	
	public static void printVehicleHeader() {
		System.out.printf("\n%-20s\t%-20s\t%-20s\t%-20s\t%-20s\t%-20s\t%20s\t%20s\n", "Policy Number", "Make", "Model", "Year", "Type", "Fuel Type", "Purchase Price", "Premium");
	}
	
	public void printVehicles() {
		NumberFormat money = NumberFormat.getCurrencyInstance(Locale.US);
		String policyNumberString;
		
		for (Policy polObj: policyList) {
			for (Vehicle vhcObj: polObj.getVehicleList()) {
				policyNumberString = String.format("%06d", polObj.getPolicyNumber());
				System.out.printf("%-20s\t%-20s\t%-20s\t%-20s\t%-20s\t%-20s\t%20s\t%20s\n", policyNumberString, vhcObj.getMake(), vhcObj.getModel(), vhcObj.getYear(), vhcObj.getType(), vhcObj.getFuelType(), money.format(vhcObj.getPurchasePrice()), money.format(vhcObj.getPremium()));
			}
		}

	}
	
	public static void printPolicyHolderHeader() {
		System.out.printf("\n%-20s\t%-20s\t%-20s\t%-20s\t%-20s\n", "First Name", "Last Name", "Birth Date", "License Number", "License Date");
	}
	
	public void printPolicyHolders() {
		for (PolicyHolder hldrObj: policyHolderList) {
			System.out.printf("%-20s\t%-20s\t%-20s\t%-20s\t%-20s\n", hldrObj.getFirstName(), hldrObj.getLastName(), hldrObj.getBirthDate(), hldrObj.getDriverLicenseNumber(), hldrObj.getLicenseDate());
		}
	}
	
	protected void addPolicyIds(List<Integer> idList) {
		for (Policy polObj: policyList) {
			idList.add(Integer.valueOf(polObj.getPolicyNumber()));
		}
	}
	
}
