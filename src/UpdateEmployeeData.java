import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UpdateEmployeeData {
    private Connection conn;
    private Scanner scanner;
    private List<IDataUpdate> updates;

    public UpdateEmployeeData(Connection conn, Scanner scanner) {
        this.conn = conn;
        this.scanner = scanner;
        this.updates = new ArrayList<>();
        
        // Register available update types
        updates.add(new FnameUpdate());
        updates.add(new LnameUpdate());
        updates.add(new EmailUpdate());
        updates.add(new HireDateUpdate());
        updates.add(new SSNUpdate());
    }

    public void updateDataHandling() {
        System.out.println("updateDataHandling method called.");

        System.out.println("\n--- Update Employee Data ---");
        System.out.println("Search employee by:");
        System.out.println("1. Employee ID");
        System.out.println("2. Last Name");
        System.out.println("3. Email");
        System.out.print("\nEnter choice: ");
        int report4Type = scanner.nextInt();
        scanner.nextLine();

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
        int id;
        try {
            System.out.print("Enter Employee ID: ");
            id = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a numeric value.");
            scanner.nextLine();
            return -1;
        }
        
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
        
        for (int i = 0; i < updates.size(); i++) {
            System.out.println((i + 1) + ". " + updates.get(i).getUpdateName());
        }
        
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice >= 1 && choice <= updates.size()) {
            updates.get(choice - 1).update(conn, scanner, empId);
        } else {
            System.out.println("Invalid choice.");
        }
    }
}
