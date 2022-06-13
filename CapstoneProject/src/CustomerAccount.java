/*
 * This is the customer object that holds the data for the 
 * main account holder. 
 * @author Macario N. Peralta V
 * Date created: June 6 2022
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
		this.fName = fName.substring(0, 1).toUpperCase() + fName.substring(1);
		this.lName = lName.substring(0, 1).toUpperCase() + lName.substring(1);
		this.address = address;
		this.cusAccNum = accNum;	
	}
	
	public void showPolicy() {
		for(Policy poly : pol) {
			poly.seeDetails();
		}
	}
	
	public void seeDetails() {
		
		System.out.println("==================Customer Account holder===================");
		System.out.println("Account number: " + cusAccNum);
		System.out.println("Full name: " + this.fName + " " + this.lName);
		System.out.println("Address: " + this.address);
		for(Policy policy : pol) {
			policy.checkStatus();
			policy.seeDetails();
		}
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
	
	public int getAccNumb() {
		int accNum = Integer.parseInt(cusAccNum);
		return accNum;
	}
	public String getAccNum() {
		return cusAccNum;
	}
}