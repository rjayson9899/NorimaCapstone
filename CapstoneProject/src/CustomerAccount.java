package CapStone;

import java.util.ArrayList;

public class CustomerAccount {
	private int cusAccNum;
	private String fName;
	private String lName;
	private String address;
	
	ArrayList<Policy> pol = new ArrayList<>();
	ArrayList<PolicyHolder> polHold = new ArrayList<>();
	
	public CustomerAccount() {
		
	}
		
	public void createAcc(String fName, String lName, String address) {

		//add checker for account duplication		
	}

	public void buyPolicy() {
		
	}
	
	public void searchCust(String fName, String lName) {
		
		System.out.println("================================");
		System.out.println("Account number: " + cusAccNum);
		System.out.println("First Name: " + this.fName);
		System.out.println("Last Name: " + this.lName);
		System.out.println("Address: " + this.address);
		System.out.println("Policies: ");
		System.out.println("Policy holders: ");
		System.out.println("================================");
	}

	
}
