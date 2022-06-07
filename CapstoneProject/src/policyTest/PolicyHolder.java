package policyTest;

import java.util.Date;

public class PolicyHolder {

	private String fname;
	private String lname;
	private Date dateOfBirth;
	private String license;
	private Date licenseIssueDate;
	
	public PolicyHolder(String fname, String lname,Date dateOfBirth, String license, Date licenseDateIssueDate) {
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
	public Date getLicenseIssueDate() {
		return licenseIssueDate;
	}
	public void setLicenseIssueDate(Date licenseIssueDate) {
		this.licenseIssueDate = licenseIssueDate;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
}
