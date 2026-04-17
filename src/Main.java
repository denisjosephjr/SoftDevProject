/*
The following is just command information for testing with Git BASH or another terminal.

cd src
java -cp ".:../lib/mysql-connector-j-9.6.0.jar" Main
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main 
{
    public static void main(String[] args) 
    {
        try {
            // Line to help recognize the jar file
            Class.forName("com.mysql.cj.jdbc.Driver");

            // This variable creates the connection between the Java file and the MySQL database.
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/employeeData",
                "root",
                "password" // password, (placeholder for obvious reasons.)
            );
            
            // Welcomes user to the UX
            System.out.println("\nHello User, welcome to the Employee Data UX.\n");
            System.out.println("Please enter a number for a corresponding option: ");

            /*
            TODO:
            Display all options that the user can do (Report Generation, Update Data, etc.) with a corresponding number.
            Takes user input as a var, then checks var using branching to lead to another class with appropriate methods.
            */

            // Creates a statement that executes a query and saves the result. (Just test code for the database.)
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
            
            // prints the results of the SQL Query
            System.out.println("\n\nJust testing Database connection with Employee Names: ");
            while (rs.next()) {
                System.out.println(rs.getString("Fname"));
            }
            // Empty print statement for terminal spacing clarity.
            System.out.println("");

            // This method cuts the connections between the Java file and the MySQL database.
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}