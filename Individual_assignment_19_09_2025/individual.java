package Shape;

import java.util.Scanner;

public class assignment {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String continueChoice;

		do {

			System.out.println("Select a shape:");
			System.out.println("1. Rectangle");
			System.out.println("2. Circle");
			System.out.println("3. Square");
			System.out.println("4. Triangle");
			System.out.print("Enter your choice (1-4): ");
			int Choice = sc.nextInt();


			System.out.println("\nWhat would you like to calculate?");
			System.out.println("1. Area");
			System.out.println("2. Perimeter");
			System.out.print("Enter your choice (1-2): ");
			int Choi = sc.nextInt();

			double result = 0;

			switch (Choice) {
			case 1: 
				System.out.print("Enter length: ");
				double length = sc.nextDouble();
				System.out.print("Enter width: ");
				double width = sc.nextDouble();
				if (calcChoice == 1) {
					result = length * width;
					System.out.println("Area of Rectangle = " + result);
				} else {
					result = 2 * (length + width);
					System.out.println("Perimeter of Rectangle = " + result);
				}
				break;

			case 2: 
				System.out.print("Enter radius: ");
				double radius = sc.nextDouble();
				if (Choi  == 1) {
					result = Math.PI * radius * radius;
					System.out.println("Area of Circle = " + result);
				} else {
					result = 2 * Math.PI * radius;
					System.out.println("Perimeter of Circle = " + result);
				}
				break;

			case 3: 
				System.out.print("Enter side: ");
				double side = sc.nextDouble();
				if (Choi  == 1) {
					result = side * side;
					System.out.println("Area of Square = " + result);
				} else {
					result = 4 * side;
					System.out.println("Perimeter of Square = " + result);
				}
				break;

			case 4: 
				if (Choi == 1) {
					System.out.print("Enter base: ");
					double base = sc.nextDouble();
					System.out.print("Enter height: ");
					double height = sc.nextDouble();
					result = (base * height) / 2;
					System.out.println("Area of Triangle = " + result);
				} else {
					System.out.print("Enter side A: ");
					double a = sc.nextDouble();
					System.out.print("Enter side B: ");
					double b = sc.nextDouble();
					System.out.print("Enter side C: ");
					double c = sc.nextDouble();
					result = a + b + c;
					System.out.println("Perimeter of Triangle = " + result);
				}
				break;

			default:
				System.out.println("Invalid shape choice!");
			}


			System.out.print("\nWould you like to continue? (yes/no): ");
			continueChoice = sc.next();

		} while (continueChoice.equalsIgnoreCase("yes"));

		System.out.println("Wakoze gukoresha system yacu.");
		sc.close();
	}


}











