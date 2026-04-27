// Import for User Input
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class ChangeEmployeeTable {
    private Connection conn;
    private Scanner scanner;

    public ChangeEmployeeTable(Connection conn, Scanner scanner) {
        this.conn = conn;
        this.scanner = scanner;
    }

    public void addSSNColumn() {
        try {
            String sql = """
            ALTER TABLE Employees ADD SSN VARCHAR(11);
            """;

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            System.out.println("SSN column added successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteSSNColumn() {
        try {
            String sql = """
            ALTER TABLE Employees DROP COLUMN SSN;
            """;

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            System.out.println("SSN column deleted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}