package policyTest;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.time.LocalDate;
import java.util.ArrayList;



public class PASApp {

	public static void main(String[] args)  {
		//initializations for global variables
		Scanner input = new Scanner(System.in);
		ArrayList<CustomerAccount> customerAccounts = new ArrayList<>();
		ArrayList<Claim> claims = new ArrayList<>();
		boolean accExist = false;
		int choice = 0;
		int accountNumGenerator = 0;
		int policyNumGenerator = 0;
		int claimNumGenerator = 0;
		
		do {

			for(CustomerAccount c: customerAccounts){ //A for loop that always update the status of each policy
				for(Policy p: c.getPolicyAct()){
					p.setStatus();
				}
			}
			//main menu user interface
			clrscrn(0, false);
			choice = checkerInt(input, "-------------------------------\n          PAS System           \n-------------------------------\n"+ 
								"[1] Create Customer Account\n[2] Get quote and Buy Policy\n[3] Cancel Policy\n" + 
								"[4] File Accident Claim\n[5] Search Customer Account\n[6] Search Policy\n[7] Search Claim\n" +
								"[8] Exit\n-------------------------------\nInput number of choice: ", false, false);
			
			
			
			switch(choice) {
			
				case 1: //Creating Account
					clrscrn(1, true);
					if(accountNumGenerator > 9998){
						System.out.println("No more room for a new account.");
						System.out.println("Returning to main menu.");
						clrscrn(1, false);
					}
					else{
						//input.nextLine();
						accExist = false;
						System.out.println("\n-------------------------------");
						System.out.println("       Creating account");
						System.out.println("-------------------------------");
						//System.out.print("First Name: ");
						String fname = checkerString(input, "First Name: ", true);
						//System.out.print("Last Name: ");
						String lname = checkerString(input, "Last Name: ", true);
						//System.out.print("Address: ");
						String address = checkerString(input, "Address: ", false);;
					
						
						for(CustomerAccount y: customerAccounts) { //for each loop to check if an account already exist
							if(y.getFname().equalsIgnoreCase(fname) && y.getLname().equalsIgnoreCase(lname)
									&& y.getAddress().equalsIgnoreCase(address)) {
								System.out.println("Account already exist!");
								accExist = true;
								clrscrn(1, false);
								//input.nextLine();
								break;
							}
						}
						
						if(!accExist) {
							customerAccounts.add(new CustomerAccount(fname, lname, address));
							customerAccounts.get(customerAccounts.size() - 1).generateId(accountNumGenerator);
							accountNumGenerator++;
							System.out.println("Created account!");
							System.out.println("Your account number is: " + customerAccounts.get(customerAccounts.size() - 1).getAccountNum());
							
							pressAnyKeyToContinue();
						}	
					}

					break;
					
				case 2: // Quote/Buy policy
					clrscrn(1, true);
					//input.nextLine();
					//initializing local variables
					int indexGet = 0;
					boolean wrongDate = true;
					accExist = false;
					String make = "",model = "",color = "";
					int year = 0, type = 0, fuel = 0;
					double price = 0, total = 0;
					LocalDate effectDate, expiredDate;

					if(policyNumGenerator > 99998){
						System.out.println("No more room for a new policy.");
						System.out.println("Returning to main.");
						clrscrn(1, false);
					}

					else{

						System.out.println("\n-------------------------------");
						System.out.println("        Quoting policy");
						System.out.println("-------------------------------");
						System.out.print("Input account number: ");
						String accNum = input.nextLine();
						
						for(CustomerAccount x: customerAccounts) { //for each loop to get index of the account number , checks if account exist
							if(x.getAccountNum().equals(accNum)) {
								System.out.println("Account exist!");
								indexGet = customerAccounts.indexOf(x);
								accExist = true;
							}
						}
						//Policy Holder Details
						if(accExist) { 
							clrscrn(1, true);
							System.out.println("\n-------------------------------");
							System.out.println("    Policy Holder Details");
							System.out.println("-------------------------------");
							do{
								effectDate = checkerDate(input, "Input effective date(yyyy-mm-dd):\nex.'2022-09-18': ");
								wrongDate = Policy.checkDate(effectDate);
							}while(!wrongDate);

							expiredDate= effectDate.plusMonths(6);
							System.out.println("Set expired date to: " + expiredDate);
							//Add checker if the one who creates policy is the account holder or different person
							String fnamePol = "", lnamePol = "";
							System.out.print("Is the account owner also the policy holder? (y/n) ");
							Character decision = input.nextLine().charAt(0);
							
							if(decision.equals('y') || decision.equals('Y')) { //condition if the account owner is also the policy holder
								for(CustomerAccount c: customerAccounts) {
									if(c.getAccountNum().equals(accNum)) {
										fnamePol = c.getFname();
										lnamePol = c.getLname();
										System.out.println("Set the first name to: " + fnamePol);
										System.out.println("Set the last name to: " + lnamePol);
										break;
									}
								}
							}
								
							else {
								//System.out.print("First Name: ");
								fnamePol = checkerString(input, "First Name: ", true);
								//System.out.print("Last Name: ");
								lnamePol = checkerString(input, "Last Name: ", true);	
							}
							
							LocalDate birthDate = checkerDate(input,"Input birth date(yyyy-mm-dd):\nex.'2022-09-18': ");
							System.out.print("Input license number: ");
							String license = input.nextLine();
							LocalDate licenseDate = checkerDate(input, "Input license date issued(yyyy-mm-dd):\n ex.'2022-09-18': " );
							int licenseYear =licenseDate.getYear() ;

							customerAccounts.get(indexGet).addPolicyAct(new Policy(effectDate, 
																			new PolicyHolder(fnamePol, lnamePol, birthDate, license, licenseDate)));
							customerAccounts.get(indexGet).addPolicyHolders();
							customerAccounts.get(indexGet).getPolicyAct().get(customerAccounts.get(indexGet).getPolicyAct().size() - 1).generateId(policyNumGenerator);
							customerAccounts.get(indexGet).getPolicyAct().get(customerAccounts.get(indexGet).getPolicyAct().size() - 1).setExpDate(expiredDate);
							customerAccounts.get(indexGet).getPolicyAct().get(customerAccounts.get(indexGet).getPolicyAct().size() - 1).setStatus(" ");
																		
							policyNumGenerator++;
							
							//Vehicle Details
							int numVehicle = checkerInt(input, "How many vehicles for this policy? : ", false, true);
							input.nextLine();
							
							while(numVehicle > 0) {
								clrscrn(1, true);
								System.out.println("\n-------------------------------");
								System.out.println("       Vehicle Details");
								System.out.println("-------------------------------");
								//System.out.print("Make: ");
								make = checkerString(input, "Make: ", false);
								//System.out.print("Model: ");
								model = checkerString(input, "Model: ", false);
								year = checkerInt(input, "Year: ", true, false);
								input.nextLine();
								System.out.println("Choose which type your vehicle is: ");
								System.out.println("[1] 4-door sedan");
								System.out.println("[2] 2-door sports car, SUV, or truck");
								System.out.println("-------------------------------");
								type = checkerInt(input, "Your type of vehicle: ", false, false);
								
								System.out.println("Choose which type of fuel: ");
								System.out.println("[1] Diesel");
								System.out.println("[2] Electric");
								System.out.println("[3] Petrol");
								System.out.println("-------------------------------");
								fuel = checkerInt(input, "Your type of fuel: ", false, false);
								//input.nextLine();
								price = checkerDouble(input, "Purchase price: ");
								System.out.print("Color: ");
								input.nextLine();
								color = input.nextLine();
						

								customerAccounts.get(indexGet).getPolicyAct().get(customerAccounts.get(indexGet).getPolicyAct().size() - 1)
													.addVehicles(new Vehicle(make,model,year, type, fuel, price, color, licenseYear));
								
								numVehicle--;
							}
							clrscrn(1, true);
							customerAccounts.get(indexGet).getPolicyAct().get(customerAccounts.get(indexGet).getPolicyAct().size() - 1)
													.getDetails();
							

							for(Vehicle v: customerAccounts.get(indexGet).getPolicyAct()
											.get(customerAccounts.get(indexGet).getPolicyAct().size() - 1).getVehicles()) {
								v.getDetails();
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
								customerAccounts.get(indexGet).getPolicyAct().get(customerAccounts.get(indexGet).getPolicyAct().size() - 1).setStatus();
								System.out.println("Policy bought! Your policy number is: " + 
								customerAccounts.get(indexGet).getPolicyAct().get(customerAccounts.get(indexGet).getPolicyAct().size() - 1).getPolicyNum());
								pressAnyKeyToContinue();
							}
							
							else {
								System.out.println("Policy cancelled!\nReturning to main menu...");
								customerAccounts.get(indexGet).getPolicyAct().remove(customerAccounts.get(indexGet).getPolicyAct().size() - 1);
								policyNumGenerator--;
								//System.out.println(customerAccounts.get(indexGet).getPolicyAct().get(customerAccounts.get(indexGet).getPolicyAct().size() - 1).getPolicyNum());

								for(CustomerAccount c: customerAccounts){
									for(Policy p: c.getPolicyAct()){
										System.out.println(p.getPolicyNum());
									}
								}
								clrscrn(1, false);
							}

						}
						
						else {
							System.out.println("Account does not exist!");
							clrscrn(1, false);
						}

					}

					
	
					break;
					
				case 3:
					clrscrn(1,true);
					//input.nextLine();
					int indexPol = 0, indexCus = 0;
					Character confirm;
					accExist = false;

					System.out.println("\n-------------------------------");
					System.out.println("        Cancel Policy");
					System.out.println("-------------------------------");
					System.out.print("Input Policy Number: ");
					String policyNum = input.nextLine();
					
					for(CustomerAccount c: customerAccounts){
						for(Policy p: c.getPolicyAct()){
							if(p.getPolicyNum().equals(policyNum)){
								indexPol = c.getPolicyAct().indexOf(p);
								indexCus = customerAccounts.indexOf(c);
								accExist = true;
								p.getDetails();
								break;
							}
						}
					}
					
					if(!accExist){
						System.out.println("Policy does not exist!");
						clrscrn(1, false);
					}

					else{
						if(customerAccounts.get(indexCus).getPolicyAct().get(indexPol).getStatus().equals("Expired")){
							System.out.println("Policy is Expired!");
							pressAnyKeyToContinue();
						}
	
						else{
							
							System.out.println("Would you like to cancel this policy? (y/n)");
							confirm = input.nextLine().charAt(0);
	
							if(confirm.equals('y') || confirm.equals('Y')){
								LocalDate expDate = checkerDate(input, "Input new expiration date(yyyy-mm-dd):\nex.'2022-09-18': ");
								customerAccounts.get(indexCus).getPolicyAct().get(indexPol).setExpDate(expDate);;
								customerAccounts.get(indexCus).getPolicyAct().get(indexPol).setStatus();
								System.out.println("Successfully edited expiration policy!"); 
								pressAnyKeyToContinue();
							}
	
							else{
								System.out.println("Going back to main menu");
								clrscrn(1, false);
							}
						}
					}
						
					break;
					
				case 4:
					clrscrn(1,true);
					//input.nextLine();
					String status = "";
					accExist = false;

					if(claimNumGenerator > 9998){
						System.out.println("No more room for claims.");
						System.out.println("Returning to main menu");
						clrscrn(1, false);
					}

					else{
						System.out.println("\n-------------------------------");
						System.out.println("     File Accident Claim");
						System.out.println("-------------------------------");
						System.out.print("Input Policy Number: ");
						String policy1 = input.nextLine();

						for(CustomerAccount c: customerAccounts){
							for(Policy p: c.getPolicyAct()){
								if(p.getPolicyNum().equals(policy1)){
									System.out.println(p.getPolicyNum());
									clrscrn(10, false);
									indexPol = c.getPolicyAct().indexOf(p);
									indexCus = customerAccounts.indexOf(c);
									status = p.getStatus();
									accExist = true;
									break;
								}
							}
						}

						if(!accExist){
							System.out.println("Policy does not exist!\nReturning to main menu...");
							clrscrn(1, false);
						}

						else{

							if(status.equals("Scheduled") || status.equals("Expired")){
								System.out.println("Policy expired or not yet enforced");
							}
							else{
								clrscrn(1, true);

								System.out.println("\n-------------------------------");
								System.out.println("        Claim Details");
								System.out.println("-------------------------------");
								LocalDate accidentDate = checkerDate(input, "Input accident date(yyyy-mm-dd):\nex.'2022-09-18': ");
								System.out.print("Address of where the accident happened: ");
								String accidentAdd = input.nextLine();
								System.out.print("Description of the accident: ");
								String descriptionAccident = input.nextLine();
								System.out.print("Description of damage to vehicle: ");
								String descriptionDamage = input.nextLine();
								double estCost = checkerDouble(input, "Estimated cost of repairs: " );
								
								claims.add(new Claim( accidentDate, accidentAdd, descriptionAccident,
											descriptionDamage,estCost));
								claims.get(claims.size()-1).generateId(claimNumGenerator);
								
								
								claimNumGenerator++;
								
								System.out.println("Successfully filed a claim!");
								System.out.println("Your claim number is: " + claims.get(claims.size()-1).getClaimNum());
								pressAnyKeyToContinue();
							}
						}
					}
					
					
				
					break;
					
					
				case 5:
					clrscrn(1,true);
					accExist = false;

					int search = checkerInt(input, "\n-------------------------------\n       Search Customer\n-------------------------------\n" + 
											"[1] Using first and last name\n[2] Using account number\n-------------------------------\nInput number of choice: ", false, false);
					
					if(search == 1) {
						//input.nextLine();
						System.out.print("Input first name: ");
						String fNameSearch = input.nextLine();
						System.out.print("Input last name: ");
						String lNameSearch = input.nextLine();

						for(CustomerAccount c: customerAccounts){
							if(c.getFname().equalsIgnoreCase(fNameSearch) && c.getLname().equalsIgnoreCase(lNameSearch)){
								c.getDetails();
								accExist = true;
								pressAnyKeyToContinue();
								break;
							}
						}
						if(!accExist){
							System.out.println("No customer found!");
							clrscrn(1, false);
						}
						
					}
					
					else if(search == 2) {
						//input.nextLine();
						System.out.print("Input account number: ");
						String accountNum = input.nextLine();

						for(CustomerAccount c: customerAccounts){
							if(c.getAccountNum().equals(accountNum)){
								c.getDetails();
								accExist = true;
								pressAnyKeyToContinue();
							}
						}
					}
					
					else {
						System.out.println("Wrong Input!");
					}

					if(!accExist){
						System.out.println("No account exist");
						clrscrn(1, false);
					}
					break;
					
					
				case 6:
					//input.nextLine();
					clrscrn(1,true);
					accExist = false;
					System.out.println("\n-------------------------------");
					System.out.println("        Search Policy");
					System.out.println("-------------------------------");
					System.out.print("Input policy number: ");
					String policyNumSearch = input.nextLine();
					for(CustomerAccount c: customerAccounts){
						for(Policy p: c.getPolicyAct()){
							if(p.getPolicyNum().equals(policyNumSearch)){
								accExist = true;
								p.getDetails();
								pressAnyKeyToContinue();
								break;
							}
						}

						if(!accExist){
							System.out.println("No policy exist!");
							clrscrn(1, false);
						}
					}
					
					break;
					
				case 7:
					clrscrn(1,true);
					//input.nextLine();
					accExist = false;
					System.out.println("\n-------------------------------");
					System.out.println("        Search Claim");
					System.out.println("-------------------------------");
					System.out.print("Input claim number: ");
					String claimNum = input.nextLine();
					
					for(Claim c: claims){
						if(c.getClaimNum().equals(claimNum)){
							accExist = true;
							c.getDetails();
							pressAnyKeyToContinue();
							break;
						}
					}

					if(!accExist){
						System.out.println("No claim exist!");
						clrscrn(1, false);
					}

					break;
				
				case 8:
					System.exit(0);
					break;
				
				case 9: // for checking
					policyNumGenerator = 9999;
					accountNumGenerator = 9999;
					claimNumGenerator = 99999;
					break;

				default:
					System.out.println("Wrong input!");
					clrscrn(1,false);
					
				
			}
			
			
		}while(choice!=8);
		
		input.close();
	}
	
