import java.sql.Connection;
import java.util.Scanner;

public interface Search {
    /**
     * @param conn 
     * @param scanner 
     */
    void search(Connection conn, Scanner scanner);
    
    /**
     * @return 
     */
    String getSearchTypeName();
}