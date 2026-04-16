/*
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

            // Creates a statement that executes a query and saves the result.
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
            
            // prints the results of the SQL Query
            while (rs.next()) {
                System.out.println(rs.getString("Fname"));
            }
            
            // This method cuts the connections between the Java file and the MySQL database.
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}