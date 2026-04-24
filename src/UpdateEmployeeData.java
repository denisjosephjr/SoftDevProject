// Import for User Input
import java.util.Scanner;

// Imports for MySQL Integration
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

// This class will up date an employee's data in the employee table.
// Not sure how to handle this one. Too many parameters to change.
public class UpdateEmployeeData {
    private Connection conn;
    private Scanner scanner;

    public UpdateEmployeeData(Connection conn, Scanner scanner) {
        this.conn = conn;
        this.scanner = scanner;
    }

    public void updateDataHandling() {
        System.out.println("updateDataHandling method called."); // Placeholder

        // Takes report type
        System.out.print("\nEmpty option for user input: ");
        int report4Type = scanner.nextInt();
    }

    // methods? Use search for Employee to update employee?
}