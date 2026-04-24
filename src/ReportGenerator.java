// Import for User Input
import java.util.Scanner;

// Imports for MySQL Integration
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

// This class will have 4 methods, 3 of which will reflect the 3 types of reports to be generated.
// Consider making this an interface that attaches to 3 other classes for the  3 types of reports.
public class ReportGenerator {
    
    private Connection conn;
    private Scanner scanner; 

    // Constructor with connection as an attribute
    public ReportGenerator(Connection conn, Scanner scanner) {
        this.conn = conn;
        this.scanner = scanner;
    }

    // Method that handles the report interaction
    public void reportHandling() {

        System.out.println("\nWhat kind of report would you like?");

        System.out.println("1. Full-time employee information with pay statement history");
        System.out.println("2. Total pay for month by job title");
        System.out.println("3. Total pay for month by Division");

        // Takes report type
        System.out.print("\nPlease enter a number for a corresponding option: ");
        int report1Type = scanner.nextInt();

        // NOTE: This could have been a switch case statement.
        // NOTE: Need error handling for invalid user input. (Loop)
        if (report1Type == 1) {
            this.employeeInformation();

        } else if (report1Type == 2) {
            this.payByJobTitle();

        } else if (report1Type == 3) {
            this.payByDivision();

        }
    }
    
    public void employeeInformation() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

            // TODO: Make spacing uniform in terminal
            while (rs.next()) { // while there is a next row
                // print the string with the column label Fname
                System.out.println(
                    rs.getString("Fname") + "  " +
                    rs.getString("Lname") + "  " +
                    rs.getString("email") + "  " +
                    rs.getDate("HireDate") + "  " +
                    rs.getDouble("Salary")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void payByJobTitle() {
        System.out.println("payByJobTitle method call."); // placeholder
    }

    public void payByDivision() {
        System.out.println("payByDivision method call."); // placeholder
    }
}