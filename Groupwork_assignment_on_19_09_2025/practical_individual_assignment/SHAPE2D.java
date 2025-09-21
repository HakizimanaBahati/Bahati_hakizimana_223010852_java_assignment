package COM.ENTITY;

import java.util.Scanner;

public class SHAPE2D 

{


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String cont;

		do {
			System.out.println("Select a shape");
			System.out.println("1.\tRectangle");
			System.out.println("2.\tCircle");
			System.out.println("3.\tSquare");
			System.out.println("4.\tTriangle");
			int choice = sc.nextInt();

			SHAPES shape = null;
			switch(choice) {
			case 1: shape = new Rectangle();
			break;
			case 2: shape = new circle(); 
			break;
			case 3: shape = new Square(); 
			break;
			case 4: shape = new Triangle(); 
			break;
			}

			System.out.println("What would you like to calculate");
			System.out.println("A.\tArea");
			System.out.println("B.\tPerimeter");
			char calc = sc.next().toUpperCase().charAt(0);

			if(calc == 'A') System.out.println("Area = " + shape.calculateArea());
			else System.out.println("Perimeter = " + shape.calculatePerimeter());

			System.out.print("Would you like to continue?\nYes/no: ");
			cont = sc.next();

		} while(cont.equalsIgnoreCase("yes"));

		System.out.println("Thank you for using system");
		sc.close();
	}


}
