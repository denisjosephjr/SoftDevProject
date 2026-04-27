import java.sql.Connection;
import java.util.Scanner;

public interface ISalaryUpdate {
    
    /**
     * Performs the salary update.
     * @param conn Database connection
     * @param scanner Scanner for user input
     */
    void update(Connection conn, Scanner scanner);
    
    /**
     * @return The name/description of the salary update
     */
    String getUpdateName();
}