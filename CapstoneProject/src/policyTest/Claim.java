package policyTest;

import java.time.LocalDate;


public class Claim{

	private String claimNum;
	private LocalDate accidentDate;
	private String address;
	private String descriptionAcc;
	private String descriptionDam;
	private double cost;
	
	public Claim( LocalDate accidentDate, String address, String descriptionAcc,
					String descriptionDam, double cost) {
		
		this.accidentDate = accidentDate;
		this.address = address;
		this.descriptionAcc = descriptionAcc;
		this.descriptionDam = descriptionDam;
		this.cost = cost;
	}
	
	public String getClaimNum() {
		return claimNum;
	}
	public void setClaimNum(String claimNum) {
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

	public void generateId(int count) {
		int accountNumInt = count + 1;
		claimNum = Integer.toString(accountNumInt);
		StringBuilder sb = new StringBuilder();
		sb.append('C');
		for(int x = 0; x < 5 - claimNum.length(); x++){
				sb.append('0');	
		}
		sb.append(claimNum);
		claimNum = sb.toString();
		
		//input code for id generation
	}

	public static boolean verifyDate(LocalDate effDate, LocalDate expDate){
		LocalDate dateNow = LocalDate.now();
		if(dateNow.isBefore(effDate) || dateNow.isAfter(expDate)){
			return false;
		}

		else{
			return true;
		}

	
	}
	
	
}
