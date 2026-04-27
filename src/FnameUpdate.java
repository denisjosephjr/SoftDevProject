// filepath: src/FnameUpdate.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Updates employee first name.
 */
public class FnameUpdate implements IDataUpdate {
    
    @Override
    public String getUpdateName() {
        return "First Name";
    }
    
    @Override
    public void update(Connection conn, Scanner scanner, int empId) {
        System.out.print("Enter new First Name: ");
        String fname = scanner.nextLine();
        
        String query = "UPDATE employees SET Fname = ? WHERE empid = ?";
        executeUpdate(conn, query, fname, empId);
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