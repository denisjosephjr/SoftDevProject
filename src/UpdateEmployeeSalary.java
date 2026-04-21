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

    public void updateSalaryHandling() {
        System.out.println("updateSalaryHandling method called.");

        // Creates scanner to take user input
        Scanner scanner6 = new Scanner(System.in);

        // Takes report type
        System.out.print("\nEmpty option for user input: ");
        int report5Type = scanner6.nextInt();

        // Closes scanner
        scanner6.close();
    }

    // 3 arguments: percentage increase, lower bound, and upperbound
    public void increaseSalary() {

    }    
}