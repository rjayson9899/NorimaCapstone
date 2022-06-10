/*
 * This is the policy object that hold the data for the policies
 * of each customer account.
 * @author Macario N. Peralta V
 * Date created: June 6 2022
 */
package CapStone;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;  

public class Policy {
	
	private int claimCounter = 0;
	private String polNum;
	private String fName;
	private String lName;
	private String bDay;
	private String address;
	private String dLicense;
	private String dateLic;
	private String effDate;
	private String expDate;
	private String status;
	private int carPrice;
	private double policyPremium;
	LocalDate stDate;
	LocalDate eDate;
	LocalDate dateNow = LocalDate.now();
	private boolean isCancelled = false;
	private boolean isEffDate = false;
	
	ArrayList<Vehicle> car = new ArrayList<>();
	PolicyHolder polyHol;
	RatingEngine rEng = new RatingEngine();	
	Scanner userIn = new Scanner(System.in);
	
	public Policy(String polNum) {
		this.polNum = polNum;
	}
	
	 public Policy(String fName, String lName, String bDay, String address, String dL, String dateLic) {
		this.fName = fName;
		this.lName = lName;
		this.bDay = bDay;
		this.address = address;
		this.dLicense = dL;
	} 
	 
	 public boolean verifyEffDate(String srtDate) {
		 effDate = srtDate.substring(0, 1).toUpperCase() + srtDate.substring(1);
		 stDate = LocalDate.parse(effDate, DateTimeFormatter.ofPattern("LLL dd yyyy"));

		 if (stDate.isBefore(dateNow)) {
			 System.out.println("The effective date should be starting today. ");
			 isEffDate =  true;
			 return isEffDate;
		 }
		 else {
			eDate = stDate.plusMonths(6);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLL dd yyyy");
			expDate = eDate.format(formatter);
			System.out.println("Expiration date: " + expDate);
			isEffDate = false;
			return isEffDate;
		 }
	}
	 
	public String getStDate() {
		return effDate;
	}
	
	public String getExpDate() {
		return expDate;
	}
	
	public int getPolNum() {
		int polN = Integer.parseInt(polNum);
		return polN;
	}
	
	public void setPolH(String fName, String lName, String bDay, String address, String dL, String dateLic) {
		this.fName = fName;
		this.lName = lName;
		this.bDay = bDay;
		this.address = address;
		this.dLicense = dL;
		this.dateLic = dateLic;
		this.polyHol = new PolicyHolder(fName, lName, bDay, address, dL, dateLic);
	}
	
	public void setCar(String carMake, String carModel, String carYear, String carType, String carFuelT, String carColor, String price) {
		car.add(new Vehicle(carMake, carModel, carYear, carType, carFuelT, carColor, price, dateLic));
	}
	
	public boolean checkStatus() {
		if (isCancelled == false && dateNow.isAfter(eDate) == false && dateNow.isBefore(stDate) == false) {
			status = "Active";
			return isCancelled;
		}
		else if (isCancelled == true) {	
			status = "Cancelled";
			return isCancelled;
		}
		else if (dateNow.isAfter(eDate) == true) {
			status = "Expired";
			return isCancelled;
		}
		else if (dateNow.isBefore(stDate) == true) {
			status = "Scheduled";
			return isCancelled;
		}
		else {
			return isCancelled;
		}
	}
	
	public void cancelPol() {
		isCancelled = true;
		eDate = LocalDate.now();
		eDate = eDate.minusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLL dd yyyy");
		expDate = eDate.format(formatter);
		System.out.println("Policy is cancelled. ");
	}
	
	public void seeDetails() {		
		System.out.println("========================Policy==============================");
		System.out.println("Policy number: " + polNum);
		System.out.println("Status: " + status);
		System.out.println("Effective date: " + effDate);
		System.out.println("Expiration date: " + expDate);
		System.out.println("Times claimed: " + claimCounter);
		System.out.println("Policy Premium: ");
		System.out.println("======================Policy holder=========================");
		System.out.println("Full name: " + fName + " " + lName);
		System.out.println("Birthday: " + bDay);
		System.out.println("Address: " + address);
		System.out.println("Driver's license number: " + dLicense);
		System.out.println("Issue date of driver's license: " + dateLic);
		for(Vehicle carz : car) {
			carz.seeDetails();
		}
	}
	
	public void setClaimCounter(int clCount) {
		this.claimCounter = clCount;
	}
	
	public double getTotalPremium() {
		for(Vehicle carz : car) {
			policyPremium += carz.getPremium();
		}
		return policyPremium;
	}

}
