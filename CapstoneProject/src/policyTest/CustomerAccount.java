package policyTest;

import java.util.ArrayList;

public class CustomerAccount {

	private int accountNum;
	private String fname;
	private String lname;
	private String address;
	private ArrayList<Policy> policyAct;
	private ArrayList<PolicyHolder> policyHolders;
	
	public CustomerAccount(String fname, String lname, String address) {
		this.fname = fname;
		this.lname = lname;
		this.address = address;
	}
	
	public void generateId() {
		this.accountNum = 1;
		//input code for id generation
	}

	public int getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<Policy> getPolicyAct() {
		return policyAct;
	}

	public void setPolicyAct(ArrayList<Policy> policyAct) {
		this.policyAct = policyAct;
	}

	public ArrayList<PolicyHolder> getPolicyHolders() {
		return policyHolders;
	}

	public void setPolicyHolders(ArrayList<PolicyHolder> policyHolders) {
		this.policyHolders = policyHolders;
	}
	
	
}
