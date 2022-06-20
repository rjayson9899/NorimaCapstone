package policyTest;

import java.util.ArrayList;

/**
 * Java Course 4, Capstone
 * 
 * Customer Account Class
   *
 * @author Mac Kristan B. Isaac
 */

public class CustomerAccount{

	private String accountNum;
	private String fname;
	private String lname;
	private String address;
	private ArrayList<Policy> policyAct = new ArrayList<>();
	private ArrayList<PolicyHolder> policyHolders = new ArrayList<>();
	
	//constructor
	public CustomerAccount(String fname, String lname, String address) {
		this.fname = fname;
		this.lname = lname;
		this.address = address;
	}
	
	//ID generation
	public void generateId(int count) {
		int accountNumInt = count + 1;
		accountNum = Integer.toString(accountNumInt);
		StringBuilder sb = new StringBuilder();
		for(int x = 0; x < 4 - accountNum.length(); x++){
			sb.append('0');
		}
		sb.append(accountNum);
		accountNum = sb.toString();
	}

	//getters and setters
	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
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

	//adding information to the policy account arraylist
	public void addPolicyAct(Policy policyAct) {
		this.policyAct.add(policyAct);
	}

	public ArrayList<PolicyHolder> getPolicyHolders() {
		return policyHolders;
	}

	//adding information to the policy account arraylist
	public void addPolicyHolders() {
		policyHolders.add(policyAct.get(policyAct.size()-1).getPolicyHolder());
	}
	
	//output for customer accounts
	public void getDetails(){
		int count = 1;
		
		
		System.out.println("-------------------------------");
		System.out.println("       Account Details: ");
		System.out.println("-------------------------------");
		System.out.println("Name: " + fname + " " + lname);
		System.out.println("Address: " + address);
		if(policyAct.isEmpty()){
			System.out.println("No policies in account");
		}
		else{
			System.out.println("Policies: ");
			System.out.format("%-5s %-15s %-15s \n", "No.","Policy Number", "Status");
			for(Policy p: policyAct){
				System.out.format("%-5d %-15s %-15s \n", count, p.getPolicyNum(),p.getStatus());
				count++;
			}
			count = 1;
			System.out.println("Policy Holders: ");
			for(PolicyHolder ph: policyHolders){
				System.out.println( count + "   "+ ph.getFname() + " " + ph.getLname());
				count++;
			}
		}
		
	}
	
}
