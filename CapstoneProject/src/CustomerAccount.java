import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Java Course 4 Module 3, Norima Java Developer Capstone Project
 * CustomerAccount Class File
 *@author Edmark
 *@Description: This capstone project is a simple Automobile Insurance Policy and Claims Administration System (PAS) 
 *				that manages customer automobile insurance policies and accident claims for an insurance company. 
 *				The program was made by using Object Oriented Programming Principles.
 *Created date: June 6, 2022
 *Modified date: June 14, 2022
 *@Modified by:
 *
 */

public class CustomerAccount {
	private int accountNumber;
	private String firstName;
	private String lastName;
	private String customerAddress;
	public static final int CUSTOMER_MAX = 9999;
	private ArrayList<Policy> policyList = new ArrayList<Policy>();
	private ArrayList<PolicyHolder> policyHolderList = new ArrayList<PolicyHolder>();

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

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void addPolicyIDs(ArrayList<Integer> policyIDList) {
		for (Policy polObj : policyList) {
			policyIDList.add(Integer.valueOf(polObj.getPolicyNumber()));
		}
	}
	
	public boolean hasPolicy(int policyID) {
		for (Policy polObj : policyList) {
			if (polObj.getPolicyNumber() == policyID) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Policy> getPolicyList() {
		return policyList;
	}
	
	public Policy getPolicy(int policyID) throws IllegalArgumentException {
		for (Policy polObj : policyList) {
			if (polObj.getPolicyNumber() == policyID) {
				return polObj;
			}
		}
		throw new IllegalArgumentException("No policy found!"); 
	}
	
	

	public void displayCustomerPolicy(int policyID) {
		String policyStatus = "";
		NumberFormat money = NumberFormat.getCurrencyInstance();
		LocalDate dateNow = LocalDate.now();
		
		
		System.out.printf("%-20s \t%-20s \t%-20s \t%-20s \t%-20s \t%-20s\n", "Policy Number", "Effective Date",
				"Expiration Date", "Policy Holder Name", "Premium Cost", "Policy Status");
		
		for (Policy polObj : policyList) {
			if (polObj.getPolicyNumber() == policyID) {
				if (polObj.getEffectiveDatePolicy().compareTo(dateNow) > 0) {
					policyStatus = "Scheduled";
				} else if (polObj.getEffectiveDatePolicy().compareTo(dateNow) > 0 || polObj.getExpirationDatePolicy().compareTo(dateNow) < 0){
					policyStatus = "Cancelled/Expired";
				} else {
					policyStatus = "Active";
				}
				System.out.printf("%06d \t\t\t%-20s \t%-20s \t%-20s \t%-20s \t%-20s\n", policyID, polObj.getEffectiveDatePolicy(),
						polObj.getExpirationDatePolicy(), polObj.getName(), money.format(polObj.getTotalPremium()), policyStatus);
			}
		}
	}

	public void addPolicy(Policy policyObj) {
		this.policyList.add(policyObj);
		this.policyHolderList.add(policyObj.getPolicyHolder());
	}
	
	public boolean cancelAccountPolicy(int policyID) {
		for (Policy polObj : policyList) {
			if (polObj.getPolicyNumber() == policyID) {
				if (polObj.isCancelled()) {
					return false;
				} else {
					polObj.cancelPolicy();
				}
			}
		}
		return true;
	}
	
	public boolean isPolicyCancelled(int policyID) {
		for (Policy polObj : policyList) {
			if (polObj.getPolicyNumber() == policyID) {
				return polObj.isCancelled();
			}
		}
		return false;
	}

	public void displayCustomerAccountInfo() {
		System.out.printf("%-20s \t%-20s \t%-20s \t%-20s\n", "Account Number", "First Name", "Last Name",
				"Address");
		System.out.printf("%04d \t\t\t%-20s \t%-20s \t%-20s\n", this.accountNumber, this.firstName, this.lastName,
				this.customerAddress);
	}
	
	//Debug method to display all created policies
	public void displayPolicyInfo() {
		for (Policy polObj : policyList) {
			System.out.printf("%06d \t\t\t%-20s \t%-20s \t%-20s \t%-20s \t%-20s\n", polObj.getPolicyNumber(), polObj.getEffectiveDatePolicy(),
					polObj.getExpirationDatePolicy(), polObj.getName(), polObj.getTotalPremium());
		}
	}
	
}
