/**
 * Java Course 4 Module 3, Norima Java Developer Capstone Project
 * Policy Class File
 *@author Edmark
 *@Description: This capstone project is a simple Automobile Insurance Policy and Claims Administration System (PAS) 
 *				that manages customer automobile insurance policies and accident claims for an insurance company. 
 *				The program was made by using Object Oriented Programming Principles.
 *Created date: June 6, 2022
 *Modified date: June 14, 2022
 *@Modified by:
 *
 */
import java.time.LocalDate;
import java.util.ArrayList;

public class Policy {
	private int policyNumber;
	private LocalDate effectiveDatePolicy;
	private LocalDate expirationDatePolicy;
	public static final int POLICY_MAX = 999999;
	private PolicyHolder policyHolder;
	private ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
	private double totalPremium;
	
	public Policy(int policyNumber) {
		this.policyNumber = policyNumber;
	}
	
	public LocalDate getEffectiveDatePolicy() {
		return effectiveDatePolicy;
	}

	public LocalDate getExpirationDatePolicy() {
		return expirationDatePolicy;
	}

	public int getPolicyNumber() {
		return policyNumber;
	}
	
	public String getName() {
		String firstName;
		String lastName;
		String policyHolderName;
		
		firstName = policyHolder.getFirstName();
		lastName = policyHolder.getLastName();
		policyHolderName = firstName + " " + lastName;
		
		return policyHolderName;
	}
	
	public PolicyHolder getPolicyHolder() {
		return policyHolder;
	}
	
	public double getTotalPremium() {
		return totalPremium;
	}
	
	public void setPolicyHolder(PolicyHolder policyHolderObj) {
		this.policyHolder = policyHolderObj;
	}
	
	public void setEffectiveDatePolicy(LocalDate effectiveDatePolicy) {
		this.effectiveDatePolicy = effectiveDatePolicy;
		this.expirationDatePolicy = effectiveDatePolicy.plusMonths(6);
	}
	
	public void addVehicle(Vehicle vehicleObj) {
		vehicleList.add(vehicleObj);
	}
	
	public void createPolicyQuote() {
		double premium;
		this.totalPremium = 0;
		System.out.println("=========================");
		System.out.println("  Create a Policy Quote  ");
		System.out.println("=========================\n");
		System.out.printf("%-20s \t%-20s \t%-20s\n", "Vehicle Make", "Vehicle Model", "Premium");
		for (Vehicle vehicleObj : vehicleList) {
			premium = RatingEngine.computePremium(vehicleObj.getVehiclePrice(), policyHolder.getLicensedYear(), vehicleObj.getVehicleYear());
			vehicleObj.setVehiclePremium(premium);
			this.totalPremium += premium;
			System.out.printf("%-20s \t%-20s \t%.2f\n", vehicleObj.getVehicleMake(), vehicleObj.getVehicleModel(), premium);
		}
		System.out.println();
		System.out.println("============================================================");
		System.out.printf("Total Vehicle Premium: %.2f\n", totalPremium);
	}

	public void cancelPolicy() { //Changes the expiration date to a year before
		if (!(isCancelled())) {
			this.expirationDatePolicy = expirationDatePolicy.minusYears(1);
		}
	}
	
	public boolean isCancelled() {
		LocalDate dateToday;
		
		dateToday = LocalDate.now();
		if (this.expirationDatePolicy.compareTo(dateToday) < 0 || this.effectiveDatePolicy.compareTo(dateToday) > 0)  {
			return true;
		} else {
			return false;
		}
	}

}
