// Import for User Input
import java.util.Scanner;

// Imports for MySQL Integration
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateEmployeeSalary {
    private Connection conn;
    private Scanner scanner;

    public UpdateEmployeeSalary(Connection conn, Scanner scanner) {
        this.conn = conn;
        this.scanner = scanner;
    }

    public void updateSalaryHandling() {
        System.out.println("updateSalaryHandling method called.");

        // Takes report type
        System.out.print("\nEmpty option for user input: ");
        int report5Type = scanner.nextInt();

    }

    // 3 arguments: percentage increase, lower bound, and upperbound
    public void increaseSalary() {

    }    
}