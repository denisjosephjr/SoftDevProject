// Import for User Input
import java.util.Scanner;

// Imports for MySQL Integration
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

// Three methods needed: searchName, searchSSN, searchEmpid
public class SearchForEmployee {

    private Connection conn;

    // Constructor with connection as an attribute
    public SearchForEmployee(Connection conn) {
        this.conn = conn;
    }

    public void searchHandling() {
        System.out.println("How would you like to search for your Employee?");

        System.out.println("1. Name");
        System.out.println("2. SSN");
        System.out.println("3. empid");

        // Creates scanner to take user input
        Scanner scanner4 = new Scanner(System.in);

        // Takes report type
        System.out.print("\nPlease enter a number for a corresponding option: ");
        int report3Type = scanner4.nextInt();

        // NOTE: This could have been a switch case statement.
        // NOTE: Need error handling for invalid user input. (Loop)
        if (report3Type == 1) {
            this.searchName();

        } else if (report3Type == 2) {
            this.searchSSN();

        } else if (report3Type == 3) {
            this.searchEmpid();

        }

        // Closes scanner
        scanner4.close();
    }

    public void searchName() {
        System.out.println("searchName method call."); // Placeholder
    }

    public void searchSSN() {
        System.out.println("searchSSN method call."); // Placeholder
    }

    public void searchEmpid() {
        System.out.println("searchEmpid method call."); // Placeholder
    }

}