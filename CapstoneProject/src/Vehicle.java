/*
 * This is the vehicle object that holds the data of the 
 * vehicles covered by the policy.
 * @author Macario N. Peralta V
 * Date created: June 6 2022
 */	
package CapStone;

public class Vehicle {
	private String carType;
	private double price;
	private String color;
	private String fuelType;
	private String carModel;
	private String carMake;
	private int year;
	private int age;
	private String dateLic;
	private double premium;
	
	RatingEngine rEng = new RatingEngine();
	
	public Vehicle(String carMake, String carModel, int carYear, String carType, String carFuelT, String carColor, double price, String dateLic) {
		
		this.carMake = carMake.substring(0, 1).toUpperCase() + carMake.substring(1);
		this.carModel = carModel.substring(0, 1).toUpperCase() + carModel.substring(1);
		this.year = carYear;
		this.carType = carType.substring(0, 1).toUpperCase() + carType.substring(1);
		this.fuelType = carFuelT.substring(0, 1).toUpperCase() + carFuelT.substring(1);
		this.color = carColor.substring(0, 1).toUpperCase() + carColor.substring(1);
		this.price = price;
		this.dateLic = dateLic.substring(0, 1).toUpperCase() + dateLic.substring(1);
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
		System.out.println("Car age: " + age);
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
	
	public double getPremium() {
		return premium;
	}

}
