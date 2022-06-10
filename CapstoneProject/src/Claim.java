public class Claim {
	
	private int claimNumber;
	public static final int CLAIM_MAX = 999999;
	private String dateOfAccident;
	private String addressOfAccident;
	private String descriptionOfAccident;
	private String descriptionOfDamage;
	private double damageRepairCost;
	
	
	public Claim(int claimNumber, String dateOfAccident, String addressOfAccident, String descriptionOfAccident,
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
	public String getDateOfAccident() {
		return dateOfAccident;
	}
	public void setDateOfAccident(String dateOfAccident) {
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
		
		System.out.printf("%-20s \t%-20s \t%-20s \t%-20s \t%-20s \t%-20s\n", claimNumber, this.dateOfAccident, this.addressOfAccident,
				this.descriptionOfAccident, this.descriptionOfDamage, this.damageRepairCost);
	}
	
}
