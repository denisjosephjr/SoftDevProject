import java.sql.Connection;
import java.sql.DriverManager;
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
                // NOTE: Need error handling for invalid user input.
                switch (userInput) {
                    case 1:
                        ReportGenerator report1 = new ReportGenerator(conn, scanner);
                        report1.reportHandling();
                        break;
                    case 2:
                        ChangeEmployeeTableUI report2 = new ChangeEmployeeTableUI(conn, scanner);
                        report2.showMenu();
                        break;
                    case 3:
                        SearchForEmployee report3 = new SearchForEmployee(conn, scanner);
                        report3.searchHandling();
                        break;
                    case 4:
                        UpdateEmployeeData report4 = new UpdateEmployeeData(conn, scanner);
                        report4.updateDataHandling();
                        break;
                    case 5:
                        UpdateEmployeeSalaryUI report5 = new UpdateEmployeeSalaryUI(conn, scanner);
                        report5.updateSalaryHandling();
                        break;
                    case 0:
                        System.out.println("Shutting down...");
                        break;
                    default:
                        System.out.println("User input invalid.");
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