import java.sql.Connection;
import java.util.Scanner;

public interface IDataUpdate {
    /**
     * @param conn 
     * @param scanner 
     * @param empId
     */
    void update(Connection conn, Scanner scanner, int empId);
    
    /**
     * @return 
     */
    String getUpdateName();
}