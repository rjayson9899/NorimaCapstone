package CapStone;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;  

public class Policy extends CustomerAccount{
	
	private int polNum = 000000;
	private String srtDate;
	private String expDate;
	
	Vehicle car = new Vehicle();
	PolicyHolder polyHol = new PolicyHolder();
	Scanner userIn = new Scanner(System.in);
	
	public Policy() {
		
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
}
