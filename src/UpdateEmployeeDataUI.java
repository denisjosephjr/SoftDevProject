// filepath: src/UpdateEmployeeDataUI.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class UpdateEmployeeDataUI {
    private Connection conn;
    private Scanner scanner;

    public UpdateEmployeeDataUI(Connection conn, Scanner scanner) {
        this.conn = conn;
        this.scanner = scanner;
    }

    public void updateDataHandling() {
        System.out.println("\n--- Update Employee Data ---");
        System.out.println("Search employee by:");
        System.out.println("1. Employee ID");
        System.out.println("2. Last Name");
        System.out.println("3. Email");
        System.out.print("\nEnter choice: ");
        int searchType = scanner.nextInt();
        scanner.nextLine();

        int empId = -1;

        switch (searchType) {
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

    // Reuse EmpidSearch logic for searching by Employee ID
    private int searchByEmpId() {
        int id;
        try {
            System.out.print("Enter Employee ID: ");
            id = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a numeric value.");
            scanner.nextLine();
            return -1;
        }

        String sql = "SELECT empid, Fname, Lname, email FROM employees WHERE empid = ?";
        return findEmployee(sql, String.valueOf(id));
    }

    private int searchByLastName() {
        System.out.print("Enter Last Name: ");
        String lname = scanner.nextLine();

        String sql = "SELECT empid, Fname, Lname, email FROM employees WHERE Lname = ?";
        return findEmployee(sql, lname);
    }

    private int searchByEmail() {
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        String sql = "SELECT empid, Fname, Lname, email FROM employees WHERE email = ?";
        return findEmployee(sql, email);
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
        } catch (Exception e) {
            System.out.println("Error searching employee: " + e.getMessage());
            return -1;
        }
    }

    private void updateEmployeeField(int empId) {
        System.out.println("\nWhich field would you like to update?");
        System.out.println("1. First Name");
        System.out.println("2. Last Name");
        System.out.println("3. Email");
        System.out.println("4. Hire Date");
        System.out.println("5. SSN");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1: new FnameUpdate().update(conn, scanner, empId); break;
            case 2: new LnameUpdate().update(conn, scanner, empId); break;
            case 3: new EmailUpdate().update(conn, scanner, empId); break;
            case 4: new HireDateUpdate().update(conn, scanner, empId); break;
            case 5: new SSNUpdate().update(conn, scanner, empId); break;
            default: System.out.println("Invalid choice.");
        }
    }
}