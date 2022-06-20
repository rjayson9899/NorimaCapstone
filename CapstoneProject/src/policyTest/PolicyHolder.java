package policyTest;

import java.time.LocalDate;

/**
 * Java Course 4, Capstone
 * 
 * Policy Holder Class
   *
 * @author Mac Kristan B. Isaac
 */

public class PolicyHolder{

	private String fname;
	private String lname;
	private LocalDate dateOfBirth;
	private String license;
	private LocalDate licenseIssueDate;
	
	//constructor
	public PolicyHolder(String fname, String lname,LocalDate dateOfBirth, String license, LocalDate licenseDateIssueDate) {
		this.fname = fname;
		this.lname = lname;
		this.dateOfBirth = dateOfBirth;
		this.license = license;
		this.licenseIssueDate = licenseDateIssueDate;
	}
	
	//getters and setters
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
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public LocalDate getLicenseIssueDate() {
		return licenseIssueDate;
	}
	public void setLicenseIssueDate(LocalDate licenseIssueDate) {
		this.licenseIssueDate = licenseIssueDate;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	//outputting policy holder details
	public void getDetails() {
		System.out.println("-------------------------------");
		System.out.println("       Policy Holder           ");
		System.out.println("-------------------------------");
		System.out.println("Name: " + fname + " " + lname);
		System.out.println("Birth date: " + dateOfBirth);
		System.out.println("License Number: " + license);
		System.out.println("License Issued date: " + licenseIssueDate);
	}

	
	
}
