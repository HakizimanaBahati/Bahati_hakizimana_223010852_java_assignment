package ibinyampande;

import java.util.Scanner;

public class shapes {


    abstract static class Shape {
        public abstract double area();
        public void display() {
            System.out.println("Area: " + area());
        }
    }

    static class Circle extends Shape {
        private double radius;
        public Circle(double radius) { this.radius = radius; }
      
        public double area() {
        	return Math.PI * radius * radius; 
        	}
    }

    static class Rectangle extends Shape {
        private double length, width;
        public Rectangle(double length, double width) {
            this.length = length;
            this.width = width;
        }
       
        public double area() { return length * width; }
    }

    static class Triangle extends Shape {
        private double base, height;
        public Triangle(double base, double height) {
            this.base = base;
            this.height = height;
        }
       
        public double area() { return 0.5 * base * height; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of shapes: ");
        int n = sc.nextInt();

        Shape[] shapes = new Shape[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nShape " + (i + 1));
            System.out.print("Enter type (1=Circle, 2=Rectangle, 3=Triangle): ");
            int type = sc.nextInt();

            switch (type) {
                case 1:
                    System.out.print("Enter radius of Circle: ");
                    double radius = sc.nextDouble();
                    shapes[i] = new Circle(radius);
                    break;

                case 2:
                    System.out.print("Enter length of Rectangle: ");
                    double length = sc.nextDouble();
                    System.out.print("Enter width of Rectangle: ");
                    double width = sc.nextDouble();
                    shapes[i] = new Rectangle(length, width);
                    break;

                case 3:
                    System.out.print("Enter base of Triangle: ");
                    double base = sc.nextDouble();
                    System.out.print("Enter height of Triangle: ");
                    double height = sc.nextDouble();
                    shapes[i] = new Triangle(base, height);
                    break;

                default:
                    System.out.println("Invalid type! Try again.");
                    i--; 
                    break;
            }
        }

       
        double totalArea = 0;
        System.out.println("\n=== Shape Areas ===");
        for (Shape sh : shapes) {
            sh.display();
            totalArea += sh.area();
        }

        System.out.println("Total Area of all shapes: " + totalArea);

        sc.close();
    }
}
