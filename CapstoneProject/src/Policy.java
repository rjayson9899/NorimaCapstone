import java.time.LocalDate;
import java.util.Scanner;

public class Policy {
	private int policyNumber;
	private LocalDate effectiveDatePolicy;
	private LocalDate expirationDatePolicy;
	public static final int POLICY_MAX = 999999;
	Scanner input = new Scanner(System.in);
	
	public Policy() {
		
	}
	
	public Policy(int policyNumber) {
		this.policyNumber = policyNumber;
	}

	public void createPolicyDate() {
		int year, month, day;
		System.out.println("Create a Policy Date");
		System.out.print("Enter year (yyyy): ");
		year = input.nextInt();
		System.out.print("Enter month (mm): ");
		month = input.nextInt();
		System.out.print("Enter day (dd): ");
		day = input.nextInt();
		
		LocalDate customDate = LocalDate.of(year, month, day); 
		this.effectiveDatePolicy = customDate ;
		customDate = customDate.plusMonths(6);
		this.expirationDatePolicy = customDate;
		
		System.out.println("Policy Effective Date: " + this.effectiveDatePolicy);
		System.out.println("Policy Expiration Date: " + this.expirationDatePolicy);
	}

}
