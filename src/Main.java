/*
The following is just command information for testing with Git BASH or another terminal.

(Run with SoftDevProject as working directory)

Compile:
javac -d bin -cp "lib/*" src/*.java

Run:
java -cp "bin;lib/*" Main
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
            System.out.println("\nHello User, welcome to the Employee Data UX.");

            // User Input set.
            int userInput = 1;

            // Need a while loop for this segment eventually.
            while (userInput != 0) {

                // Displays all options that the user can do with a corresponding number.
                System.out.println("\n1. Generate Report");
                System.out.println("2. Change Employee Table");
                System.out.println("3. Search for Employee");
                System.out.println("4. Update Employee Data");
                System.out.println("5. Update Employee Salary");
                
                // Takes user input.
                System.out.print("\nPlease enter a number for a corresponding option (To quit, enter 0.): ");
                userInput = scanner.nextInt();

                // TODO: Define classes to link to this branching segment.
                // NOTE: This could have been a switch case statement.
                // NOTE: Need error handling for invalid user input.
                if (userInput == 1) {
                    ReportGenerator report1 = new ReportGenerator(conn, scanner);
                    report1.reportHandling();

                } else if (userInput == 2) {
                    ChangeEmployeeTable report2 = new ChangeEmployeeTable(conn, scanner);
                    report2.changeHandling();

                } else if (userInput == 3) {
                    SearchForEmployee report3 = new SearchForEmployee(conn, scanner);
                    report3.searchHandling();

                } else if (userInput == 4) {
                    UpdateEmployeeData report4 = new UpdateEmployeeData(conn, scanner);
                    report4.updateDataHandling();

                } else if (userInput == 5) {
                    UpdateEmployeeSalary report5 = new UpdateEmployeeSalary(conn, scanner);
                    report5.updateSalaryHandling();

                } else if (userInput == 0) {
                    System.out.println("Shutting down...");

                } else {
                    System.out.println("User input not valid.");
                    // TODO: Still need better error handling.
                }
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