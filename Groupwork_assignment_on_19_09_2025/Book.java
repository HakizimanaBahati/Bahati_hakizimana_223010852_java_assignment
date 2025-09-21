package ibitabo;

import java.util.ArrayList;
import java.util.Scanner;


public class Book {


	private String title;
	private String author;
	private boolean available;

	
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
		this.available = true; 
	}

	
	public void borrowBook() {
		if (available) {
			available = false;
			System.out.println("You borrowed: " + title);
		} else {
			System.out.println("Sorry, " + title + " is already borrowed.");
		}
	}


	public void returnBook() {
		if (!available) {
			available = true;
			System.out.println("You returned: " + title);
		} else {
			System.out.println(title + " was not borrowed.");
		}
	}

	public boolean isAvailable() {
		return available;
	}
	 public String getTitle() {
	        return title;
	    }


	public void displayBook() {
		System.out.println("Title: " + title + " | Author: " + author +
				" | Available: " + (available ? "Yes" : "No"));
	}



	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        ArrayList<Book> library = new ArrayList<>();

        
        System.out.print("Enter number of books to add: ");
        int n = sc.nextInt();
        sc.nextLine(); 

        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for book " + (i + 1) + ":");
            System.out.print("Title: ");
            String title = sc.nextLine();
            System.out.print("Author: ");
            String author = sc.nextLine();
            library.add(new Book(title, author));
        }

        // Step 2: Borrow or return books
        boolean running = true;
        while (running) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Borrow a book");
            System.out.println("2. Return a book");
            System.out.println("3. Show available books");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter the title of the book to borrow: ");
                    String borrowTitle = sc.nextLine();
                    boolean foundBorrow = false;
                    for (Book b : library) {
                        if (b.getTitle().equalsIgnoreCase(borrowTitle)) {
                            b.borrowBook();
                            foundBorrow = true;
                            break;
                        }
                    }
                    if (!foundBorrow) {
                        System.out.println("Book not found!");
                    }
                    break;

                case 2:
                    System.out.print("Enter the title of the book to return: ");
                    String returnTitle = sc.nextLine();
                    boolean foundReturn = false;
                    for (Book b : library) {
                        if (b.getTitle().equalsIgnoreCase(returnTitle)) {
                            b.returnBook();
                            foundReturn = true;
                            break;
                        }
                    }
                    if (!foundReturn) {
                        System.out.println("Book not found!");
                    }
                    break;

                case 3:
                    System.out.println("\nAvailable books:");
                    for (Book b : library) {
                        if (b.isAvailable()) {
                            b.displayBook();
                        }
                    }
                    break;

                case 4:
                    running = false;
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }

        sc.close();
		

	}

}
