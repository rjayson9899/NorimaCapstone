import java.time.LocalDate;

public class Policy {
	private int policyNumber;
	private LocalDate effectiveDatePolicy;
	private LocalDate expirationDatePolicy;
	public static final int POLICY_MAX = 999999;
	
	
	public Policy() {
		
	}
	
	public Policy(int policyNumber) {
		this.policyNumber = policyNumber;
	}

	public LocalDate getEffectiveDatePolicy() {
		return effectiveDatePolicy;
	}

	public LocalDate getExpirationDatePolicy() {
		return expirationDatePolicy;
	}

	public int getPolicyNumber() {
		return policyNumber;
	}
	
	


}
