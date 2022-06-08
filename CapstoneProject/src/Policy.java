package CapStone;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;  

public class Policy {
	
	private int polNum = 000000;
	private String fName;
	private String lName;
	private String bDay;
	private String address;
	private String dLicense;
	private String srtDate;
	private String expDate;
	
	ArrayList<Vehicle> car = new ArrayList<>();
	PolicyHolder polyHol;
	Scanner userIn = new Scanner(System.in);
	
	public Policy() {

	}
	
	 public Policy(String fName, String lName, String bDay, String address, String dL, String dateLic) {
		this.fName = fName;
		this.lName = lName;
		this.bDay = bDay;
		this.address = address;
		this.dLicense = dL;
	} 
	
	public void setExpDate(String srtDate) {
		String cSDate = srtDate.substring(0, 1).toUpperCase() + srtDate.substring(1);
		LocalDate date = LocalDate.parse(cSDate, DateTimeFormatter.ofPattern("LLL dd yyyy"));
		LocalDate eDate = date.plusMonths(6);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLL dd yyyy");
		String forExpDate = eDate.format(formatter);
		expDate = forExpDate;
		System.out.println("Expiration date: " + expDate);
	}
	
	public void buyPolicy() {
		
	}
	
	public void getStDate() {
		
	}
	
	public void getExpDate() {
		
	}
	
	public void setPolH(String fName, String lName, String bDay, String address, String dL, String dateLic) {
		this.polyHol = new PolicyHolder(fName, lName, bDay, address, dL, dateLic);
	}
	
	public void setCar(String fName, String lName, String bDay, String address, String dL, String dateLic) {
		car.add(new Vehicle(fName, lName, bDay, address, dL, dateLic));
	}


}
