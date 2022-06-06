
public class Vehicle {

	private String make;
	private String model;
	private int year;
	private String type;
	private String fuelType;
	private double purchasePrice;
	private double premium;
	
	public Vehicle(String make, String model, int year, String type, String fuelType, double purchasePrice) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.type = type;
		this.fuelType = fuelType;
		this.purchasePrice = purchasePrice;
	}
	
	public String getMake() {
		return make;
	}
	
	public String getModel() {
		return model;
	}
	
	public double getPurchasePrice() {
		return this.purchasePrice;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public double getPremium() {
		return this.premium;
	}
	
	public void setPremium(double premium) {
		this.premium = premium;
	}
	
}
