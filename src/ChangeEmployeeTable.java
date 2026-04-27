// Import for User Input
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class ChangeEmployeeTable {
    private Connection conn;
    private Scanner scanner;

    public ChangeEmployeeTable(Connection conn, Scanner scanner) {
        this.conn = conn;
        this.scanner = scanner;
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

    public void insertNewEmployee() {
        String firstName;
        String lastName;
        int empId;
        try {
            System.out.print("Enter first name: ");
            firstName = scanner.next();

            System.out.print("Enter last name: ");
            lastName = scanner.next();

            System.out.print("Enter employee ID (must be unique): ");
            empId = scanner.nextInt();
        } catch (Exception e) {
            scanner.next();
            System.out.println("Invalid input. Please enter the correct data types.");
            return;
        }


            String sql = """
            INSERT INTO Employees (empid, Fname, Lname) VALUES (%d, '%s', '%s');
            """.formatted(empId, firstName, lastName);
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            System.out.println("New employee inserted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeEmployee() {
        int empId;
        try {
            System.out.print("Enter employee ID to remove: ");
            empId = scanner.nextInt();
        } catch (Exception e) {
            scanner.next();
            System.out.println("Invalid input. Please enter a numeric employee ID.");
            return;
        }

        String sql = """
        DELETE FROM Employees WHERE empid = %d;
        """.formatted(empId);
        try {
            Statement stmt = conn.createStatement();
            int rowsAffected = stmt.executeUpdate(sql);
            if (rowsAffected > 0) {
                System.out.println("Employee removed successfully.");
            } else {
                System.out.println("No employee found with the given ID.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}