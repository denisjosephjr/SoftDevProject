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

    public UpdateEmployeeData(Connection conn) {
        this.conn = conn;
    }

    public void updateDataHandling() {
        System.out.println("updateDataHandling method called."); // Placeholder

        // Creates scanner to take user input
        Scanner scanner5 = new Scanner(System.in);

        // Takes report type
        System.out.print("\nEmpty option for user input: ");
        int report4Type = scanner5.nextInt();

        // Closes scanner
        scanner5.close();
    }

    // methods? Use search for Employee to update employee?
}