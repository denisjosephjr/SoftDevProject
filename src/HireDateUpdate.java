// filepath: src/HireDateUpdate.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class HireDateUpdate implements IDataUpdate {
    @Override
    public String getUpdateName() {
        return "Hire Date";
    }
    
    @Override
    public void update(Connection conn, Scanner scanner, int empId) {
        System.out.print("Enter new Hire Date (YYYYMMDD): ");
        String date = scanner.nextLine();
        
        if (!date.matches("\\d{8}")) {
            System.out.println("Invalid input. Date must be in YYYYMMDD format.");
            return;
        }
        date = date.replaceAll("(\\d{4})(\\d{2})(\\d{2})", "$1-$2-$3");
        
        String query = "UPDATE employees SET HireDate = ? WHERE empid = ?";
        executeUpdate(conn, query, date, empId);
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