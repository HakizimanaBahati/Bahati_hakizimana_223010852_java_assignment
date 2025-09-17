package ikusanya;

import java.util.Scanner;

public class ibicuruzwa {


	public static void main(String[] args) {




		Scanner input = new Scanner(System.in);
		System.out.print("Enter number of different items: ");
		int a = input.nextInt();
		input.nextLine();
		String[] itemNames = new String[a];
		double[] price = new double[a];
		int[] qtr = new int[a];
		double[] subt = new double[a];

		double total = 0;
		for (int i = 0; i < a; i++) {
			System.out.println("\nEnter details for item " + (i + 1) + ":");

			System.out.print("Item name: ");
			itemNames[i] = input.nextLine();

			System.out.println("Price per unit: ");
			price[i] = input.nextDouble();

			System.out.print("Quantity purchased: ");
			qtr[i] = input.nextInt();
			input.nextLine(); 


			subt[i] = price[i] * qtr[i];
			total += subt[i];
		}

		double discount = 0;
		if (total > 50000) {
			discount = total * 0.05;
		}
		double finalAmount = total - discount;


		System.out.println("\n========= SUPERMARKET RECEIPT =========");
		System.out.println("Item\t\tQty\tPrice\tSubtotal");

		for (int i = 0; i < a; i++) {
			System.out.println(itemNames[i] + "\t\t" 
					+ qtr[i] + "\t" 
					+ price[i] + "\t" 
					+ subt[i]);
		}

		System.out.println("------------------------------");
		System.out.println("Grand Total: " + total);
		System.out.println("Discount: " + discount);
		System.out.println("Final Amount Payable: " + finalAmount);
		System.out.println("=================MURAKOZE====================");
	}






}
