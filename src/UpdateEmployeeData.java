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
    /* 
    6 Columns = 6 methods. WHERE empid =, SSN =, Name =
    empid INT primary key,
    Fname VARCHAR(255),
    Lname VARCHAR(255),
    email VARCHAR(255),
    HireDate DATE,
    Salary DECIMAL

    Figure out update Queries.
    */
}