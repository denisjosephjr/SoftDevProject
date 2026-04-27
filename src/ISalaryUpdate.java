import java.sql.Connection;
import java.util.Scanner;

public interface ISalaryUpdate {
    /**
     * @param conn
     * @param scanner
     */
    void update(Connection conn, Scanner scanner);
    
    /**
     * @return
     */
    String getUpdateName();
}