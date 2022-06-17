package CapStone;
/*
 * This is the claim class of the whole capstone project.
 * This class contains the data for the claim information.
 * @author Macario N. Peralta V
 * Date created: June 6 2022
 */
public class Claim {
	
	private String claimNum;
	private String dateOfAcc;
    private String addOfAcc;
    private String desAcc;
    private String desDmgV;
    private double cost;

    
    public Claim(String dateOfAcc, String addOfAcc, String desAcc, String desDmgV, double cost, String clNum) {
    	this.dateOfAcc = dateOfAcc;
    	this.addOfAcc = addOfAcc;
    	this.desAcc = desAcc;
    	this.desDmgV = desDmgV;
    	this.cost = cost;
    	this.claimNum = "C" + clNum;
    }
    
	public void accidentClaim() {
		System.out.println("The claim number is: " + claimNum);
	}
	
	public String getClaimNum() {
		return claimNum;
	}
	
	//this method shows the claim details.
	public void seeDetails() {
		System.out.println("======================Claim============================");
		System.out.println("Date of the accident: " + dateOfAcc);
		System.out.println("Address of accident: " + addOfAcc);
		System.out.println("Brief Description of accident: " + desAcc);
		System.out.println("Description of damage to vehicle: " + desDmgV);
		System.out.println("Estimated cost of repairs: " + cost);
		System.out.println("=======================================================");
	}
	
}
