package policyTest;

import java.util.Date;

public class PolicyHolder {

	private String fname;
	private String lname;
	private String license;
	private Date licenseIssueDate;
	
	public PolicyHolder() {
		
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
	public Date getLicenseIssueDate() {
		return licenseIssueDate;
	}
	public void setLicenseIssueDate(Date licenseIssueDate) {
		this.licenseIssueDate = licenseIssueDate;
	}
	
	
}
