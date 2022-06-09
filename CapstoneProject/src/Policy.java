import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.text.NumberFormat;
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
	
	public ArrayList<Vehicle> getVehicleList() {
		return vehicleList;
	}
	
	public String getHolderName() {
		String name = holder.getFirstName() + " " + holder.getLastName();
		return name;
	}
	
	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
		this.expirationDate = effectiveDate.plusMonths(6);
	}
	
	public void setPolicyHolder(PolicyHolder holderObj) {
		this.holder = holderObj;
	}
	
	public void addVehicle(Vehicle vhcObj) {
		vehicleList.add(vhcObj);
	}
	
	public void generateQuote() {
		NumberFormat money = NumberFormat.getCurrencyInstance(Locale.US);
		double singlePremium;
		this.premium = 0;
		
		System.out.println("\nPOLICY QUOTE");
		for (Vehicle vhcObj: vehicleList) {
			singlePremium = RatingEngine.ratePremium(holder.getLicenseYear(), vhcObj.getYear(), vhcObj.getPurchasePrice());
			vhcObj.setPremium(singlePremium);
			this.premium += singlePremium;
			System.out.printf("%-30s: %10s\n", (vhcObj.getMake() + " " + vhcObj.getModel()), money.format(singlePremium));
		}
		System.out.println("-------------------------------------------------------------------------------");
		System.out.printf("%-30s: %10s\n", "TOTAL", money.format(this.premium));
	}
	
	public void cancelPolicy() {
		LocalDate now = LocalDate.now();
		
		if (!(isExpired())) {
			expirationDate = now.minusDays(1);
		}
	}
	
	public boolean isExpired() {
		LocalDate now = LocalDate.now();
		if (this.expirationDate.compareTo(now) < 0) {
			return true;
		}
		else {
			return false;
		}
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
