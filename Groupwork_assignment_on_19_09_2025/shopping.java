package guhaha;

import java.util.Scanner;

public class shopping {

    // Make Product static so main can access it
    static class Product {
        private int productId;
        private String name;
        private double price;
        private int quantity;

        public Product(int productId, String name, double price, int quantity) {
            this.productId = productId;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public double calculateCost() {
            return price * quantity;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of products: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        Product[] products = new Product[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Product " + (i + 1));
            System.out.print("Product ID: ");
            int id = sc.nextInt();
            sc.nextLine(); 
            System.out.print("Product Name: ");
            String name = sc.nextLine();
            System.out.print("Price: ");
            double price = sc.nextDouble();
            System.out.print("Quantity: ");
            int quantity = sc.nextInt();
            sc.nextLine(); 

            products[i] = new Product(id, name, price, quantity);
        }

        double totalCost = 0;
        for (Product p : products) {
            totalCost += p.calculateCost();
        }

        boolean discountApplied = false;
        if (totalCost > 10000) {
            totalCost *= 0.9; 
            discountApplied = true;
        }

        System.out.println("\n=== Product Costs ===");
        for (Product p : products) {
            System.out.println(p.getName() + ": " + p.calculateCost());
        }

        System.out.println("\nTotal Bill: " + totalCost);
        if (discountApplied) {
            System.out.println("Discount of 10% applied.");
        } else {
            System.out.println("No discount applied.");
        }

        sc.close();
    }
}
