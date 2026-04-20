// Import for User Input
import java.util.Scanner;

// Imports for MySQL Integration
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

// This class will up date an employee's data in the employee table.
public class UpdateEmployeeData {
    private Connection conn;

    public UpdateEmployeeData(Connection conn) {
        this.conn = conn;
    }
}