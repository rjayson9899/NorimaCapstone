import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Policy {
	
	private final int policyNumber;
	private LocalDate effectiveDate;
	private LocalDate expirationDate;
	private PolicyHolder holder;
	private ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
	private double premium;
	
	public Policy(int policyNumber) {
		this.policyNumber = policyNumber;
	}
	
	public int getPolicyNumber() {
		return policyNumber;
	}
	
	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}
	
	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	
	public PolicyHolder getHolder() {
		return holder;
	}
	
	public double getPremium() {
		return this.premium;
	}
	
	public String getHolderName() {
		String name = holder.getFirstName() + " " + holder.getLastName();
		return name;
	}
	
	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
		this.expirationDate = effectiveDate.plusMonths(6);
	}
	
	public void setHolder(CustomerAccount acctObj, LocalDate birthDate, String driverLicenseNumber, LocalDate licenseDate) {
		this.holder = new PolicyHolder(acctObj.getFirstName(), acctObj.getLastName(), birthDate, driverLicenseNumber, licenseDate);
	}
	
	public void setHolder(String firstName, String lastName, LocalDate birthDate, String driverLicenseNumber, LocalDate licenseDate) {
		this.holder = new PolicyHolder(firstName, lastName, birthDate, driverLicenseNumber, licenseDate);
	}
	
	public void addVehicle(Vehicle vhcObj) {
		vehicleList.add(vhcObj);
	}
	
	public void generateQuote() {
		double singlePremium;
		this.premium = 0;
		for (Vehicle vhcObj: vehicleList) {
			singlePremium = RatingEngine.ratePremium(holder, vhcObj);
			vhcObj.setPremium(singlePremium);
			this.premium += singlePremium;
			System.out.printf("%-30s: %.2f\n", (vhcObj.getMake() + " " + vhcObj.getModel()), singlePremium);
		}
		System.out.println("-------------------------------------------------------------------------------");
		System.out.printf("%-30s: %.2f\n", "TOTAL", this.premium);
	}
	
	public void cancelPolicy() {
		LocalDate now = LocalDate.now();
		expirationDate = now.minusDays(1);
	}
	
	public static int generateUniqueId(List<CustomerAccount> customerList) {
		int limit = 999999;
		ArrayList<Integer> idList = new ArrayList<Integer>();
		
		for (CustomerAccount custObj: customerList) {
			custObj.addPolicyIds(idList);
		}
		
		for (int i = 0; i <= limit; i++) {
			if (!(idList.contains(Integer.valueOf(i)))) {
				return i;
			}
		}
		
		return -1;
	}
	
}
