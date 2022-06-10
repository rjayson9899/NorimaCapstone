/**
 * Norima Capstone Project, Vehicle Class File.
 * 
 * For the Norima Capstone project, the student is assigned to create a console-based
 * Policy and Claims Administration System based on the specifications provided in the 
 * Robertson Brightspace Java 102 Part 2 course.
 * 
 * This class file contains details consisting a Vehicle. Since premium is to be computed
 * by another class, a setter method is available to allow placing computed premiums into
 * Vehicle instances.
 * 
 * @author Roger Jayson M. Mendez III
 */

public class Vehicle {

	private String make;
	private String model;
	private int year;
	private String type;
	private String fuelType;
	private double purchasePrice;
	private double premium;
	
	/**
	 * Constructor
	 * 
	 * @param make - make of vehicle
	 * @param model - model of vehicle
	 * @param year - year of vehicle
	 * @param type - type of vehicle
	 * @param fuelType - fuel type of vehicle
	 * @param purchasePrice - purchase price of vehicle
	 */
	public Vehicle(String make, String model, int year, String type, String fuelType, double purchasePrice) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.type = type;
		this.fuelType = fuelType;
		this.purchasePrice = purchasePrice;
	}
	
	/**
	 * Returns make of current instance
	 * 
	 * @return String - make
	 */
	public String getMake() {
		return make;
	}
	
	/**
	 * Returns model of current instance
	 * 
	 * @return String - model
	 */
	public String getModel() {
		return model;
	}
	
	/**
	 * Returns year of current instance
	 * 
	 * @return int - year
	 */
	public int getYear() {
		return this.year;
	}
	
	/**
	 * Returns type of current instance
	 * 
	 * @return String- type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Returns fuel type of current instance
	 * 
	 * @return String - fuel type
	 */
	public String getFuelType() {
		return fuelType;
	}
	
	/**
	 * Returns purchase price of current instance
	 * 
	 * @return double - purchase price
	 */
	public double getPurchasePrice() {
		return this.purchasePrice;
	}
	
	/**
	 * Returns premium of current instance
	 * 
	 * @return double - premium
	 */
	public double getPremium() {
		return this.premium;
	}
	
	/**
	 * Sets the premium of instance to value of premium param
	 * 
	 * @param premium - value to set
	 */
	public void setPremium(double premium) {
		this.premium = premium;
	}
	
}
