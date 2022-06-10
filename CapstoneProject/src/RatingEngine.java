/*
 * 
 */
package CapStone;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RatingEngine {

	private double premium;
	private double vP;
	private double vPF;
	private int dLX;
	private String age;
	private String driverDate;
	private int carAge;
	private int carYear;
	private int dDate;
	LocalDate date = LocalDate.now();
	LocalDate carA;
	LocalDate dLic;
	public RatingEngine() {
		
	}
	
	public void setVPrice(String VPrice) {
		this.vP = Double.parseDouble(VPrice);
	}
	
	public int setCarAge(String age) {
		this.carYear = Integer.parseInt(age);
		carA = date.minusYears(carYear);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
		this.age = carA.format(formatter);
		carAge = Integer.parseInt(this.age);
		return this.carAge;
	}
	public void setVPF() {
		
		if(carAge < 1) {
			vPF = 1;
		}
		else if (carAge >= 1 && carAge < 3) {
			vPF = 0.8;
		}
		else if (carAge >= 3 && carAge < 5) {
			vPF = 0.7;
		}
		else if (carAge >= 5 && carAge < 10) {
			vPF = 0.6;
		}
		else if (carAge >= 10 && carAge < 15) {
			vPF = 0.4;
		}
		else if (carAge >= 15 && carAge < 20) {
			vPF = 0.2;
		}
		else if (carAge >= 20 && carAge < 40) {
			vPF = 0.1;
		}
		
	}
	
	public void setdLX(String dateLic) {
		driverDate = dateLic.substring(dateLic.lastIndexOf(" ") + 1);
		this.dDate = Integer.parseInt(driverDate);
		dLic = date.minusYears(dDate);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
		driverDate = dLic.format(formatter);
		dLX = Integer.parseInt(driverDate);
	}
	
	public double calcP() {
		premium = (vP * vPF) + ((vP/100)/dLX);
		return premium;
	}
	
	
}
