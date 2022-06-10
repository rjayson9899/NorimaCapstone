package policyTest;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.time.LocalDate;
import java.util.ArrayList;


public class PASApp {

	public static void main(String[] args)  {
		Scanner input = new Scanner(System.in);
		ArrayList<CustomerAccount> customerAccounts = new ArrayList<>();
		ArrayList<Claim> claims = new ArrayList<>();
		int choice = 0;
		int accountNumGenerator = 0;
		int policyNumGenerator = 0;
		
		do {
			//main menu user interface
			clrscrn(1);
			System.out.println("-------------------------------");
			System.out.println("          PAS System           ");
			System.out.println("-------------------------------");
			System.out.println("[1] Create Customer Account");
			System.out.println("[2] Get quote and Buy Policy");
			System.out.println("[3] Cancel Policy");
			System.out.println("[4] File Accident Claim");
			System.out.println("[5] Search Customer Account");
			System.out.println("[6] Search Policy");
			System.out.println("[7] Search Claim");
			System.out.println("[8] Exit");
			System.out.println("-------------------------------");
			System.out.print("Input number of choice: ");
			choice = input.nextInt();
			
			switch(choice) {
			
				case 1: //Creating Account
					clrscrn(1);
					input.nextLine();
					boolean checker = false;
					System.out.println("\n-------------------------------");
					System.out.println("       Creating account");
					System.out.println("-------------------------------");
					System.out.print("First Name: ");
					String fname = input.nextLine();
					System.out.print("Last Name: ");
					String lname = input.nextLine();
					System.out.print("Address: ");
					String address = input.nextLine();
				
					
					for(CustomerAccount y: customerAccounts) { //for each loop to check if an account already exist
						if(y.getFname().equalsIgnoreCase(fname) && y.getLname().equalsIgnoreCase(lname)
								&& y.getAddress().equalsIgnoreCase(address)) {
							System.out.println("Account already exist!");
							checker = true;
							break;
						}
					}
					
					if(!checker) {
						customerAccounts.add(new CustomerAccount(fname, lname, address));
						customerAccounts.get(customerAccounts.size() - 1).generateId(accountNumGenerator);
						accountNumGenerator++;
						System.out.println("Created account!");
						System.out.println("Your account number is: " + customerAccounts
						.get(customerAccounts.size() - 1).getAccountNum());
					}	
					
					
					
					break;
					
				case 2: // Quote/Buy policy
					clrscrn(1);
					System.out.println("\n-------------------------------");
					System.out.println("        Quoting policy");
					System.out.println("-------------------------------");
					System.out.print("Input account number: ");
					int accNum = input.nextInt();
					int indexGet = 0;
					boolean accExist = false, wrongDate = true;
					String make = "",model = "",color = "";
					int year = 0, type = 0, fuel = 0;
					double price = 0, total = 0;
					LocalDate effectDate, expiredDate;
					
					for(CustomerAccount x: customerAccounts) { //for each loop to get index of the account number , checks if account exist
						if(x.getAccountNum() == accNum) {
							System.out.println("Account exist!");
							indexGet = customerAccounts.indexOf(x);
							accExist = true;
						}
					}

					//Policy Holder Details
					
					if(accExist) { 
						clrscrn(1);
						System.out.println("\n-------------------------------");
						System.out.println("    Policy Holder Details");
						System.out.println("-------------------------------");
						input.nextLine();
						do{
							System.out.println("Input effective date(yyyy-mm-dd): ");
							System.out.print("ex.'2022-09-18': ");
							String date = input.nextLine();
							effectDate = LocalDate.parse(date);
							wrongDate = Policy.checkDate(effectDate);
						}while(!wrongDate);

						expiredDate= effectDate.plusMonths(6);
						System.out.println(expiredDate);
						//Add checker if the one who creates policy is the account holder or different person
						String fnamePol = "", lnamePol = "";
						System.out.print("Is the account owner also the policy holder? (y/n) ");
						Character decision = input.nextLine().charAt(0);
						
						if(decision.equals('y') || decision.equals('Y')) { //condition if the account owner is also the policy holder
							for(int x=0; x < customerAccounts.size(); x++) {
								if(accNum == customerAccounts.get(x).getAccountNum()) {
									fnamePol = customerAccounts.get(x).getFname();
									lnamePol = customerAccounts.get(x).getLname();
									System.out.println("Set the first name to: " + fnamePol);
									System.out.println("Set the last name to: " + lnamePol);
								}
							}
						}
						
						
						else {
							System.out.print("First Name: ");
							fnamePol = input.nextLine();
							System.out.print("Last Name: ");
							lnamePol = input.nextLine();	
						}
						
						System.out.println("Input birth date(yyyy-mm-dd): ");
						System.out.print("ex.'2022-09-18': ");
						String birthDateString = input.nextLine();
						LocalDate birthDate = LocalDate.parse(birthDateString);
						System.out.print("Input license number: ");
						String license = input.nextLine();
						System.out.println("Input license date issued(yyyy-mm-dd): ");
						System.out.print("ex.'2022-09-18': ");
						String licenseDateString = input.nextLine();
						LocalDate licenseDate = LocalDate.parse(licenseDateString);
						int licenseYear =licenseDate.getYear() ;

						customerAccounts.get(indexGet).addPolicyAct(new Policy(effectDate, 
																		new PolicyHolder(fnamePol, lnamePol, birthDate, license, licenseDate)));
						customerAccounts.get(indexGet).getPolicyAct().get(customerAccounts.get(indexGet).getPolicyAct().size() - 1).generateId(policyNumGenerator);
						customerAccounts.get(indexGet).getPolicyAct().get(customerAccounts.get(indexGet).getPolicyAct().size() - 1).setExpDate(expiredDate);
						customerAccounts.get(indexGet).getPolicyAct().get(customerAccounts.get(indexGet).getPolicyAct().size() - 1).setStatus();
																	
						policyNumGenerator++;
						
						//Vehicle Details
						System.out.print("How many vehicles for this policy? : ");
						int numVehicle = input.nextInt();
						input.nextLine();
						
						while(numVehicle > 0) {
							clrscrn(1);
							System.out.println("\n-------------------------------");
							System.out.println("       Vehicle Details");
							System.out.println("-------------------------------");
							System.out.print("Make: ");
							make = input.nextLine();
							System.out.print("Model: ");
							model = input.nextLine();
							System.out.print("Year: ");
							year = input.nextInt();
							
							System.out.println("Choose which type your vehicle is: ");
							System.out.println("[1] 4-door sedan");
							System.out.println("[2] 2-door sports car, SUV, or truck");
							System.out.println("-------------------------------");
							System.out.print("Your type of vehicle: ");
							type = input.nextInt();
							
							System.out.println("Choose which type of fuel: ");
							System.out.println("[1] Diesel");
							System.out.println("[2] Electric");
							System.out.println("[3] Petrol");
							System.out.println("-------------------------------");
							System.out.print("Your type of fuel: ");
							fuel = input.nextInt();
							
							System.out.print("Purchase price: ");
							price = input.nextDouble();
							System.out.print("Color: ");
							input.nextLine();
							color = input.nextLine();
					

							customerAccounts.get(indexGet).getPolicyAct().get(customerAccounts.get(indexGet).getPolicyAct().size() - 1)
												.addVehicles(new Vehicle(make,model,year, type, fuel, price, color, licenseYear));
							
							numVehicle--;
						}
						clrscrn(1);
						System.out.println("-------------------------------");
						System.out.println(" Details of your Policy Holder ");
						System.out.println("-------------------------------");
						System.out.println("Policy Number: " +(policyNumGenerator + 1));
						System.out.println("Full name: " + fnamePol + " " + lnamePol );
						System.out.println("Effective Date: " + effectDate );
						System.out.format("Birthdate: %-5s \n", birthDate );
						System.out.format("License Number:  %-5s \n", license );
						System.out.format("License Date: %-5s ", licenseDate );
						System.out.println("\n-------------------------------");
						

						for(Vehicle v: customerAccounts.get(indexGet).getPolicyAct()
										.get(customerAccounts.get(indexGet).getPolicyAct().size() - 1).getVehicles()) {

							System.out.println("-------------------------------");
							System.out.println("    Details of your Vehicle    ");
							System.out.println("-------------------------------");
							System.out.println("Make: " + v.getMake());
							System.out.println("Model: " + v.getModel());
							System.out.println("Year: " + v.getYear());
							
							if(v.getType() == 1) {
								System.out.println("Type of vehicle: 4-door sedan");
							}
							
							else if(v.getType() == 2) {
								System.out.println("Type of vehicle: 2-door sports car, SUV, or truck");
							}
							
							if(v.getFuel() == 1) {
								System.out.println("Type of fuel: Diesel");
							}
							
							else if(v.getFuel() == 2) {
								System.out.println("Type of fuel: Electronic");
							}
							
							else if (v.getFuel() == 3) {
								System.out.println("Type of fuel: Petrol");
							}
							
							System.out.println("Purchase price: " + price);
							System.out.println("Color: " + color);
							System.out.println("-------------------------------\n");
							
							total += v.getPremium();
						}
						
						customerAccounts.get(indexGet).getPolicyAct()
							.get(customerAccounts.get(indexGet).getPolicyAct().size() - 1).setCost(total);
						System.out.println("-------------------------------");
						System.out.println(" Total Premium: $" + total);
						System.out.println("-------------------------------\n");
						
						System.out.println("-------------------------------");
						System.out.println("Would you like to buy the policy? (y/n)");
						decision = input.nextLine().charAt(0);
						
						if(decision.equals('y') || decision.equals('Y')) {
							
							System.out.println("Policy bought!");
						}
						
						else {
							System.out.println("Policy cancelled!");
							customerAccounts.get(indexGet).getPolicyAct().remove(customerAccounts.get(indexGet).getPolicyAct().size() - 1);
							policyNumGenerator--;
						}
						
						
					
					}
					
					else {
						System.out.println("Account does not exist!");
					}
	
					break;
					
				case 3:
					clrscrn(1);
					int indexPol = 0, indexCusPol = 0, indexCus = 0;
					boolean doesExist = false;
					Character confirm;

					System.out.println("\n-------------------------------");
					System.out.println("        Cancel Policy");
					System.out.println("-------------------------------");
					System.out.print("Input Policy Number: ");
					int policyNum = input.nextInt();
					
					for(CustomerAccount c: customerAccounts){
						for(Policy p: c.getPolicyAct()){
							if(p.getPolicyNum() == policyNum){
								doesExist = true;
								indexPol = c.getPolicyAct().indexOf(p);
								indexCus = customerAccounts.indexOf(c);
								p.displayInfo();
								break;
							}
						}
					}
				
					if(customerAccounts.get(indexCus).getPolicyAct().get(indexPol).getStatus().equals("Expired")){
						System.out.println("Policy is Expired!");
						System.out.println("Going back to main menu");
						clrscrn(3);
					}

					else{
						input.nextLine();
						System.out.println("Would you like to cancel this policy? (y/n)");
						confirm = input.nextLine().charAt(0);

						if(confirm.equals('y') || confirm.equals('Y')){
							System.out.println("Input new expiration date(yyyy-mm-dd): ");
							System.out.print("ex.'2022-09-18': ");
							String expDateString = input.nextLine();
							LocalDate expDate = LocalDate.parse(expDateString);
							customerAccounts.get(indexCus).getPolicyAct().get(indexPol).setExpDate(expDate);;
							customerAccounts.get(indexCus).getPolicyAct().get(indexPol).setStatus();
							System.out.println("Successfully edited expiration policy!"); 
						}

						else{
							System.out.println("Going back to main menu");
						}
					}
					
					break;
					
				case 4:
					clrscrn(1);

					System.out.println("\n-------------------------------");
					System.out.println("     File Accident Claim");
					System.out.println("-------------------------------");
					System.out.print("Input Policy Number: ");
					int policy1 = input.nextInt();

					
					
					//Add condition if policy exist
					clrscrn(1);
					input.nextLine();
					System.out.println("\n-------------------------------");
					System.out.println("Claim Details");
					System.out.println("-------------------------------");
					System.out.println("Input accident date(yyyy-mm-dd): ");
					System.out.print("ex.'2022-09-18': ");
					String accidentDateString = input.nextLine();
					LocalDate accidentDate = LocalDate.parse(accidentDateString);
					System.out.print("Address of where the accident happened: ");
					String accidentAdd = input.nextLine();
					System.out.print("Description of the accident: ");
					String descriptionAccident = input.nextLine();
					System.out.print("Description of damage to vehicle: ");
					String descriptionDamage = input.nextLine();
					System.out.print("Estimated cost of repairs: ");
					double estCost = input.nextDouble();
					
					
					
					System.out.println("Successfully Claimed policy!");
					break;
					
					
				case 5:
					clrscrn(1);
					System.out.println("\n-------------------------------");
					System.out.println("       Search Customer");
					System.out.println("-------------------------------");
					System.out.println("How would you like to search for customer?");
					System.out.println("[1] Using first and last name");
					System.out.println("[2] Using account number");
					System.out.println("-------------------------------");
					System.out.print("Input number of choice: ");
					int search = input.nextInt();
					
					if(search == 1) {
						input.nextLine();
						System.out.print("Input first name: ");
						String fNameSearch = input.nextLine();
						System.out.print("Input last name: ");
						String lNameSearch = input.nextLine();
						
					}
					
					else if(search == 2) {
						System.out.print("Input account number: ");
						int accountNum = input.nextInt();
					}
					
					else {
						System.out.println("Wrong Input!");
					}
					break;
					
					
				case 6:
					clrscrn(1);

					System.out.println("\n-------------------------------");
					System.out.println("        Search Policy");
					System.out.println("-------------------------------");
					System.out.print("Input policy number: ");
					int policyNumSearch = input.nextInt();
					System.out.println(policyNumSearch);
					
					break;
					
				case 7:
					clrscrn(1);
					System.out.println("\n-------------------------------");
					System.out.println("        Search Claim");
					System.out.println("-------------------------------");
					System.out.print("Input claim number: ");
					int claimNum = input.nextInt();
					System.out.println(claimNum);
					break;
				
				default:
					clrscrn(1);
					
				
			}
			
		}while(choice!=8);
		
		input.close();
	}
	
	private static void clrscrn(int timer) {
		System.out.println("Processing...");
		try {
			TimeUnit.SECONDS.sleep(timer);
			System.out.print("\033[H\033[2J");  
			System.out.flush();  
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	
}
