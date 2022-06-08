import java.time.LocalDate;

public class RatingEngine {

	public static double computePremium(double vehiclePrice, int licensedYear, int vehicleYear) {
		double premium;
		double vp = vehiclePrice;
		double vpf;
		int presentYear = LocalDate.now().getYear();
		int dlx = presentYear - licensedYear;
		int diffYear = presentYear - vehicleYear;
		
		if (diffYear < 1) {
			vpf = 0.01;
		} else if (diffYear < 3) {
			vpf = 0.008;
		} else if (diffYear < 5) {
			vpf = 0.007;
		} else if (diffYear < 10) {
			vpf = 0.006;
		} else if (diffYear < 15) {
			vpf = 0.004;
		} else if (diffYear < 20) {
			vpf = 0.002;
		} else if (diffYear < 40) {
			vpf = 0.001;
		} else {
			vpf = 0.0001; //Assume VPF if vehicle age is greater than 40 years
		}
		
		if (dlx <= 0) { //dlx cannot be <= 0 to avoid computation error
			dlx = 1;
		}
		
		premium = (vp * vpf) + ((vp/100)/dlx);
		
		return premium;
	}
	

		
		
		
}
