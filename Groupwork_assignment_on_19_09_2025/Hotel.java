package amacumbi;

import java.util.Scanner;

public class Hotel {
    
    static class Room {
        private int roomNumber;
        private String type;
        private double pricePerNight;

        public Room(int roomNumber, String type, double pricePerNight) {
            this.roomNumber = roomNumber;
            this.type = type;
            this.pricePerNight = pricePerNight;
        }

        public double calculateCost(int nights) {
            double total = pricePerNight * nights;
            if (nights > 5) {
                total = total - (total * 0.15); 
            }
            return total;
        }

        public int getRoomNumber() {
            return roomNumber;
        }

        public String getType() {
            return type;
        }

        public double getPricePerNight() {
            return pricePerNight;
        }

        
        public void displayRoom() {
            System.out.println("Room No: " + roomNumber +
                               " | Type: " + type +
                               " | Price per Night: " + pricePerNight);
        }
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of rooms to add: ");
        int n = sc.nextInt();
        sc.nextLine(); 

        Room[] rooms = new Room[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Room " + (i + 1) + ":");
            System.out.print("Room Number: ");
            int roomNumber = sc.nextInt();
            sc.nextLine(); 

            System.out.print("Type (Standard/Deluxe/Suite): ");
            String type = sc.nextLine();

            System.out.print("Price per Night: ");
            double price = sc.nextDouble();

            rooms[i] = new Room(roomNumber, type, price);
        }

        System.out.println("\n=== Available Rooms ===");
        for (Room r : rooms) {
            r.displayRoom(); // now works
        }

        System.out.print("\nEnter the room number you want to book: ");
        int bookRoomNo = sc.nextInt();

        System.out.print("Enter number of nights: ");
        int nights = sc.nextInt();

        boolean found = false;
        for (Room r : rooms) {
            if (r.getRoomNumber() == bookRoomNo) {
                double bill = r.calculateCost(nights);
                System.out.println("\nBooking Successful!");
                System.out.println("Room Type: " + r.getType());
                System.out.println("Nights Stayed: " + nights);
                System.out.println("Price per Night: " + r.getPricePerNight());
                System.out.println("Total Bill: " + bill);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Room not found!");
        }

        sc.close();
    }
}
