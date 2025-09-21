package abanyeshuli;

import java.util.Scanner;

public class student {
	
	    private int id;
	    private String name;
	    private int[] marks = new int[3]; 

	    
	    public student(int id, String name, int[] marks) {
	        this.id = id;
	        this.name = name;
	        this.marks = marks;
	    }

	   
	    public double calculateAverage() {
	        int sum = 0;
	        for (int mark : marks) {
	            sum += mark;
	        }
	        return sum / 3;
	    }

	 
	    public String getGrade() {
	        double avg = calculateAverage();
	        if (avg >= 80) {
	            return "A";
	        } else if (avg >= 60) {
	            return "B";
	        } else if (avg >= 40) {
	            return "C";
	        } else {
	            return "Fail";
	        }
	    }

	   
	    public void display() {
	        System.out.println("ID: " + id);
	        System.out.println("Name: " + name);
	        System.out.print("Marks: ");
	        for (int m : marks) {
	            System.out.print(m + " ");
	        }
	        System.out.println("\nAverage: " + calculateAverage());
	        System.out.println("Grade: " + getGrade());
	        System.out.println("-----------------------------");
	    }

	    public double getAverage() {
	        return calculateAverage();
	    }


	
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);

		 student[] students = new student[3];

	        
	        for (int i = 0; i < 3; i++) {
	            System.out.println("\nEnter details for Student " + (i + 1) + ":");

	            System.out.print("Enter ID: ");
	            int id = sc.nextInt();
	            sc.nextLine(); 

	            System.out.print("Enter Name: ");
	            String name = sc.nextLine();

	            int[] marks = new int[3];
	            for (int j = 0; j < 3; j++) {
	                System.out.print("Enter marks for subject " + (j + 1) + ": ");
	                marks[j] = sc.nextInt();
	            }

	            students[i] = new student(id, name, marks);
	        }

	       
	        System.out.println("\n=== Student Details ===");
	        for (student s : students) {
	            s.display();
	        }

	       
	        double highestAvg = students[0].getAverage();
	        for (student s : students) {
	            if (s.getAverage() > highestAvg) {
	                highestAvg = s.getAverage();
	            }
	        }

	        System.out.println("Highest Average among students: " + highestAvg);

	        sc.close();
	    }


	}

