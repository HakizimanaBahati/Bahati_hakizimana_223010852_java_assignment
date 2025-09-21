package ibinyabiziga;

import java.util.Scanner;

public class Vehicle {

	protected String registrationNumber;
	protected int speed;

	public Vehicle(String registrationNumber, int speed) {
		this.registrationNumber = registrationNumber;
		this.speed = speed;
	}


	public void displayDetails() {
		System.out.println("Registration: " + registrationNumber + ", Speed: " + speed + " km/h");
	}
}


class Car extends Vehicle {
	private double fuelEfficiency;

	public Car(String registrationNumber, int speed, double fuelEfficiency) {
		super(registrationNumber, speed);
		this.fuelEfficiency = fuelEfficiency;
	}


	public void displayDetails() {
		System.out.println("Car -> Registration: " + registrationNumber +
				", Speed: " + speed + " km/h" +
				", Fuel Efficiency: " + fuelEfficiency + " km/l");
	}
}


class Bike extends Vehicle {
	private double fuelEfficiency; 

	public Bike(String registrationNumber, int speed, double fuelEfficiency) {
		super(registrationNumber, speed);
		this.fuelEfficiency = fuelEfficiency;
	}


	public void displayDetails() {
		System.out.println("Bike -> Registration: " + registrationNumber +
				", Speed: " + speed + " km/h" +
				", Fuel Efficiency: " + fuelEfficiency + " km/l");
	}
}


class Truck extends Vehicle {
	private int loadCapacity; 

	public Truck(String registrationNumber, int speed, int loadCapacity) {
		super(registrationNumber, speed);
		this.loadCapacity = loadCapacity;
	}


	public void displayDetails() {
		System.out.println("Truck: Registration: " + registrationNumber +
				", Speed: " + speed + " km/h" +
				", Load Capacity: " + loadCapacity + " tons");
	}



	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter number of vehicles: ");
		int n = sc.nextInt();
		sc.nextLine(); 

		Vehicle[] vehicles = new Vehicle[n];

		for (int i = 0; i < n; i++) {
			System.out.println("\nEnter details for vehicle " + (i + 1) + ":");
			System.out.println("Select type: 1. Car  2. Bike  3. Truck");
			int type = sc.nextInt();
			sc.nextLine(); 

			System.out.print("Registration Number: ");
			String regNo = sc.nextLine();

			System.out.print("Speed (km/h): ");
			int speed = sc.nextInt();

			switch (type) {
			case 1:
				System.out.print("Fuel Efficiency (km/l): ");
				double carFuel = sc.nextDouble();
				vehicles[i] = new Car(regNo, speed, carFuel);
				break;

			case 2: 
				System.out.print("Fuel Efficiency (km/l): ");
				double bikeFuel = sc.nextDouble();
				vehicles[i] = new Bike(regNo, speed, bikeFuel);
				break;

			case 3: 
				System.out.print("Load Capacity (tons): ");
				int load = sc.nextInt();
				vehicles[i] = new Truck(regNo, speed, load);
				break;

			default:
				System.out.println("Invalid choice. Adding as a generic Vehicle.");
				vehicles[i] = new Vehicle(regNo, speed);
			}
		}

		
		System.out.println("\n=== Vehicle Details ===");
		for (Vehicle v : vehicles) {
			v.displayDetails();
		}

		sc.close();




	}

}
