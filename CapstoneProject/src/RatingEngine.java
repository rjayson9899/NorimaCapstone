
package CapStone;

public class ratingEngine {

	private double premium;
	private double vP;
	private double vPF;
	private double dLX;
	private int carAge;
	
	public ratingEngine() {
		
	}
	
	public void setVPrice() {
		System.out.println("Enter the vehicle price: ");
	}
	
	public void setVPF(int carYear) {
		
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
	
	public void calcP() {
		premium = (vP * vPF) + ((vP/100)/dLX);
	}
	
	
}
