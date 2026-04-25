// Import for User Input
import java.util.Scanner;

// Imports for MySQL Integration
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
// This class will up date an employee's data in the employee table.
public class UpdateEmployeeData {
    private Connection conn;
    private Scanner scanner;

    public UpdateEmployeeData(Connection conn, Scanner scanner) {
        this.conn = conn;
        this.scanner = scanner;
    }

    public void updateDataHandling() {
        System.out.println("updateDataHandling method called.");

        System.out.println("\n--- Update Employee Data ---");
        System.out.println("Search employee by:");
        System.out.println("1. Employee ID");
        System.out.println("2. Last Name");
        System.out.println("3. Email");
        System.out.print("\nEmpty option for user input: ");
        int report4Type = scanner.nextInt();
        scanner.nextLine(); // consume newline

        int empId = -1;

        switch (report4Type) {
            case 1:
                empId = searchByEmpId();
                break;
            case 2:
                empId = searchByLastName();
                break;
            case 3:
                empId = searchByEmail();
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }

        if (empId == -1) {
            System.out.println("Employee not found.");
            return;
        }

        updateEmployeeField(empId);
    }

    // --- Search Methods ---

    private int searchByEmpId() {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        String query = "SELECT empid, Fname, Lname, email FROM employees WHERE empid = ?";
        return findEmployee(query, String.valueOf(id));
    }

    private int searchByLastName() {
        System.out.print("Enter Last Name: ");
        String lname = scanner.nextLine();

        String query = "SELECT empid, Fname, Lname, email FROM employees WHERE Lname = ?";
        return findEmployee(query, lname);
    }

    private int searchByEmail() {
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        String query = "SELECT empid, Fname, Lname, email FROM employees WHERE email = ?";
        return findEmployee(query, email);
    }

    private int findEmployee(String query, String param) {
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, param);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int empId = rs.getInt("empid");
                System.out.println("\nEmployee Found:");
                System.out.println("  ID    : " + empId);
                System.out.println("  Name  : " + rs.getString("Fname") + " " + rs.getString("Lname"));
                System.out.println("  Email : " + rs.getString("email"));
                return empId;
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("Error searching employee: " + e.getMessage());
            return -1;
        }
    }

    // --- Update Methods ---

    private void updateEmployeeField(int empId) {
        System.out.println("\nWhich field would you like to update?");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Email");
        System.out.println("4. Hire Date");
        System.out.println("5. Salary");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: updateFname(empId);    break;
            case 2: updateLname(empId);    break;
            case 3: updateEmail(empId);    break;
            case 4: updateHireDate(empId); break;
            case 5: updateSalary(empId);   break;
            default: System.out.println("Invalid choice.");
        }
    }

    private void updateFname(int empId) {
        System.out.print("Enter new First Name: ");
        String fname = scanner.nextLine();
        runUpdate("UPDATE employees SET Fname = ? WHERE empid = ?", fname, empId);
    }

    private void updateLname(int empId) {
        System.out.print("Enter new Last Name: ");
        String lname = scanner.nextLine();
        runUpdate("UPDATE employees SET Lname = ? WHERE empid = ?", lname, empId);
    }

    private void updateEmail(int empId) {
        System.out.print("Enter new Email: ");
        String email = scanner.nextLine();
        runUpdate("UPDATE employees SET email = ? WHERE empid = ?", email, empId);
    }

    private void updateHireDate(int empId) {
        System.out.print("Enter new Hire Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        runUpdate("UPDATE employees SET HireDate = ? WHERE empid = ?", date, empId);
    }

    private void updateSalary(int empId) {
        System.out.print("Enter new Salary: ");
        String salary = scanner.nextLine();
        runUpdate("UPDATE employees SET Salary = ? WHERE empid = ?", salary, empId);
    }

    private void runUpdate(String query, String newValue, int empId) {
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, newValue);
            ps.setInt(2, empId);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Employee record updated successfully.");
            } else {
                System.out.println("Update failed. No records changed.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating employee: " + e.getMessage());
        }
    }
}
