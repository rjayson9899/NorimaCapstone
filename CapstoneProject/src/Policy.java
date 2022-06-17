package CapStone;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;  
/*
 * This is the policy object that hold the data for the policies
 * of each customer account.
 * @author Macario N. Peralta V
 * Date created: June 6 2022
 */
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
	private double policyPremium;
	LocalDate stDate;
	LocalDate eDate;
	LocalDate dateNow = LocalDate.now();
	private boolean isCancelled = false;
	private boolean isExpired = false;
	private boolean isEffDate = false;
	
	ArrayList<Vehicle> car = new ArrayList<>();
	PolicyHolder polyHol;
	RatingEngine rEng = new RatingEngine();	
	Scanner userIn = new Scanner(System.in);
	
	public Policy(String polNum) {
		this.polNum = polNum;
	}
	 
	//this method verifies the starting effective date inputted by the user.
	 public boolean verifyEffDate(String srtDate) {
		 try {
			 String dateFormat = "LLL dd uuuu";
			 effDate = srtDate.substring(0, 1).toUpperCase() + srtDate.substring(1);
			 stDate = LocalDate.parse(effDate, DateTimeFormatter.ofPattern(dateFormat, Locale.US).withResolverStyle(ResolverStyle.STRICT));
		
			 if (stDate.isBefore(dateNow)) {
				 System.out.println("The effective date should be starting today. ");
				 isEffDate =  true;
				 return isEffDate;
			 }
			 else {
				eDate = stDate.plusMonths(6);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLL dd uuuu");
				expDate = eDate.format(formatter);
				System.out.println("Expiration date: " + expDate);
				isEffDate = false;
				return isEffDate;
			 }
		 } 
		 catch(DateTimeParseException | StringIndexOutOfBoundsException | IllegalArgumentException  e) {
				System.out.println("Please enter a valid input.");
				isEffDate = true;
				return isEffDate;
			}
	}
	 
	public String getStDate() {
		return effDate;
	}
	
	public String getExpDate() {
		return expDate;
	}
	
	public String getPolNum() {
		return polNum;
	}
	
	//this method creates a policy holder object within the policy class.
	public void setPolH(String fName, String lName, String bDay, String address, String dL, String dateLic) {
		this.fName = fName;
		this.lName = lName;
		this.bDay = bDay;
		this.address = address;
		this.dLicense = dL;
		this.dateLic = dateLic;
		this.polyHol = new PolicyHolder(fName, lName, bDay, address, dL, dateLic);
	}
	
	//this method creates a vehicle object within the policy class.
	public void setCar(String carMake, String carModel, int carYear, String carType, String carFuelT, String carColor, double price) {
		car.add(new Vehicle(carMake, carModel, carYear, carType, carFuelT, carColor, price, dateLic));
	}
	
	//this method checks for the current status of the policy.
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
			isExpired = true;
			return isExpired;
		}
		else if (dateNow.isBefore(stDate) == true) {
			isCancelled = true;
			status = "Scheduled";
			return isCancelled;
		}
		else {
			return isCancelled;
		}
	}
	
	//this method sets the status of the policy to cancelled.
	public void cancelPol() {
		isCancelled = true;
		eDate = LocalDate.now();
		eDate = eDate.minusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLL dd uuuu");
		expDate = eDate.format(formatter);
		System.out.println("Policy is cancelled. ");
	}
	
	//this method shows all of the details that are in this class.
	public void seeDetails() {		
		System.out.println("========================Policy==============================");
		System.out.println("Policy number: " + polNum);
		System.out.println("Status: " + status);
		System.out.println("Effective date: " + effDate);
		System.out.println("Expiration date: " + expDate);
		System.out.println("Times claimed: " + claimCounter);
		System.out.println("Policy Premium: $" + policyPremium);
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
	
	//this method shows the total premium of the specific policy.
	public double getTotalPremium() {
		for(Vehicle carz : car) {
			policyPremium += carz.getPremium();
		}
		return policyPremium;
	}

}
