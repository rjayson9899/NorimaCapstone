package policyTest;

import java.time.LocalDate;


public class Claim implements InterfaceApp{

	private int claimNum;
	private LocalDate accidentDate;
	private String address;
	private String descriptionAcc;
	private String descriptionDam;
	private double cost;
	
	public Claim(int claimNum, LocalDate accidentDate, String address, String descriptionAcc,
					String descriptionDam, double cost) {
		this.claimNum = claimNum + 1;
		this.accidentDate = accidentDate;
		this.address = address;
		this.descriptionAcc = descriptionAcc;
		this.descriptionDam = descriptionDam;
		this.cost = cost;
	}
	
	public int getClaimNum() {
		return claimNum;
	}
	public void setClaimNum(int claimNum) {
		this.claimNum = claimNum;
	}
	public LocalDate getAccidentDate() {
		return accidentDate;
	}
	public void setAccidentDate(LocalDate accidentDate) {
		this.accidentDate = accidentDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescriptionAcc() {
		return descriptionAcc;
	}
	public void setDescriptionAcc(String descriptionAcc) {
		this.descriptionAcc = descriptionAcc;
	}
	public String getDescriptionDam() {
		return descriptionDam;
	}
	public void setDescriptionDam(String descriptionDam) {
		this.descriptionDam = descriptionDam;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public void getDetails() {
		System.out.println("-------------------------------");
		System.out.println("    Details of your Claim      ");
		System.out.println("-------------------------------");
		System.out.println("Claim number: " + claimNum);
		System.out.println("Date of accident: " + accidentDate);
		System.out.println("address: " + address);
		System.out.println("description of accident: \n" + descriptionAcc);
		System.out.println("description of damage: \n" + descriptionDam);
		System.out.println("Estimated cost of damage: $" + cost);
	}
	
	
	
}
