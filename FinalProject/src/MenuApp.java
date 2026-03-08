import java.util.Scanner;

public class MenuApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Display the menu
            System.out.println("--- Main Menu ---");
            System.out.println("1. Option A");
            System.out.println("2. Option B");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            // Get user input
            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume the invalid input
                continue; // Restart the loop
            }

            // Process user choice using a switch statement
            switch (choice) {
                case 1:
                    System.out.println("You selected Option A.");
                    // Add functionality for Option A here
                    break;
                case 2:
                    System.out.println("You selected Option B.");
                    // Add functionality for Option B here
                    break;
                case 3:
                    System.out.println("Exiting the application. Goodbye!");
                    running = false; // Set loop control variable to false to exit
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option (1-3).");
            }
            System.out.println("\n"); // Add a newline for better readability
        }
        scanner.close(); // Close the scanner when done
    }
}
