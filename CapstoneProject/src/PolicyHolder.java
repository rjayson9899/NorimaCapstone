import java.time.LocalDate;

/**
 * Java Course 4 Module 3, Norima Java Developer Capstone Project
 * Policy Holder File
 *@author Edmark
 *@Description: This capstone project is a simple Automobile Insurance Policy and Claims Administration System (PAS) 
 *				that manages customer automobile insurance policies and accident claims for an insurance company. 
 *				The program was made by using Object Oriented Programming Principles.
 *Created date: June 6, 2022
 *Modified date: June 14, 2022
 *@Modified by:
 *
 */

public class PolicyHolder {
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String driversLicenseNumber;
	private LocalDate driversLicenseIssued;
	private String address;
	
	public PolicyHolder(String firstName, String lastName, LocalDate birthDate, String address, String driversLicenseNumber, LocalDate driversLicenseIssued) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.address = address;
		this.driversLicenseNumber = driversLicenseNumber;
		this.driversLicenseIssued = driversLicenseIssued;
	}
	
	public PolicyHolder (CustomerAccount custObj, LocalDate birthDate, String driversLicenseNumber, LocalDate driversLicensedIssued) {
		this.firstName = custObj.getFirstName();
		this.lastName = custObj.getLastName();
		this.address = custObj.getCustomerAddress();
		this.birthDate = birthDate;
		this.driversLicenseNumber = driversLicenseNumber;
		this.driversLicenseIssued = driversLicensedIssued;	
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getDriversLicenseNumber() {
		return driversLicenseNumber;
	}

	public LocalDate getDriversLicenseIssued() {
		return driversLicenseIssued;
	}
	
	public int getLicensedYear() {
		return driversLicenseIssued.getYear();
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public String getAddress() {
		return address;
	}
	
	public String toString() {
		String str;
		
		str = firstName + "\n";
		str += lastName + "\n";
		str += address + "\n";
		str += driversLicenseNumber + "\n";
		str += birthDate + "\n";
		str += driversLicenseIssued.toString();
		
		return str;
	}
	
}
