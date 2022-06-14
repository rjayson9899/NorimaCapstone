/*
 * This is the main driver of the whole capstone project.
 * This program is able to create a customer account and a
 * accompanying policy that can  expire, be cancelled or claimed.
 * @author Macario N. Peralta V
 * Date created: June 6 2022
 */
package CapStone;

public class PolicyHolder {
	private String fName;
	private String lName;
	private String bDay;
	private String address;
	private String dLicense;
	private String dateLic;
	
	public PolicyHolder(String fName, String lName, String bDay, String address, String dL, String dateLic) {
		this.fName = fName;
		this.lName = lName;
		this.bDay = bDay;
		this.address = address;
		this.dLicense = dL;
		this.dateLic = dateLic;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}
	
	public void setlName(String lName) {
		this.lName = lName;
	}
	
	public void setBDay(String bDay) {
		this.bDay = bDay;
	}
	
	public void setAddress(String addz) {
		
	}
	
	public void setdLicNum(String licNum) {
		
	}
	
	public void setDateLic(String dateLic) {
		
	}
	
	public void getAddress() {
		
	}
}

