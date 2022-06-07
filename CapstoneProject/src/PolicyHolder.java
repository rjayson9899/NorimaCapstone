public class PolicyHolder {
	private String firstName;
	private String lastName;
	private String birthDate;
	private String driversLicenseNumber;
	private String driversLicenseIssued;
	
	
	public PolicyHolder(String firstName, String lastName, String birthDate, String driversLicenseNumber, String driversLicenseIssued) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.driversLicenseNumber = driversLicenseNumber;
		this.driversLicenseIssued = driversLicenseIssued;
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

	public String getDriversLicenseIssued() {
		return driversLicenseIssued;
	}

	
}
