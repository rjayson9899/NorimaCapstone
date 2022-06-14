package policyTest;

import java.util.Calendar;
import java.text.NumberFormat;
import java.util.Locale;

public class RatingEngine {
		static Locale locale = new Locale("en", "US");      
		static NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
	public static double rate(int yearCar, double vp, int yearLicense) {
		
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int ageCar = currentYear - yearCar;
		int ageLicense = currentYear - yearLicense;
		if(ageLicense <= 0) {
			ageLicense = 1;
		}
		double vpf , premium;

		if(ageCar < 1) {
			vpf = 0.01;
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
		
		System.out.println("Premium: " + currencyFormatter.format((vpf * vp)));
		System.out.println("Additional expense: "  + currencyFormatter.format(((vp/100)/ageLicense)));
		System.out.println("-------------------------------");
		System.out.println("Total: "  + currencyFormatter.format(premium));
		System.out.println("-------------------------------");

		return premium;
	}

	public static double rateCalOnly(int yearCar, double vp, int yearLicense) {
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
		

		return premium;
		
	}
}
