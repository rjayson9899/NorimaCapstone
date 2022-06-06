package policyTest;

import java.util.ArrayList;
import java.util.Date;

public class Policy {
	private int policyNum;
	private Date effectDate;
	private Date expDate;
	private ArrayList<PolicyHolder> policyHolders;
	private ArrayList<Vehicle> vehicles;
	private double cost;
	private String relationship;
	
	
	public Policy() {
		
	}
	
	public int getPolicyNum() {
		return policyNum;
	}
	public void setPolicyNum(int policyNum) {
		this.policyNum = policyNum;
	}
	public Date getEffectDate() {
		return effectDate;
	}
	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public ArrayList<PolicyHolder> getPolicyHolders() {
		return policyHolders;
	}
	public void setPolicyHolders(ArrayList<PolicyHolder> policyHolders) {
		this.policyHolders = policyHolders;
	}
	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	
	
}
