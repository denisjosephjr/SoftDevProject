import java.sql.Connection;
import java.util.Scanner;

public interface IReport {
    /**
     * @param conn 
     * @param scanner 
     */
    void generate(Connection conn, Scanner scanner);
    
    /**
     * @return The name/description of the report
     */
    String getReportName();
}