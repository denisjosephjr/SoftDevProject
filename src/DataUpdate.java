import java.sql.Connection;
import java.util.Scanner;

public interface DataUpdate {
    
    /**
     * Performs the employee data update.
     * @param conn Database connection
     * @param scanner Scanner for user input
     * @param empId Employee ID to update
     */
    void update(Connection conn, Scanner scanner, int empId);
    
    /**
     * @return The name/description of the update
     */
    String getUpdateName();
}