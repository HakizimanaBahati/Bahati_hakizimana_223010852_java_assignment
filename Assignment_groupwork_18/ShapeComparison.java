package com.case5;

import java.util.Scanner;


class Rectangle {
 double length, width;
 Rectangle(double length, double width) { this.length = length; this.width = width; }
 double calculateArea() { return length * width; }
}

class Circle {
 double radius;
 Circle(double radius) { this.radius = radius; }
 double calculateArea() { return Math.PI * radius * radius; }
}

public class ShapeComparison {
 public static void main(String[] args) {
     java.util.Scanner sc = new java.util.Scanner(System.in);

     System.out.print("Enter rectangle length: ");
     double length = sc.nextDouble();
     System.out.print("Enter rectangle width: ");
     double width = sc.nextDouble();

     System.out.print("Enter circle radius: ");
     double radius = sc.nextDouble();

     Rectangle rectangle = new Rectangle(length, width);
     Circle circle = new Circle(radius);

     double rectArea = rectangle.calculateArea();
     double circleArea = circle.calculateArea();

     System.out.println("\nRectangle Area = " + rectArea);
     System.out.println("Circle Area = " + circleArea);

     if (rectArea > circleArea) {
         System.out.println("Rectangle has the larger area.");
     } else if (circleArea > rectArea) {
         System.out.println("Circle has the larger area.");
     } else {
         System.out.println("Both shapes have equal areas.");
     }

     sc.close();
 }
}

