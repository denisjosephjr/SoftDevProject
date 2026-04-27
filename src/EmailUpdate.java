// filepath: src/EmailUpdate.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class EmailUpdate implements IDataUpdate {
    @Override
    public String getUpdateName() {
        return "Email";
    }
    
    @Override
    public void update(Connection conn, Scanner scanner, int empId) {
        System.out.print("Enter new Email: ");
        String email = scanner.nextLine();
        
        String query = "UPDATE employees SET email = ? WHERE empid = ?";
        executeUpdate(conn, query, email, empId);
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