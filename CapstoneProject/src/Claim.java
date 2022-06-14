/*
 * This is the main driver of the whole capstone project.
 * This program is able to create a customer account and a
 * accompanying policy that can  expire, be cancelled or claimed.
 * @author Macario N. Peralta V
 * Date created: June 6 2022
 */
package CapStone;

public class Claim {
	
	private String claimNum;
	private int claimNumb;
	private String dateOfAcc;
    private String addOfAcc;
    private String desAcc;
    private String desDmgV;
    private double cost;
    private boolean claimed;
    
    public Claim(String dateOfAcc, String addOfAcc, String desAcc, String desDmgV, double cost, int clNum) {
    	this.dateOfAcc = dateOfAcc;
    	this.addOfAcc = addOfAcc;
    	this.desAcc = desAcc;
    	this.desDmgV = desDmgV;
    	this.cost = cost;
    	this.claimNumb = clNum;
    	this.claimNum = Integer.toString(clNum);
    }
	public void accidentClaim() {
		claimNum = String.format("%06d", claimNum);
		System.out.println("The claim number is: C" + claimNum);
	}
	
	public int getClaimNum() {
		return claimNumb;
	}
	
	public void seeDetails() {
		System.out.println("======================Claim============================");
		System.out.println("Date of the accident: " + dateOfAcc);
		System.out.println("Address of accident: " + addOfAcc);
		System.out.println("Brief Description of accident: " + desAcc);
		System.out.println("Description of damage to vehicle: " + desDmgV);
		System.out.println("Estimated cost of repairs: " + cost);
		
	}
	
	public void setDateAcc(String dateAcc) {
		
	}
	
	public void setAddAcc(String addAcc) {
		
	}
	
	public void setDesAcc(String desAcc) {
		
	}
	
	public void setDesDmgV(String desDV) {
		
	}
	
	public void setCost(String cost) {
		
	}
	
	public void claimed() {
		claimed = true;
	}
}
