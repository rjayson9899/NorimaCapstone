import java.time.LocalDate;

public class PolicyHolder {
	
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String driverLicenseNumber;
	private LocalDate licenseDate;
	
	public PolicyHolder(String firstName, String lastName, LocalDate birthDate, String driverLicenseNumber, LocalDate licenseDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.driverLicenseNumber = driverLicenseNumber;
		this.licenseDate = licenseDate;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	public String getDriverLicenseNumber() {
		return driverLicenseNumber;
	}
	
	public int getYear() {
		return licenseDate.getYear();
	}
	
}
