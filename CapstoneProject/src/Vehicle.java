/**
 * Java Course 4 Module 3, Norima Java Developer Capstone Project
 * Vehicle Class File
 *@author Edmark
 *@Description: This capstone project is a simple Automobile Insurance Policy and Claims Administration System (PAS) 
 *				that manages customer automobile insurance policies and accident claims for an insurance company. 
 *				The program was made by using Object Oriented Programming Principles.
 *Created date: June 6, 2022
 *Modified date: June 14, 2022
 *@Modified by:
 *
 */

public class Vehicle {
	private String vehicleMake;
	private String vehicleModel;
	private int vehicleYear;
	private String vehicleType;
	private String vehicleFuel;
	private double vehiclePrice;
	private String vehicleColor;
	private double vehiclePremium;
	
	public Vehicle(String vehicleMake, String vehicleModel, int vehicleYear, String vehicleType, String vehicleFuel,
			double vehiclePrice, String vehicleColor) {
		this.vehicleMake = vehicleMake;
		this.vehicleModel = vehicleModel;
		this.vehicleYear = vehicleYear;
		this.vehicleType = vehicleType;
		this.vehicleFuel = vehicleFuel;
		this.vehiclePrice = vehiclePrice;
		this.vehicleColor = vehicleColor;
	}
	
	public String getVehicleMake() {
		return vehicleMake;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}
	public int getVehicleYear() {
		return vehicleYear;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public String getVehicleFuel() {
		return vehicleFuel;
	}
	public double getVehiclePrice() {
		return vehiclePrice;
	}
	public String getVehicleColor() {
		return vehicleColor;
	}
	public double getVehiclePremium() {
		return vehiclePremium;
	}
	
	public void setVehiclePremium(double vehiclePremium) {
		this.vehiclePremium = vehiclePremium;
	}
	
	public String toString() {
		String str;
		
		str = "Vehicle Make: " + vehicleMake + "\n";
		str += "Vehicle Model: " + vehicleModel + "\n";
		str += "Vehicle Year: " + vehicleYear + "\n";
		str += "Vehicle Type: " + vehicleType + "\n";
		str += "Vehicle Fuel: " + vehicleFuel + "\n";
		str += "Vehicle Price: " + vehiclePrice + "\n";
		str += "Vehicle Color: " + vehicleColor;
		return str;
	}
	
}

