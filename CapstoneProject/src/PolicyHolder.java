import java.time.LocalDate;

public class PolicyHolder {
	private String firstName;
	private String lastName;
	private String birthDate;
	private String driversLicenseNumber;
	private LocalDate driversLicenseIssued;
	private String address;
	
	public PolicyHolder(String firstName, String lastName, String birthDate, String address, String driversLicenseNumber, LocalDate driversLicenseIssued) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.address = address;
		this.driversLicenseNumber = driversLicenseNumber;
		this.driversLicenseIssued = driversLicenseIssued;
	}
	
	public PolicyHolder (CustomerAccount custObj, String birthDate, String driversLicenseNumber, LocalDate driversLicensedIssued) {
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

	public String getBirthDate() {
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
