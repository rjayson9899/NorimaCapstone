package policyTest;

import java.time.LocalDate;

public class PolicyHolder {

	private String fname;
	private String lname;
	private LocalDate dateOfBirth;
	private String license;
	private LocalDate licenseIssueDate;
	
	
	public PolicyHolder(String fname, String lname,LocalDate dateOfBirth, String license, LocalDate licenseDateIssueDate) {
		this.fname = fname;
		this.lname = lname;
		this.dateOfBirth = dateOfBirth;
		this.license = license;
		this.licenseIssueDate = licenseDateIssueDate;
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

	
	
}
