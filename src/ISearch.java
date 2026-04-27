import java.sql.Connection;
import java.util.Scanner;

public interface ISearch {
    /**
     * Searches for employees based on the search type.
     * @param conn Database connection
     * @param scanner Scanner for user input
     */
    void search(Connection conn, Scanner scanner);
    
    /**
     * @return The name/description of the search type
     */
    String getSearchTypeName();
}