import java.time.LocalDate;
import java.util.ArrayList;

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
	
	public boolean getPolicy(int policyID) {
		for (Policy polObj : policyList) {
			if (polObj.getPolicyNumber() == policyID) {
				return true;
			}
		}
		return false;
	}

	public void displayCustomerPolicy(int policyID) {
		String policyStatus;
		
		for (Policy polObj : policyList) {
			if (polObj.getPolicyNumber() == policyID) {
				if (polObj.getEffectiveDatePolicy().compareTo(polObj.getExpirationDatePolicy()) < 0) {
					policyStatus = "Active";
				} else {
					policyStatus = "Cancelled/Expired";
				}
				
				System.out.printf("%06d \t\t\t%-20s \t%-20s \t%-20s \t%-20s \t%-20s\n", policyID, polObj.getEffectiveDatePolicy(),
						polObj.getExpirationDatePolicy(), polObj.getName(), polObj.getTotalPremium(), policyStatus);
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
		System.out.printf("%04d \t\t\t%-20s \t%-20s \t%-20s\n", this.accountNumber, this.firstName, this.lastName,
				this.customerAddress);
	}
	
	

}
