// filepath: src/SetSalary.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class SetSalary implements ISalaryUpdate {
    @Override
    public String getUpdateName() {
        return "Set Salary";
    }
    
    @Override
    public void update(Connection conn, Scanner scanner) {
        int empId;
        double newSalary;
        try {
            System.out.print("Enter employee ID: ");
            empId = scanner.nextInt();
            System.out.print("Enter new salary amount: ");
            newSalary = scanner.nextDouble();
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Invalid input. Please enter numeric values.");
            return;
        }
        
        try {
            String sql = "UPDATE employees SET Salary = ? WHERE empid = ?";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, newSalary);
            pstmt.setInt(2, empId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee salary updated successfully.");
            } else {
                System.out.println("Employee ID not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}