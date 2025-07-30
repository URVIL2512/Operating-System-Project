import java.util.Scanner;

public class ConsoleMain {
    public static void main(String[] args) {
        System.out.println("=== Operating System Project ===");
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("OS: " + System.getProperty("os.name"));
        System.out.println();
        
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Available Algorithms:");
            System.out.println("1. Priority Preemptive Scheduling");
            System.out.println("2. Readers-Writers Problem with Semaphore");
            System.out.println("3. FCFS Disk Scheduling Algorithm");
            System.out.println("4. LRU Page Replacement Algorithm");
            System.out.println("5. Exit");
            System.out.print("\nSelect an option (1-5): ");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.println("\n=== Priority Preemptive Scheduling ===");
                    System.out.println("This algorithm schedules processes based on priority.");
                    System.out.println("Higher priority processes are executed first.");
                    System.out.println("Preemptive means a running process can be interrupted.");
                    break;
                    
                case 2:
                    System.out.println("\n=== Readers-Writers Problem ===");
                    System.out.println("This is a classic synchronization problem.");
                    System.out.println("Multiple readers can read simultaneously.");
                    System.out.println("Only one writer can write at a time.");
                    break;
                    
                case 3:
                    System.out.println("\n=== FCFS Disk Scheduling ===");
                    System.out.println("First Come First Serve disk scheduling.");
                    System.out.println("Requests are processed in order of arrival.");
                    System.out.println("Simple but may not be optimal for performance.");
                    break;
                    
                case 4:
                    System.out.println("\n=== LRU Page Replacement ===");
                    System.out.println("Least Recently Used page replacement algorithm.");
                    System.out.println("Replaces the page that hasn't been used for longest time.");
                    System.out.println("Effective for memory management.");
                    break;
                    
                case 5:
                    System.out.println("\nThank you for using our Operating System Project!");
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Invalid option. Please select 1-5.");
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine(); // Clear buffer
            scanner.nextLine(); // Wait for Enter
            System.out.println("\n" + "=".repeat(50) + "\n");
        }
    }
} 