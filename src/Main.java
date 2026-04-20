/*
The following is just command information for testing with Git BASH or another terminal.

cd src
java -cp ".:../lib/mysql-connector-j-9.6.0.jar" Main
*/

// Imports for MySQL Integration
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// Import for User Input
import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        try {
            // Line to help recognize the jar file
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Creates scanner to take user input
            Scanner scanner = new Scanner(System.in);

            // Enter your MySQL password
            System.out.print("\nPlease enter your MySQL-DBeaver password: ");
            String password = scanner.nextLine();

            // This variable creates the connection between the Java file and the MySQL database.
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/employeeData",
                "root",
                password // MySQL-DBeaver Password.
            );
            

            // Welcomes user to the UX
            System.out.println("\nHello User, welcome to the Employee Data UX.\n");

            // Displays all options that the user can do with a corresponding number.
            System.out.println("1. Generate Report\n2. Change Employee Table\n3. Search for Employee");
            System.out.println("4. Update Employee Data\n5. Update Employee Salary");
            
            
            // Need a while loop for this segment eventually.
            // Takes user input.
            System.out.print("\nPlease enter a number for a corresponding option: ");
            int userInput = scanner.nextInt();

            // TODO: Define classes to link to this branching segment.
            // NOTE: This could have been a switch case statement.
            // NOTE: Need error handling for invalid user input.
            if (userInput == 1) {
                ReportGenerator report1 = new ReportGenerator(conn);
                report1.reportHandling();

            } else if (userInput == 2) {
                ChangeEmployeeTable report2 = new ChangeEmployeeTable(conn);
                // TODO: Method call

            } else if (userInput == 3) {
                SearchForEmployee report3 = new SearchForEmployee(conn);
                // TODO: Method call

            } else if (userInput == 4) {
                UpdateEmployeeData report4 = new UpdateEmployeeData(conn);
                // TODO: Method call

            } else if (userInput == 5) {
                UpdateEmployeeSalary report2 = new UpdateEmployeeSalary(conn);
                // TODO: Method call

            } else {
                System.out.println("User input not valid.");
                // TODO: Still need better error handling.
            }
            
            // Empty print statement for terminal spacing clarity.
            System.out.println("");
            // This method cuts the connection of the scanner (that takes user input).
            scanner.close();
            // This method cuts the connections between the Java file and the MySQL database.
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}