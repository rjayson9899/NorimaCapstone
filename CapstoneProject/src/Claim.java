/**
 * Java Course 4 Module 3, Norima Java Developer Capstone Project
 * Claim Class File 
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

public class Claim {
	
	private int claimNumber;
	public static final int CLAIM_MAX = 999999;
	private LocalDate dateOfAccident;
	private String addressOfAccident;
	private String descriptionOfAccident;
	private String descriptionOfDamage;
	private double damageRepairCost;
	
	public Claim(int claimNumber, LocalDate dateOfAccident, String addressOfAccident, String descriptionOfAccident,
			String descriptionOfDamage, double damageRepairCost) {
		this.claimNumber = claimNumber;
		this.dateOfAccident = dateOfAccident;
		this.addressOfAccident = addressOfAccident;
		this.descriptionOfAccident = descriptionOfAccident;
		this.descriptionOfDamage = descriptionOfDamage;
		this.damageRepairCost = damageRepairCost;
	}
	
	public int getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(int claimNumber) {
		this.claimNumber = claimNumber;
	}
	public LocalDate getDateOfAccident() {
		return dateOfAccident;
	}
	public void setDateOfAccident(LocalDate dateOfAccident) {
		this.dateOfAccident = dateOfAccident;
	}
	public String getAddressOfAccident() {
		return addressOfAccident;
	}
	public void setAddressOfAccident(String addressOfAccident) {
		this.addressOfAccident = addressOfAccident;
	}
	public String getDescriptionOfAccident() {
		return descriptionOfAccident;
	}
	public void setDescriptionOfAccident(String descriptionOfAccident) {
		this.descriptionOfAccident = descriptionOfAccident;
	}
	public String getDescriptionOfDamage() {
		return descriptionOfDamage;
	}
	public void setDescriptionOfDamage(String descriptionOfDamage) {
		this.descriptionOfDamage = descriptionOfDamage;
	}
	public double getDamageRepairCost() {
		return damageRepairCost;
	}
	public void setDamageRepairCost(double damageRepairCost) {
		this.damageRepairCost = damageRepairCost;
	}
	
	public void displayClaimDetails() {
		String claimNumber = "C" + String.format("%06d", this.claimNumber);
		
		System.out.printf("%-20s \t%-20s \t%-20s \t%-20s \t%-20s \t%-20s\n", "Claim Number", "Accident Date",
				"Accident Location", "Accident Description", "Vehicle Damage", "Repair Cost");
		System.out.printf("%-20s \t%-20s \t%-20s \t%-20s \t%-20s \t%-20s\n", claimNumber, this.dateOfAccident, this.addressOfAccident,
				this.descriptionOfAccident, this.descriptionOfDamage, this.damageRepairCost);
	}
	
}
