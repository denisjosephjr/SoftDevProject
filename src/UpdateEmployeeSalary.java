// Import for User Input
import java.util.Scanner;

// Imports for MySQL Integration
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateEmployeeSalary {
    private Connection conn;

    public UpdateEmployeeSalary(Connection conn) {
        this.conn = conn;
    }
}