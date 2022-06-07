import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Claim {

	private final String claimNumber;
	private LocalDate accidentDate;
	private String accidentAddress;
	private String accidentDescription;
	private String accidentDamage;
	private double repairCosts;
	
	public Claim(int claimNumber) {
		this.claimNumber = String.format("C%06d", claimNumber);
	}
	
	public void setAccidentDate(LocalDate accidentDate) {
		this.accidentDate = accidentDate;
	}
	
	public void setAccidentAddress(String accidentAddress) {
		this.accidentAddress = accidentAddress;
	}
	
	public void setAccidentDescription(String accidentDescription) {
		this.accidentDescription = accidentDescription;
	}
	
	public void setAccidentDamage(String accidentDamage) {
		this.accidentDamage = accidentDamage;
	}
	
	public void setRepairCosts(double repairCosts) {
		this.repairCosts = repairCosts;
	}
	
	public String getClaimNumber() {
		return claimNumber;
	}
	
	public int getIntId() {
		return Integer.parseInt(claimNumber.substring(1));
	}
	
	public static int generateUniqueId(List<Claim> claimList) {
		int limit = 999999;
		ArrayList<Integer> idList = new ArrayList<Integer>();
		
		for (Claim i: claimList) {
			idList.add(Integer.valueOf(i.getIntId()));
		}
		
		
		for (int i = 0; i <= limit; i++) {
			if (!(idList.contains(Integer.valueOf(i)))) {
				return i;
			}
		}
		
		return -1;
	}
	
	public static void printClaimHeader() {
		System.out.printf("\n%-20s\t%-20s\t%-20s\t%-20s\t%-20s\t%-20s\n", "Claim Number", "Accident Date", "Accident Address", "Accident Description", "Damage Description", "Repair Costs");
	}
	
	public void printClaimDetails() {
		System.out.printf("%-20s\t%-20s\t%-20s\t%-20s\t%-20s\t%-20.2f\n", this.claimNumber, this.accidentDate, this.accidentAddress, this.accidentDescription, this.accidentDamage, this.repairCosts);
	}
	
}
