import java.sql.Connection;
import java.util.Scanner;

public interface Report {
    /**
     * Generates the report based on the report type.
     * @param conn Database connection
     * @param scanner Scanner for user input
     */
    void generate(Connection conn, Scanner scanner);
    
    /**
     * @return The name/description of the report
     */
    String getReportName();
}