// filepath: src/PayByJobTitleReport.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class PayByJobTitleReport implements IReport {
    
    @Override
    public String getReportName() {
        return "Total pay for month by job title";
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
            jt.job_title,
            SUM(p.earnings) AS total_pay
        FROM employees e
        JOIN employee_job_titles ejt ON e.empid = ejt.empid
        JOIN job_titles jt ON ejt.job_title_id = jt.job_title_id
        JOIN payroll p ON e.empid = p.empid
        WHERE YEAR(p.pay_date) = ? AND MONTH(p.pay_date) = ?
        GROUP BY jt.job_title
        """;
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, year);
            pstmt.setInt(2, month);
            
            ResultSet rs = pstmt.executeQuery();
            
            System.out.printf("%-20s %-15s%n", "Job Title", "Total Pay");
            System.out.println("---------------------------------------");
            
            while (rs.next()) {
                System.out.printf("%-20s %-15.2f%n", 
                    rs.getString("job_title"), 
                    rs.getDouble("total_pay")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}