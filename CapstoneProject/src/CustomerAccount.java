/*
 * This is the customer object that 
 */
package CapStone;

import java.util.ArrayList;

public class CustomerAccount {
	private String cusAccNum;
	private String fName;
	private String lName;
	private String address;
	
	ArrayList<Policy> pol = new ArrayList<>();
	ArrayList<PolicyHolder> polHold = new ArrayList<>();
	
	public CustomerAccount(String fName, String lName, String address, String accNum) {
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.cusAccNum = accNum;	
	}
		
	public void createAcc(String fName, String lName, String address, String accNum) {
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.cusAccNum = accNum;
		//add checker for account duplication		
	}
	
	public void buyPolicy() {
		
	}
	
	public void seeDetails() {
		
		System.out.println("================================");
		System.out.println("Account number: " + cusAccNum);
		System.out.println("First Name: " + this.fName);
		System.out.println("Last Name: " + this.lName);
		System.out.println("Address: " + this.address);
		System.out.println("Policies: ");
		System.out.println("Policy holders: ");
		System.out.println("================================");
	}
	
	public String getfName() {
		return this.fName;
	}
	
	public String getlName() {
		return this.lName;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public String getAccNum() {
		return this.cusAccNum;
	}
	
	/*public void quotePol() {
		pol.add(new Policy(fName, address, cusAccNum));
	} */
}
