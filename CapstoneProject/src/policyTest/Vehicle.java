package policyTest;

public class Vehicle {

	private String make;
	private String model;
	private int year;
	private int type;
	private int fuel;
	private int yearLicense;
	private double price;
	private String color;
	private double premium;
	
	public Vehicle(String make, String model, int year, int type, int fuel, double price, String color, int yearLicense) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.type = type;
		this.fuel = fuel;
		this.price = price;
		this.color = color;
		this.yearLicense = yearLicense;
	}
	
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getFuel() {
		return fuel;
	}
	public void setFuel(int fuel) {
		this.fuel = fuel;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPremium() {
		this.premium = RatingEngine.rate(year, price, yearLicense);
		return premium;
	}
	public void setPremium(double premium) {
		this.premium = premium;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
}
