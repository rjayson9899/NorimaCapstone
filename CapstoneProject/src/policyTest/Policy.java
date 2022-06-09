package policyTest;

import java.time.LocalDate;
import java.util.ArrayList;


public class Policy {
	private int policyNum;
	private LocalDate effectDate;
	private LocalDate expDate;
	private PolicyHolder policyHolder;
	private ArrayList<Vehicle> vehicles = new ArrayList<>();
	private double cost;
	private String status;
	
	
	public Policy(LocalDate effectDate, PolicyHolder policyHolder) {
		this.effectDate = effectDate;
		this.policyHolder = policyHolder;
	}
	
	public int getPolicyNum() {
		return policyNum;
	}
	public void setPolicyNum(int policyNum) {
		this.policyNum = policyNum;
	}
	public LocalDate getEffectDate() {
		return effectDate;
	}
	public void setEffectDate(LocalDate effectDate) {
		this.effectDate = effectDate;
	}
	public LocalDate getExpDate() {
		return expDate;
	}
	public void setExpDate(LocalDate expDate) {
		this.expDate = expDate;
	}
	
	public PolicyHolder getPolicyHolder() {
		return policyHolder;
	}

	public void setPolicyHolder(PolicyHolder policyHolder) {
		this.policyHolder = policyHolder;
	}

	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}
	public void addVehicles(Vehicle car) {
		vehicles.add(car);
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}

	
	public void generateId(int count) {
		this.policyNum = count + 1;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus() {
		LocalDate present = LocalDate.now(); 
		if(present.isAfter(effectDate) && present.isBefore(expDate)){
			this.status = "Enforced";
		}
		else if(present.isBefore(effectDate)){
			this.status = "Scheduled";
		}

		else if(present.isAfter(expDate)){
			this.status = "Expired";
		}
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void displayInfo(){
		System.out.println("-------------------------------");
		System.out.println("       Policy Details: ");
		System.out.println("-------------------------------");
		System.out.println("Policy Number: " + policyNum);
		System.out.println("Policy Holder: " + policyHolder.getFname() + " " 
							+ policyHolder.getLname());
		System.out.println("Status: " + status);
		System.out.println("Effective Date: " +effectDate);
		System.out.println("Expiration Date: " + expDate);
		System.out.println("List of Vehicles: ");
		for(Vehicle v: vehicles){
			System.out.println(v.getMake() + " " + v.getModel());
		}
		System.out.println("Total cost of premium: " + cost);
	}

	public static boolean checkDate(LocalDate inputDate){
		
		LocalDate date = LocalDate.now();
		
		if(inputDate.isAfter(date) || inputDate.equals(date)) {
			return true;
		}
		else if(inputDate.isBefore(date)) {
			System.out.println("Date already passed!");
			return false;
		}
		else{
			return false;
		}
	}
	
	
}
