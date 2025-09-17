package iringanyiza;

import java.util.Scanner;

public class abanyeshuli {
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int totalStudents = 0;
		int passes = 0;
		int fails = 0;
		System.out.println("=== Student Grading System ===");
		System.out.println("Enter marks between 0 and 100. Enter -1 to stop.\n");

		while (true) {
			System.out.print("Enter student marks: ");
			int marks = input.nextInt();

			// 2. Check for sentinel value
			if (marks == -1) {
				break;
			}
			if (marks < 0 || marks > 100) {
				System.out.println("Invalid marks! Please enter between 0 and 100.");
				continue;
			}
			totalStudents++;
			String grade;
			if (marks >= 80) {
				grade = "A";
			} else if (marks >= 70) {
				grade = "B";
			} else if (marks >= 60) {
				grade = "C";
			} else if (marks >= 50) {
				grade = "D";
			} else {
				grade = "F";
			}
			if (marks >= 50) {
				passes++;
			} else {
				fails++;
			}


			System.out.println("Grade: " + grade + "\n");
		}


		System.out.println("\n=== Summary Report ===");
		System.out.println("Total students: " + totalStudents);
		System.out.println("Passed: " + passes);
		System.out.println("Failed: " + fails);

		if (totalStudents > 0) {
			double passRate = ((double) passes / totalStudents) * 100;
			System.out.println(passRate);
		} else {
			System.out.println("No student data entered.");
		}
	
				
}
}


