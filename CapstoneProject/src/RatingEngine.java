/**
 * Java Course 4 Module 3, Norima Java Developer Capstone Project
 * Rating Engine Class File
 *@author Edmark
 *@Description: This capstone project is a simple Automobile Insurance Policy and Claims Administration System (PAS) 
 *				that manages customer automobile insurance policies and accident claims for an insurance company. 
 *				The program was made by using Object Oriented Programming Principles.
 *Created date: June 6, 2022
 *Modified date: June 14, 2022
 *@Modified by:
 *
 */
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
