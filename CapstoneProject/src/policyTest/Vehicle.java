package policyTest;

public class Vehicle implements InterfaceApp{

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

	@Override
	public void getDetails() {
		System.out.println("-------------------------------");
		System.out.println("    Details of your Vehicle    ");
		System.out.println("-------------------------------");
		System.out.println("Make: " + getMake());
		System.out.println("Model: " + getModel());
		System.out.println("Year: " + getYear());
		
		if(getType() == 1) {
			System.out.println("Type of vehicle: 4-door sedan");
		}
		
		else if(getType() == 2) {
			System.out.println("Type of vehicle: 2-door sports car, SUV, or truck");
		}
		
		if(getFuel() == 1) {
			System.out.println("Type of fuel: Diesel");
		}
		
		else if(getFuel() == 2) {
			System.out.println("Type of fuel: Electronic");
		}
		
		else if (getFuel() == 3) {
			System.out.println("Type of fuel: Petrol");
		}
		
		System.out.println("Purchase price: " + price);
		System.out.println("Color: " + color);
		System.out.println("-------------------------------\n");
		
	}
	
	
}
