package CapStone;

public class Vehicle {
	private static int ID;
	private String carType;
	private String price;
	private double premChar;
	private String Color;
	private String fuelType;
	private String carModel;
	private String carMake;
	private String year;
	
	public Vehicle() {
		
	}
	
	public void setCar(String carMake, String carModel) {
		
	}
	
	public static void setID() {
		
	}
	
	public void setType(String carType) {
		this.carType = carType;
	}
	
	public void setPrice(String Price) {
		this.price = Price;
	}
	public void setpremChar() {
		
	}
	
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	
	public void setColor(String color) {
		this.Color = color;
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
		return Color;
	}

}
