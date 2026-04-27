import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class NameSearch implements ISearch {
    @Override
    public String getSearchTypeName() {
        return "Name";
    }
    
    @Override
    public void search(Connection conn, Scanner scanner) {
        try {
            System.out.print("Enter input: ");
            String userSearch = scanner.next();
            
            String sql = """
            SELECT 
                CONCAT(Fname, ' ', Lname) AS full_name,
                empid
            FROM Employees
            WHERE CONCAT(Fname, ' ', Lname) LIKE '%""" + userSearch + "%';";
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
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