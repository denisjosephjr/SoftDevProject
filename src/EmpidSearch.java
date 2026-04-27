import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class EmpidSearch implements ISearch {
    
    @Override
    public String getSearchTypeName() {
        return "empid";
    }
    
    @Override
    public void search(Connection conn, Scanner scanner) {
        int userSearch;
        try {
            System.out.print("Enter input: ");
            userSearch = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a numeric value.");
            scanner.next(); // Clear the invalid input
            return;
        }
        
        String sql = """
        SELECT 
            CONCAT(Fname, ' ', Lname) AS full_name,
            empid
        FROM Employees
        WHERE empid = ?;""";
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userSearch);
            ResultSet rs = pstmt.executeQuery();
            
            System.out.printf("%-20s %-6s%n",
                "Name", "ID");
            
            System.out.println("--------------------------------------");
            
            while (rs.next()) {
                System.out.printf(
                    "%-20s %-6d%n",
                    rs.getString("full_name"),
                    rs.getInt("empid")
                );
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}