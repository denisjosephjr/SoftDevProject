// filepath: src/PayByDivisionReport.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class PayByDivisionReport implements IReport {
    @Override
    public String getReportName() {
        return "Total pay for month by Division";
    }
    
    @Override
    public void generate(Connection conn, Scanner scanner) {
        int year, month;
        try {
            System.out.print("Enter year: ");
            year = scanner.nextInt();
            System.out.print("Enter month (1-12): ");
            month = scanner.nextInt();
            if (month < 1 || month > 12) {
                System.out.println("Invalid month. Please enter a number between 1 and 12.");
                return;
            }
        } catch (Exception e) {
            scanner.nextLine(); 
            System.out.println("Invalid input. Please enter numeric values for year and month.");
            return;
        }
        
        String sql = """
        SELECT 
            d.Name AS division_name,
            SUM(p.earnings) AS total_pay
        FROM employees e
            JOIN employee_division ed ON e.empid = ed.empid
            JOIN division d ON ed.div_ID = d.ID
            JOIN payroll p ON e.empid = p.empid
            WHERE YEAR(p.pay_date) = ?
            AND MONTH(p.pay_date) = ?
            GROUP BY d.Name
            ORDER BY total_pay DESC;
            """;
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, year);
            pstmt.setInt(2, month);
            ResultSet rs = pstmt.executeQuery();
            
            System.out.printf("%-20s %-15s%n", "Division", "Total Pay");
            System.out.println("----------------------------------------");
            
            while (rs.next()) {
                System.out.printf(
                    "%-20s %-15.2f%n",
                    rs.getString("division_name"),
                    rs.getDouble("total_pay")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}