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
            // I need pay statement history.
            String sql = """
            SELECT 
                CONCAT(e.Fname, ' ', e.Lname) AS full_name,
                e.empid,
                p.pay_date,
                p.earnings,

                (p.fed_tax + p.fed_med + p.fed_SS + p.state_tax + p.retire_401k + p.health_care) AS total_deductions,

                (p.earnings - (p.fed_tax + p.fed_med + p.fed_SS + p.state_tax + p.retire_401k + p.health_care)) AS net_pay
            FROM employees e
            JOIN payroll p 
            ON e.empid = p.empid
            ORDER BY e.empid, p.pay_date;
            """;

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.printf("%-20s %-6s %-12s %-10s %-10s %-10s%n",
        "Name", "ID", "Pay Date", "Earnings", "Deductions", "Net Pay");

            System.out.println("---------------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf(
                    "%-20s %-6d %-12s %-10.2f %-10.2f %-10.2f%n",
                    rs.getString("full_name"),
                    rs.getInt("empid"),
                    rs.getDate("pay_date"),
                    rs.getDouble("earnings"),
                    rs.getDouble("total_deductions"),
                    rs.getDouble("net_pay")
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