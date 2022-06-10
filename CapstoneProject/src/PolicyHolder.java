import java.time.LocalDate;

/**
 * Norima Capstone Project, Policy Holder Class File.
 * 
 * For the Norima Capstone project, the student is assigned to create a console-based
 * Policy and Claims Administration System based on the specifications provided in the 
 * Robertson Brightspace Java 102 Part 2 course.
 * 
 * This class file contains details consisting a Policy Holder. Values can only be set
 * upon construction. Aside from that, methods for retrieving set values are available.
 * 
 * @author Roger Jayson M. Mendez III
 */

public class PolicyHolder {
	
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String driverLicenseNumber;
	private LocalDate licenseDate;
	
	/**
	 * Constructor
	 * Use for making Policy Holder with custom name
	 * 
	 * @param firstName - first name of custom policy holder
	 * @param lastName - last name of custom policy holder
	 * @param birthDate - date of birth of policy holder
	 * @param driverLicenseNumber - license number of policy holder
	 * @param licenseDate - license issue date of policy holder
	 */
	public PolicyHolder(String firstName, String lastName, LocalDate birthDate, String driverLicenseNumber, LocalDate licenseDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.driverLicenseNumber = driverLicenseNumber;
		this.licenseDate = licenseDate;
	}
	
	/**
	 * Constructor
	 * Uses acctObj param to set first name and last name of Policy Holder
	 * 
	 * @param acctObj - CustomerAccount instance
	 * @param birthDate - date of birth of policy holder
	 * @param driverLicenseNumber - license number of policy holder
	 * @param licenseDate - license issue date of policy holder
	 */
	public PolicyHolder(CustomerAccount acctObj, LocalDate birthDate, String driverLicenseNumber, LocalDate licenseDate) {
		this.firstName = acctObj.getFirstName();
		this.lastName = acctObj.getLastName();
		this.birthDate = birthDate;
		this.driverLicenseNumber = driverLicenseNumber;
		this.licenseDate = licenseDate;
	}
	
	/**
	 * Returns first name of current instance
	 * 
	 * @return String - first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Returns last name of current instance
	 * 
	 * @return String - last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Returns birth date of current instance
	 * 
	 * @return LocalDate - birth date
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	/**
	 * Returns license number of current instance
	 * 
	 * @return String - license number
	 */
	public String getDriverLicenseNumber() {
		return driverLicenseNumber;
	}
	
	/**
	 * Returns license issue date of current instance
	 * 
	 * @return LocalDate - license issue date
	 */
	public LocalDate getLicenseDate() {
		return licenseDate;
	}
	
	/**
	 * Gets year of date license was issue
	 * 
	 * @return int - license issue year
	 */
	public int getLicenseYear() {
		return licenseDate.getYear();
	}
	
}
