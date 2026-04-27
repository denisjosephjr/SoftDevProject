import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SSNSearch implements ISearch {
    @Override
    public String getSearchTypeName() {
        return "SSN";
    }
    
    @Override
    public void search(Connection conn, Scanner scanner) {
        try {
            System.out.print("Enter SSN (9 digits no dashes): ");
            String userInput = scanner.next();
            if (!userInput.matches("\\d{9}")) {
                System.out.println("Invalid input. SSN must be a 9-digit number.");
                return;
            }
            userInput = userInput.replaceAll("(\\d{3})(\\d{2})(\\d{4})", "$1-$2-$3");
            
            String sql = """
            SELECT 
                CONCAT(Fname, ' ', Lname) AS full_name,
                SSN,
                empid
            FROM Employees
            WHERE SSN = ?;""";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userInput);
            ResultSet rs = pstmt.executeQuery();
            
            System.out.printf("%-20s %-12s %-6s%n",
                "Name", "SSN", "ID");
            
            System.out.println("--------------------------------------");
            
            while (rs.next()) {
                System.out.printf(
                    "%-20s %-12s %-6d%n",
                    rs.getString("full_name"),
                    rs.getString("SSN"),
                    rs.getInt("empid")
                );
            }
            
        } catch (Exception e) {
            System.out.println("SSN not found. Make sure to add the ssn column to the employees table and input valid SSN data.");
        }
    }
}