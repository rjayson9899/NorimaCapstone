package policyTest;

import java.util.ArrayList;
import java.util.Date;

public class Policy {
	private int policyNum;
	private Date effectDate;
	private Date expDate;
	private PolicyHolder policyHolder;
	private ArrayList<Vehicle> vehicles;
	private double cost;
	private String relationship;
	
	
	public Policy(Date effectDate, PolicyHolder policyHolder) {
		this.effectDate = effectDate;
		this.policyHolder = policyHolder;
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
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	public void generateId(int count) {
		this.policyNum = count + 1;
	}
	
	
	
}
