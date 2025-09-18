//1. Bahati HAKIZIMANA 		223010852
//2. Olivier HABINGABIRE	223007847
//3. Federance TUYIZERE 	223003521
//4. Christine KIRABO		223008741
//5. Dieudonne IRADUKUNDA	223015105
//6. Felicite NYIRANZIZA	223005134
//7. Hidaya UMUTONI			222020222

package com.case3;

import java.util.Scanner;

//Rectangle class
class Rectangle {
 double length;
 double width;

 // Constructor
 Rectangle(double length, double width) {
     this.length = length;
     this.width = width;
 }

 // Method to calculate area
 double calculateArea() {
     return length * width;
 }
}

public class RectangleAreas {
 public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);

     // Array to hold 3 rectangles
     Rectangle[] rectangles = new Rectangle[3];

     // Input dimensions for 3 rectangles
     for (int i = 0; i < 3; i++) {
         System.out.println("Enter length of rectangle " + (i + 1) + ": ");
         double length = sc.nextDouble();

         System.out.println("Enter width of rectangle " + (i + 1) + ": ");
         double width = sc.nextDouble();

         // Store rectangle object in the array
         rectangles[i] = new Rectangle(length, width);
     }

     // Print areas
     System.out.println("\nAreas of the rectangles:");
     for (int i = 0; i < 3; i++) {
         System.out.println("Rectangle " + (i + 1) + " area: " + rectangles[i].calculateArea());
     }

     sc.close();
 }
}
