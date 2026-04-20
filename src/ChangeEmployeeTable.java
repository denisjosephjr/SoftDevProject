// Import for User Input
import java.util.Scanner;

// Imports for MySQL Integration
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ChangeEmployeeTable {
    private Connection conn;

    // Constructor with connection as an attribute
    public ChangeEmployeeTable(Connection conn) {
        this.conn = conn;
    }
}