package abakozi;

import java.util.Scanner;

public class Salary {

    
    abstract static class Employee {
        protected String name;

        public Employee(String name) {
            this.name = name;
        }

        public abstract double calculateSalary();

        public void display() {
            double salary = calculateSalary();
            System.out.println("Employee: " + name);
            System.out.println("Salary: " + salary);

            if (salary > 60000) {
                System.out.println("Status: High Earner");
            }
            System.out.println("======================");
        }
    }

    
    static class FullTimeEmployee extends Employee {
        private double baseSalary;

        public FullTimeEmployee(String name, double baseSalary) {
            super(name);
            this.baseSalary = baseSalary;
        }

        public double calculateSalary() {
            return baseSalary + (0.2 * baseSalary); 
        }
    }

    
    static class PartTimeEmployee extends Employee {
        private int hoursWorked;
        private double hourlyRate;

        public PartTimeEmployee(String name, int hoursWorked, double hourlyRate) {
            super(name);
            this.hoursWorked = hoursWorked;
            this.hourlyRate = hourlyRate;
        }

        public double calculateSalary() {
            return hoursWorked * hourlyRate;
        }
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of employees: ");
        int n = sc.nextInt();
        sc.nextLine();

        Employee[] employees = new Employee[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Employee " + (i + 1));
            System.out.print("choose (1 = FullTime, 2 = PartTime): ");
            int type = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter name: ");
            String name = sc.nextLine();

            if (type == 1) {
                System.out.print("Enter base salary: ");
                double baseSalary = sc.nextDouble();
                employees[i] = new FullTimeEmployee(name, baseSalary);
            } else if (type == 2) {
                System.out.print("Enter hours worked: ");
                int hoursWorked = sc.nextInt();
                System.out.print("Enter hourly rate: ");
                double hourlyRate = sc.nextDouble();
                employees[i] = new PartTimeEmployee(name, hoursWorked, hourlyRate);
            } else {
                System.out.println("Invalid employee type. Skip");
                i--; 
            }
        }

        double totalExpenditure = 0;
        System.out.println("\n=== Employee Salary Details ===");
        for (Employee emp : employees) {
            emp.display();
            totalExpenditure += emp.calculateSalary();
        }

        System.out.println("Total Salary Expenditure: " + totalExpenditure);

        sc.close();
    }
}
