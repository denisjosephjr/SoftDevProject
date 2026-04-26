// Import for User Input
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

// Three methods needed: searchName, searchSSN, searchEmpid
public class SearchForEmployee {

    private Connection conn;
    private Scanner scanner;

    // Constructor with connection as an attribute
    public SearchForEmployee(Connection conn, Scanner scanner) {
        this.conn = conn;
        this.scanner = scanner;
    }

    public void searchHandling() {
        System.out.println("How would you like to search for your Employee?");

        System.out.println("1. Name");
        System.out.println("2. SSN");
        System.out.println("3. empid");

        // Takes report type
        System.out.print("\nPlease enter a number for a corresponding option: ");
        int report3Type = scanner.nextInt();

        // NOTE: This could have been a switch case statement.
        // NOTE: Need error handling for invalid user input. (Loop)
        if (report3Type == 1) {
            this.searchName();

        } else if (report3Type == 2) {
            this.searchSSN();

        } else if (report3Type == 3) {
            this.searchEmpid();

        }
    }

    public void searchName() {
        try {
            System.out.print("Enter input: ");
            String userSearch = scanner.next();

            String sql = """
            SELECT 
                CONCAT(Fname, ' ', Lname) AS full_name,
                empid
            FROM Employees
            WHERE CONCAT(Fname, ' ', Lname) LIKE '%""" + userSearch + "%';";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.printf("%-20s %-6s%n",
        "Name", "ID");

            System.out.println("--------------------------------------");

            while (rs.next()) {
                System.out.printf(
                    "%-20s %-6d%n",
                    rs.getString("full_name"),
                    rs.getInt("empid")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchSSN() {
        try {
            System.out.print("Enter SSN (XXX-XX-XXXX): ");
            String userSearch = scanner.next();
            String sql = """
            SELECT 
                CONCAT(Fname, ' ', Lname) AS full_name,
                SSN,
                empid
            FROM Employees
            WHERE SSN = '""" + userSearch + "';";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.printf("%-20s %-12s %-6s%n",
        "Name", "SSN", "ID");

            System.out.println("--------------------------------------");

            while (rs.next()) {
                System.out.printf(
                    "%-20s %-12s %-6d%n",
                    rs.getString("full_name"),
                    rs.getString("SSN"),
                    rs.getInt("empid")
                );
            }

        } catch (Exception e) {
            System.out.println("SSN not found. Make sure to add the ssn column to the employees table and input valid SSN data.");
        }
    }

    public void searchEmpid() {
        try {
            System.out.print("Enter input: ");
            int userSearch = scanner.nextInt();

            String sql = """
            SELECT 
                CONCAT(Fname, ' ', Lname) AS full_name,
                empid
            FROM Employees
            WHERE empid = """ + userSearch + ";";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.printf("%-20s %-6s%n",
        "Name", "ID");

            System.out.println("--------------------------------------");

            while (rs.next()) {
                System.out.printf(
                    "%-20s %-6d%n",
                    rs.getString("full_name"),
                    rs.getInt("empid")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}