/*
 * This is the policy holder class of the whole capstone project.
 * This class contains all of the details that the policy holder possess.
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

}

