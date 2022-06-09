package CapStone;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;  

public class Policy {
	
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
	LocalDate eDate;
	private boolean isCancelled = false;
	
	ArrayList<Vehicle> car = new ArrayList<>();
	PolicyHolder polyHol;
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
	
	public void setExpDate(String srtDate) {
		effDate = srtDate.substring(0, 1).toUpperCase() + srtDate.substring(1);
		LocalDate date = LocalDate.parse(effDate, DateTimeFormatter.ofPattern("LLL dd yyyy"));
		eDate = date.plusMonths(6);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLL dd yyyy");
		expDate = eDate.format(formatter);
		System.out.println("Expiration date: " + expDate);
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
	
	public void setCar(String fName, String lName, String bDay, String address, String dL, String dateLic) {
		car.add(new Vehicle(fName, lName, bDay, address, dL, dateLic));
	}
	
	public void checkStatus() {
		LocalDate date =LocalDate.now();
		if (isCancelled == false && date.isAfter(eDate) == false) {
			status = "Active";
		}
		else if (isCancelled == true) {	
			status = "Cancelled";
		}
		else if (date.isAfter(eDate) == true) {
			status = "Expired";
		}
	}
	
	public void cancelPol() {
		isCancelled = true;
		System.out.println("Policy is cancelled. ");
	}
	
	public void seeDetails() {		
		System.out.println("========================Policy==============================");
		System.out.println("Policy number: " + polNum);
		System.out.println("Status: " + status);
		System.out.println("Effective date: " + effDate);
		System.out.println("Expiration date: " + expDate);
		System.out.println("======================Policy holder=========================");
		System.out.println("Full name: " + fName + " " + lName);
		System.out.println("Birthday: " + bDay);
		System.out.println("Address: " + address);
		System.out.println("Driver's license number: " + dLicense);
		System.out.println("Issue date of driver's license: " + dateLic);
	}


}
