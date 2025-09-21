package banking;

import java.util.Scanner;

public class ATM {

	    private double balance;
	    private final int pin = 1234;

	    
	    public ATM(double initialBalance) {
	        this.balance = initialBalance;
	    }

	    public boolean verifyPin(int enteredPin) {
	        return enteredPin == pin;
	    }

	    
	    public void checkBalance() {
	        System.out.println("Current Balance: " + balance);
	    }

	    public void deposit(double amount) {
	        if (amount > 0) {
	            balance += amount;
	            System.out.println("Deposited: " + amount);
	        } else {
	            System.out.println("Invalid deposit amount!");
	        }
	    }

	    
	    public void withdraw(double amount) {
	        if (amount > 0 && amount <= balance) {
	            balance -= amount;
	            System.out.println("Withdrawn: " + amount);
	        } else if (amount > balance) {
	            System.out.println("Insufficient balance!");
	        } else {
	            System.out.println("Invalid withdrawal amount!");
	        }
	    }
	
	

	
	public static void main(String[] args) {
		  Scanner sc = new Scanner(System.in);

	        
	        ATM atm = new ATM(0);

	        System.out.print("Enter PIN: ");
	        int enteredPin = sc.nextInt();

	        if (atm.verifyPin(enteredPin)) {
	            System.out.println("PIN verified successfully!\n");

	            
	            atm.deposit(10000);   
	            atm.withdraw(3000);  
	            
	            atm.checkBalance();
	        } else {
	            System.out.println("Invalid PIN! Access denied.");
	        }

	        sc.close();
		
		
		
	}

}
