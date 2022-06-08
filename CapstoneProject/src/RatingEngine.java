import java.time.LocalDate;

public class RatingEngine {
	
	public static double ratePremium(int licenseYear, int vehicleYear, double vehiclePurchasePrice) {
		double premium;
		double vp = vehiclePurchasePrice;
		double vpf;
		double dlx;
		int yearNow = LocalDate.now().getYear();
		int yearDif = yearNow - vehicleYear;
		
		if (yearDif < 1) {
			vpf = 0.01;
		} 
		else if (yearDif < 3) {
			vpf = 0.008;
		}
		else if (yearDif < 5) {
			vpf = 0.007;
		}
		else if (yearDif < 10) {
			vpf = 0.006;
		}
		else if (yearDif < 15) {
			vpf = 0.004;
		}
		else if (yearDif < 20) {
			vpf = 0.002;
		}
		else if (yearDif < 40) {
			vpf = 0.001;
		}
		else {
			vpf = 0.0005;
		}
		
		dlx = yearNow - licenseYear;
		if (dlx < 1) {
			dlx = 1;
		}
		
		premium = (vp * vpf);
		premium += (vp / 100) / dlx;
		
		return premium;
	}
	
}
