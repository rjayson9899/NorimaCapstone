/*
 * This is the vehicle object that holds the data of the 
 * vehicles covered by the policy.
 * @author Macario N. Peralta V
 * Date created: June 6 2022
 */	
package CapStone;

public class Vehicle {
	private String carType;
	private String price;
	private String color;
	private String fuelType;
	private String carModel;
	private String carMake;
	private String year;
	private String age;
	private String dateLic;
	private double premium;
	
	RatingEngine rEng = new RatingEngine();
	
	public Vehicle(String carMake, String carModel, String carYear, String carType, String carFuelT, String carColor, String price, String dateLic) {
		this.carMake = carMake;
		this.carModel = carModel;
		this.year = carYear;
		this.carType = carType;
		this.fuelType = carFuelT;
		this.color = carColor;
		this.price = price;
		this.dateLic = dateLic;
		sendDataToREng();
		setPremium();
	}
	
	public void seeDetails() {
		System.out.println("======================Vehicle=========================");
		System.out.println("Car: " + year + " " + carMake + " " + carModel);
		System.out.println("Car type: " + carType);
		System.out.println("Fuel Type: " + fuelType);
		System.out.println("Color: " + color);
		System.out.println("Price (New): " + price);
		System.out.println("Premium: " + premium);
		System.out.println("======================================================");
	}
	
	public void sendDataToREng() {
		rEng.setVPrice(price);
		age = rEng.setCarAge(year);
		rEng.setVPF();
		rEng.setdLX(dateLic);
	}	
	public void setPremium() {
		premium = rEng.calcP();
	}
	
	public void setType(String carType) {
		this.carType = carType;
	}
	
	public void setPrice(String Price) {
		this.price = Price;
	}
	
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public void setModel(String carModel) {
		this.carModel = carModel;
	}
	
	public void setMake(String carMake) {
		this.carMake = carMake;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public String getYear() {
		return year;
	}
	
	public String getMake() {
		return carMake;
	}
	
	public String getModel() {
		return carModel;
	}

	public String getFuelType() {
		return fuelType;
	}
	
	public String getColor() {
		return color;
	}
	public double getPremium() {
		return premium;
	}

}
