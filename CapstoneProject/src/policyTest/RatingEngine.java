package policyTest;

import java.util.Calendar;

public class RatingEngine {
	
	public static double rate(int yearCar, double vp, int yearLicense) {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int ageCar = currentYear - yearCar;
		int ageLicense = currentYear - yearLicense;
		if(ageLicense <= 0) {
			ageLicense = 1;
		}
		double vpf , premium;

		if(ageCar < 1) {
			vpf = 0.001;
		}
		else if(ageCar < 3) {
			vpf = 0.008;
		}
		else if(ageCar < 5) {
			vpf = 0.007;
		}
		else if(ageCar < 10) {
			vpf = 0.006;
		}
		else if(ageCar < 15) {
			vpf = 0.004;
		}
		else if(ageCar < 20) {
			vpf = 0.002;
		}
		else if(ageCar < 40) {
			vpf = 0.001;
		}
		
		else {
			vpf = 0.001;
		}
		
		premium = (vpf * vp) + ((vp/100)/ageLicense);
		
		System.out.println("Premium: " + "$" + (vpf * vp));
		System.out.println("Additional expense: " +"$"  + ((vp/100)/ageLicense));
		System.out.println("-------------------------------");
		System.out.println("Total Premium for this vehicle: " + "$"  + premium);
		System.out.println("-------------------------------");

		return premium;
		
		
		
	}
}
