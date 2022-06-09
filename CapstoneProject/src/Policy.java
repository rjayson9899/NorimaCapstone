import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.text.NumberFormat;
import java.time.LocalDate;

/**
 * Norima Capstone Project, Policy Class File.
 * 
 * For the Norima Capstone project, the student is assigned to create a console-based
 * Policy and Claims Administration System based on the specifications provided in the 
 * Robertson Brightspace Java 102 Part 2 course.
 * 
 * This class file contains details consisting a Policy. Methods are also available for
 * setting up Policy details and performing tasks related to a Policy.
 * 
 * @author Roger Jayson M. Mendez III
 */

public class Policy {
	
	private final int policyNumber;
	private LocalDate effectiveDate;
	private LocalDate expirationDate;
	private PolicyHolder holder;
	private ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
	private double premium;
	
	
	/**
	 * Constructor for instantiation of a Policy object.
	 * 
	 * @param policyNumber
	 */
	public Policy(int policyNumber) {
		this.policyNumber = policyNumber;
	}
	
	/**
	 * Returns the policy number of current instance.
	 * 
	 * @return int - policy number
	 */
	public int getPolicyNumber() {
		return policyNumber;
	}
	
	/**
	 * Returns the effective date of current instance
	 * 
	 * @return LocalDate - effective date of policy
	 */
	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}
	
	/**
	 * Returns expiration date of current instance
	 * 
	 * @return LocalDate - expiration date of policy
	 */
	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	
	/**
	 * Returns Policy Holder instance
	 * 
	 * @return PolicyHolder instance
	 */
	public PolicyHolder getHolder() {
		return holder;
	}
	
	/**
	 * Returns premium of current instance
	 * 
	 * @return double - policy premium
	 */
	public double getPremium() {
		return this.premium;
	}
	
	/**
	 * Returns the entire list of vehicles registered in current instance
	 * 
	 * @return ArrayList - list of vehicles in policy
	 */
	public ArrayList<Vehicle> getVehicleList() {
		return vehicleList;
	}
	
	/**
	 * Returns a string bearing the complete name of the policy holder\
	 * 
	 * @return String - complete policy holder name
	 */
	public String getHolderName() {
		String name = holder.getFirstName() + " " + holder.getLastName();
		return name;
	}
	
	/**
	 * Sets the effective date of current instance to the value of param.
	 * Expiration date is immediately set 6 months from effective date.
	 * 
	 * @param effectiveDate - date when policy will be effective
	 */
	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
		this.expirationDate = effectiveDate.plusMonths(6);
	}
	
	/**
	 * Sets policy holder instance in policy to param.
	 * 
	 * @param holderObj - Policy Holder instance
	 */
	public void setPolicyHolder(PolicyHolder holderObj) {
		this.holder = holderObj;
	}
	
	/**
	 * Adds Vehicle param instance to vehicle list
	 * 
	 * @param vhcObj - Vehicle instance
	 */
	public void addVehicle(Vehicle vhcObj) {
		vehicleList.add(vhcObj);
	}
	
	/**
	 * Generates a quote based on what vehicles are available in Policy.
	 * Requires the RatingEngine Class to run. Total premium of policy
	 * will also be set to current instance.
	 */
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
	
	/**
	 * Sets 
	 */
	public void cancelPolicy() {
		if (!(isExpired())) {
			expirationDate = effectiveDate.minusDays(1);
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
