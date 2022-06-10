import java.time.LocalDate;

/**
 * Norima Capstone Project, Rating Engine Class File.
 * 
 * For the Norima Capstone project, the student is assigned to create a console-based
 * Policy and Claims Administration System based on the specifications provided in the 
 * Robertson Brightspace Java 102 Part 2 course.
 * 
 * This class file contains the algorithm for computing premiums.
 * 
 * @author Roger Jayson M. Mendez III
 */

public class RatingEngine {
	
	/**
	 * Computes for the premium of a vehicle using provided param values.
	 * 
	 * Computation of premium follows this formula:
	 * 		P = (vp * vpf) + ((vp/100)/dlx)
	 * Where:
	 * 		P 	= computed premium
	 * 		vp	= vehicle purchase price
	 * 		vpf	= vehicle price factor
	 * 		dlx	= number of years license was first issued
	 * 
	 * The value of vp is based on the value found in the vehiclePurchasePrice param
	 * 
	 * The value of dlx is computed by subtracting the current year to the licenseYear param.
	 * If the value of dlx will result in 0 or lower, dlx is automatically set to 1.
	 * 
	 * The value of vpf depends on the difference between the current year and the vehicleYear
	 * param. Depending on the amount, a set value is used. Below is the table on what vpf to use:
	 * 
	 * 		Vehicle Age (Less than)	|	vpf
	 * 		==================================
	 * 		 1						|	1.00%
	 * 		 3						|	0.80%
	 * 		 5						|	0.70%
	 * 		10						|	0.60%
	 * 		15						|	0.40%
	 * 		20						|	0.20%
	 * 		40						|	0.10%
	 * 		> 40					|	0.05%
	 * 
	 * @param licenseYear - year license was issued
	 * @param vehicleYear - year of vehicle
	 * @param vehiclePurchasePrice - price vehicle was purchased
	 * @return double - computed premium
	 */
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
