// Import for User Input
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

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

        // NOTE: Need error handling for invalid user input. (Loop)
        switch (report1Type) {
            case 1:
                this.employeeInformation();
                break;
            case 2:
                this.payByJobTitle();
                break;
            case 3:
                this.payByDivision();
                break;
            default:
                System.out.println("Invalid user input.");
        }
    }
    
    public void employeeInformation() {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet columns = dbmd.getColumns(null, null, "employees", "ssn");
            boolean ssnExists = columns.next(); // If columns.next() is true, the column exists

            String ssnSelection = ssnExists ? "e.ssn, " : "";
            
            String sql = "SELECT CONCAT(e.Fname, ' ', e.Lname) AS full_name, e.empid, " + ssnSelection + 
                            "p.pay_date, p.earnings, " +
                            "(p.fed_tax + p.fed_med + p.fed_SS + p.state_tax + p.retire_401k + p.health_care) AS total_deductions, " +
                            "(p.earnings - (p.fed_tax + p.fed_med + p.fed_SS + p.state_tax + p.retire_401k + p.health_care)) AS net_pay " +
                            "FROM employees e JOIN payroll p ON e.empid = p.empid ORDER BY e.empid, p.pay_date;";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (ssnExists) {
                System.out.printf("%-20s %-6s %-12s %-12s %-10s %-10s %-10s%n", "Name", "ID", "SSN", "Pay Date", "Earnings", "Deductions", "Net Pay");
            } else {
                System.out.printf("%-20s %-6s %-12s %-10s %-10s %-10s%n", "Name", "ID", "Pay Date", "Earnings", "Deductions", "Net Pay");
            }
            System.out.println("------------------------------------------------------------------------------------------");

            while (rs.next()) {
                if (ssnExists) {
                    System.out.printf("%-20s %-6d %-12s %-12s %-10.2f %-10.2f %-10.2f%n", 
                        rs.getString("full_name"), rs.getInt("empid"), rs.getString("ssn"), 
                        rs.getDate("pay_date"), rs.getDouble("earnings"), rs.getDouble("total_deductions"), rs.getDouble("net_pay"));
                } else {
                    System.out.printf("%-20s %-6d %-12s %-10.2f %-10.2f %-10.2f%n", 
                        rs.getString("full_name"), rs.getInt("empid"), 
                        rs.getDate("pay_date"), rs.getDouble("earnings"), rs.getDouble("total_deductions"), rs.getDouble("net_pay"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void payByJobTitle() {
        // Problem, month is hardcoded to April and doesn't auto update.
        int year, month;
        try {
            System.out.print("Enter year: ");
            year = scanner.nextInt();
            System.out.print("Enter month (1-12): ");
            month = scanner.nextInt();
            if (month < 1 || month > 12) {
                System.out.println("Invalid month. Please enter a number between 1 and 12.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter numeric values for year and month.");
            return;
        }
        String sql = """
        SELECT 
            jt.job_title,
            SUM(p.earnings) AS total_pay
        FROM employees e
        JOIN employee_job_titles ejt ON e.empid = ejt.empid
        JOIN job_titles jt ON ejt.job_title_id = jt.job_title_id
        JOIN payroll p ON e.empid = p.empid
        WHERE YEAR(p.pay_date) = ? AND MONTH(p.pay_date) = ?
        GROUP BY jt.job_title
        """;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, year);
            pstmt.setInt(2, month);

            ResultSet rs = pstmt.executeQuery();

            // 4. Remove all the 'format:', 's:', and 'x:' labels
            System.out.printf("%-20s %-15s%n", "Job Title", "Total Pay");
            System.out.println("---------------------------------------");

            while (rs.next()) {
                System.out.printf("%-20s %-15.2f%n", 
                rs.getString("job_title"), 
                rs.getDouble("total_pay")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void payByDivision() {
        // Potential Problem: this query assumes each employee belongs to exactly one division
        int year, month;
        try {
            System.out.print("Enter year: ");
            year = scanner.nextInt();
            System.out.print("Enter month (1-12): ");
            month = scanner.nextInt();
            if (month < 1 || month > 12) {
                System.out.println("Invalid month. Please enter a number between 1 and 12.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter numeric values for year and month.");
            return;
        }
        String sql = """
        SELECT 
            d.Name AS division_name,
            SUM(p.earnings) AS total_pay
        FROM employees e
            JOIN employee_division ed ON e.empid = ed.empid
            JOIN division d ON ed.div_ID = d.ID
            JOIN payroll p ON e.empid = p.empid
            WHERE YEAR(p.pay_date) = ?
            AND MONTH(p.pay_date) = ?
            GROUP BY d.Name
            ORDER BY total_pay DESC;
            """;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, year);
            pstmt.setInt(2, month);
            ResultSet rs = pstmt.executeQuery();

            System.out.printf("%-20s %-15s%n", "Division", "Total Pay");
            System.out.println("----------------------------------------");

            while (rs.next()) {
                System.out.printf(
                    "%-20s %-15.2f%n",
                    rs.getString("division_name"),
                    rs.getDouble("total_pay")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}