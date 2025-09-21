package amafaranga;

public class bank {

	private String accountNumber;
	private String holderName;
	private double balance;


	public bank(String accountNumber, String holderName, double balance) {
		this.accountNumber = accountNumber;
		this.holderName = holderName;
		this.balance = balance;
	}


	public void deposit(double amount) {
		if (amount > 0) {
			balance += amount;
			System.out.println(holderName+ " deposited: " + amount);
		} else {
			System.out.println("invalid input.");
		}
		checkLowBalance();
	}


	public void withdraw(double amount) {
		if (amount > balance) {
			System.out.println("Insufficient Funds " );
		} else {
			balance -= amount;
			System.out.println(holderName + " withdrew: " + amount);
		}
		checkLowBalance();
	}


	private void checkLowBalance() {
		if (balance < 1000) {
			System.out.println("Warning: Low Balance");
		}
	}


	public void displayAccount() {
		System.out.println("Nimero ya konti: " + accountNumber);
		System.out.println("Amazina: " + holderName);
		System.out.println("Asigaye: " + balance);
		System.out.println("====================");
	}


	public double getBalance() {
		return balance;
	}




	public static void main(String[] args) {

		bank acc1 = new bank("ACC0001", "BAHATI", 0);
		bank acc2 = new bank("ACC0002", "OLIVIER", 1000);

		acc1.deposit(5000);

		acc1.withdraw(2000);

		System.out.println(" Asigaye :");
		acc1.displayAccount();
		acc2.displayAccount();





	}

}
