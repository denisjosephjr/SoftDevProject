// filepath: src/PercentageIncrease.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class PercentageIncrease implements ISalaryUpdate {
    @Override
    public String getUpdateName() {
        return "Percentage Increase";
    }
    
    @Override
    public void update(Connection conn, Scanner scanner) {
        double percentage, lowerBound, upperBound;
        try {
            System.out.print("Enter percentage increase (e.g., 10 for 10%): ");
            percentage = scanner.nextDouble();
            System.out.print("Enter lower bound of salary range: ");
            lowerBound = scanner.nextDouble();
            System.out.print("Enter upper bound of salary range: ");
            upperBound = scanner.nextDouble();
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Invalid input. Please enter numeric values.");
            return;
        }
        
        String sql = """
        UPDATE employees
        SET Salary = Salary * (1 + ? / 100)
        WHERE Salary BETWEEN ? AND ?;
        """;
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, percentage);
            pstmt.setDouble(2, lowerBound);
            pstmt.setDouble(3, upperBound);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " rows updated.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}