// filepath: src/SSNUpdate.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SSNUpdate implements IDataUpdate {
    
    @Override
    public String getUpdateName() {
        return "SSN";
    }
    
    @Override
    public void update(Connection conn, Scanner scanner, int empId) {
        try {
            System.out.print("Enter new SSN (9 digits no dashes): ");
            String ssn = scanner.next();
            if (!ssn.matches("\\d{9}")) {
                System.out.println("Invalid input. SSN must be a 9-digit number.");
                return;
            }
            ssn = ssn.replaceAll("(\\d{3})(\\d{2})(\\d{4})", "$1-$2-$3");
            
            String query = "UPDATE employees SET ssn = ? WHERE empid = ?";
            executeUpdate(conn, query, ssn, empId);
        } catch (Exception e) {
            System.out.println("SSN column does not exist. Please add the SSN column to the employees table before updating SSN data.");
        }
    }
    
    private void executeUpdate(Connection conn, String query, String value, int empId) {
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, value);
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