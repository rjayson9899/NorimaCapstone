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
	
	public void setPolicyHolder(PolicyHolder policyHolderObj) {
		this.policyHolder = policyHolderObj;
	}
	
	public void addVehicle(Vehicle vehicleObj) {
		vehicleList.add(vehicleObj);
		//System.out.println(vehicleObj.toString()); Debug
	}
	
	public void createPolicyQuote() {
		double premium;
		this.totalPremium = 0;
		System.out.println("Create a Policy Quote\n");
		
		for (Vehicle vehicleObj : vehicleList) {
			premium = RatingEngine.computePremium(vehicleObj.getVehiclePrice(), policyHolder.getLicensedYear(), vehicleObj.getVehicleYear());
			vehicleObj.setVehiclePremium(premium);
			this.totalPremium += premium;
			
			System.out.printf("%-20s \t%-20s \t%-20s\n", "Vehicle Make", "Vehicle Model", "Premium");
			System.out.printf("%-20s \t%-20s %.2f\n", vehicleObj.getVehicleMake(), vehicleObj.getVehicleModel(), premium);
		}
		
		System.out.println();
		System.out.printf("Total Vehicle Premium: %.2f", totalPremium);
		
		
	}
	
	
	
	
	


}