	private static void clrscrn(int timer, boolean showMessage) {
		if(showMessage){
			System.out.println("Processing...");
		}
		try {
			TimeUnit.SECONDS.sleep(timer);
			System.out.print("\033[H\033[2J");  
			System.out.flush();  
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	private static void pressAnyKeyToContinue(){ 
        System.out.println("Press Enter key to continue...");
        try
        {
            System.in.read();
			System.in.reset();
        }  
        catch(Exception e)
        {}  
 	}

	 private static int checkerInt(Scanner inpt, String msg, boolean isYear, boolean isCar) {
		int output = 0;

		if(isYear){
			System.out.print(msg);
			while(!inpt.hasNextInt()) {
				System.out.println("wrong input!");
				inpt.next();
				clrscrn(1, false);
				System.out.print(msg);
			}
			
			boolean yearCheck = false;
			while(!yearCheck){
				System.out.print(msg);
				output = inpt.nextInt();
				if(output<1990 || output > 2022){
					System.out.println("Wrong input");
				}
				else{
					return output;
				}
			}	
		}

		else if(isCar){
			System.out.print(msg);
			while(!inpt.hasNextInt()) {
				System.out.println("wrong input!");
				inpt.next();
				clrscrn(1, false);
				System.out.print(msg);
			}

			output = inpt.nextInt();

			return output;

		}

		else{
			boolean accepted = false;
			System.out.print(msg);
			while(!accepted){
				try {
					String input = inpt.nextLine();
					if(input.length() > 1){
						System.out.println("Wrong input in length!");
						clrscrn(1, false);
						System.out.print(msg);
					}
					else{
						output = Integer.parseInt(input);
						accepted = true;
					}
				} catch (Exception e) {
					clrscrn(0, false);
					System.out.print(msg);
				}
			}		
		}
		
		return output;
	}

	private static LocalDate checkerDate(Scanner inpt, String msg) {
		LocalDate dateInput = LocalDate.now();
		boolean isFinished = false;

		do{
			
			System.out.print(msg);
			try{	
				String date = inpt.nextLine();
				dateInput = LocalDate.parse(date);
				isFinished = true;
			}
			catch(Exception e){
				System.out.println("Wrong input for date, follow the format");
				clrscrn(1,false);
				
			}
		}while(!isFinished);

		return dateInput;
	}

	private static double checkerDouble(Scanner inpt, String msg) {

		System.out.print(msg);
		while(!inpt.hasNextDouble()) {
			System.out.println("wrong input!");
			inpt.next();
			clrscrn(1,false);
			System.out.print(msg);
		}
		double output = inpt.nextDouble();
		
		return output;
	}
	

	private static String checkerString(Scanner inpt, String msg, boolean check){
		String regex1 = "[A-Z a-z\\.,]+";
		String regex2 = "[A-Z a-z\\.,0-9]+";
		String output = "";
		if(check){
			while(!output.matches(regex1)){
				System.out.print(msg);
				output = inpt.nextLine();
				output.trim();
				if(!output.matches(regex1)){
					System.out.println("Special characters or numeric values are not accepted.");
				}
			}
		}

		else{
			while(!output.matches(regex2)){
				System.out.print(msg);
				output = inpt.nextLine();
	
				if(!output.matches(regex2)){
					System.out.println("Special characters or numeric values are not accepted.");
				}
			}
		}
		

		return output;
	}


	
	
}
