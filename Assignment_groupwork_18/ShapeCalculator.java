//1. Bahati HAKIZIMANA 		223010852
//2. Olivier HABINGABIRE	223007847
//3. Federance TUYIZERE 	223003521
//4. Christine KIRABO		223008741
//5. Dieudonne IRADUKUNDA	223015105
//6. Felicite NYIRANZIZA	223005134
//7. Hidaya UMUTONI			222020222


package com.case4;

import java.util.Scanner;

//Rectangle class
class Rectangle {
 double length, width;

 Rectangle(double length, double width) {
     this.length = length;
     this.width = width;
 }

 double calculateArea() {
     return length * width;
 }
}

//Circle class
class Circle {
 double radius;

 Circle(double radius) {
     this.radius = radius;
 }

 double calculateArea() {
     return Math.PI * radius * radius;
 }
}

//Main class
public class ShapeCalculator {
 public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);

     System.out.println("Choose a shape to calculate area:");
     System.out.println("1. Rectangle");
     System.out.println("2. Circle");
     System.out.print("Enter choice (1 or 2): ");
     int choice = sc.nextInt();

     switch (choice) {
         case 1:
             System.out.print("Enter length of rectangle: ");
             double length = sc.nextDouble();
             System.out.print("Enter width of rectangle: ");
             double width = sc.nextDouble();

             Rectangle rect = new Rectangle(length, width);
             System.out.println("Rectangle Area = " + rect.calculateArea());
             break;

         case 2:
             System.out.print("Enter radius of circle: ");
             double radius = sc.nextDouble();

             Circle circle = new Circle(radius);
             System.out.println("Circle Area = " + circle.calculateArea());
             break;

         default:
             System.out.println("Invalid choice. Please select 1 or 2.");
     }

     sc.close();
 }
}




	
		

	


