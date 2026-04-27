import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class EmployeeInformationReport implements IReport {
    @Override
    public String getReportName() {
        return "Full-time employee information with pay statement history";
    }
    
    @Override
    public void generate(Connection conn, Scanner scanner) {
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet columns = dbmd.getColumns(null, null, "employees", "ssn");
            boolean ssnExists = columns.next();
            
            String ssnSelection = ssnExists ? "e.ssn, " : "";
            
            String sql = "SELECT CONCAT(e.Fname, ' ', e.Lname) AS full_name, e.empid, " + ssnSelection + 
                            "p.pay_date, p.earnings, " +
                            "(p.fed_tax + p.fed_med + p.fed_SS + p.state_tax + p.retire_401k + p.health_care) AS total_deductions, " +
                            "(p.earnings - (p.fed_tax + p.fed_med + p.fed_SS + p.state_tax + p.retire_401k + p.health_care)) AS net_pay " +
                            "FROM employees e JOIN payroll p ON e.empid = p.empid ORDER BY e.empid, p.pay_date;";
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            if (ssnExists) {
                System.out.printf("%-20s %-6s %-12s %-12s %-10s %-10s %-10s%n", "Name", "ID", "SSN", "Pay Date", "Earnings", "Deductions", "Net Pay");
            } else {
                System.out.printf("%-20s %-6s %-12s %-10s %-10s %-10s%n", "Name", "ID", "Pay Date", "Earnings", "Deductions", "Net Pay");
            }
            System.out.println("------------------------------------------------------------------------------------------");
            
            while (rs.next()) {
                if (ssnExists) {
                    System.out.printf("%-20s %-6d %-12s %-12s %-10.2f %-10.2f %-10.2f%n", 
                        rs.getString("full_name"), rs.getInt("empid"), rs.getString("ssn"), 
                        rs.getDate("pay_date"), rs.getDouble("earnings"), rs.getDouble("total_deductions"), rs.getDouble("net_pay"));
                } else {
                    System.out.printf("%-20s %-6d %-12s %-10.2f %-10.2f %-10.2f%n", 
                        rs.getString("full_name"), rs.getInt("empid"), 
                        rs.getDate("pay_date"), rs.getDouble("earnings"), rs.getDouble("total_deductions"), rs.getDouble("net_pay"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}