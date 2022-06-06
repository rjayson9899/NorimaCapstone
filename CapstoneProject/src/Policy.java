import java.util.ArrayList;
import java.time.LocalDate;

public class Policy {
	
	private int policyNumber;
	private LocalDate effectiveDate;
	private LocalDate expirationDate;
	private PolicyHolder holder;
	private ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
	private double premium;

}
