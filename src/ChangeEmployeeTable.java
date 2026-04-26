// Import for User Input
import java.util.Scanner;

// Imports for MySQL Integration
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ChangeEmployeeTable {

    private Connection conn;
    private Scanner scanner;

    // Constructor with connection as an attribute
    public ChangeEmployeeTable(Connection conn, Scanner scanner) {
        this.conn = conn;
        this.scanner = scanner;
    }

    public void changeHandling() {

        System.out.println("\nWhat would you like to change about the Employee Table?");

        // Only one option.. (Should there be more?)
        System.out.println("1. Add SSN Column");
        System.out.println("2. Delete SSN Column");

        // Takes report type
        System.out.print("\nPlease enter a number for a corresponding option: ");
        int report2Type = scanner.nextInt();

        // NOTE: This could have been a switch case statement.
        // NOTE: Need error handling for invalid user input. (Loop)
        if (report2Type == 1) {
            this.addSSNColumn();

        } else if (report2Type == 2){
            this.deleteSSNColumn();
        } else {
            System.out.println("Invalid user input.");
        }
    }

    public void addSSNColumn() {
        try {
            String sql = """
            ALTER TABLE Employees ADD SSN VARCHAR(11);
            """;

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            System.out.println("SSN column added successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteSSNColumn() {
        try {
            String sql = """
            ALTER TABLE Employees DROP COLUMN SSN;
            """;

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            System.out.println("SSN column deleted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}