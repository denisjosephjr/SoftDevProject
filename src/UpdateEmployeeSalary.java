// Import for User Input
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class UpdateEmployeeSalary {
    private Connection conn;
    private Scanner scanner;

    public UpdateEmployeeSalary(Connection conn, Scanner scanner) {
        this.conn = conn;
        this.scanner = scanner;
    }

    public void updateSalaryHandling() {
        System.out.print("\nHow would you like to update employee salary? (1. Percentage Increase, 2. Set Salary): ");
        int userChoice = scanner.nextInt();
        switch (userChoice) {
            case 1:
                this.increaseSalary();
                break;
            case 2:
                this.setEmployeeSalary();
                break;
            default:
                System.out.println("Invalid option.");
        }

    }

    // 3 arguments: percentage increase, lower bound, and upperbound
    public void increaseSalary() {
        double percentage, lowerBound, upperBound;
        try {
            System.out.print("Enter percentage increase (e.g., 10 for 10%): ");
            percentage = scanner.nextDouble();
            System.out.print("Enter lower bound of salary range: ");
            lowerBound = scanner.nextDouble();
            System.out.print("Enter upper bound of salary range: ");
            upperBound = scanner.nextDouble();
        } catch (Exception e) {
            scanner.nextLine(); // Clear the invalid input
            System.out.println("Invalid input. Please enter numeric values.");
            return;
        }

        String sql = """
        UPDATE employees
        SET Salary = Salary * (1 + ? / 100)
        WHERE Salary BETWEEN ? AND ?;
        """;

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, percentage);
            pstmt.setDouble(2, lowerBound);
            pstmt.setDouble(3, upperBound);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " rows updated.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setEmployeeSalary() {
        int empId;
        double newSalary;
        try {
            System.out.print("Enter employee ID: ");
            empId = scanner.nextInt();
            System.out.print("Enter new salary amount: ");
            newSalary = scanner.nextDouble();
        } catch (Exception e) {
            scanner.nextLine(); // Clear the invalid input
            System.out.println("Invalid input. Please enter numeric values.");
            return;
        }
        try {
            String sql = "UPDATE employees SET Salary = ? WHERE empid = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, newSalary);
            pstmt.setInt(2, empId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee salary updated successfully.");
            } else {
                System.out.println("Employee ID not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}