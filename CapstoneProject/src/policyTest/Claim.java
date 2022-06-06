package policyTest;

import java.util.Date;

public class Claim {

	private int claimNum;
	private Date accidentDate;
	private String address;
	private String descriptionAcc;
	private String descriptionDam;
	private double cost;
	
	public Claim() {
		
	}
	
	public int getClaimNum() {
		return claimNum;
	}
	public void setClaimNum(int claimNum) {
		this.claimNum = claimNum;
	}
	public Date getAccidentDate() {
		return accidentDate;
	}
	public void setAccidentDate(Date accidentDate) {
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
	
	
	
}
