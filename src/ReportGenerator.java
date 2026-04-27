// Import for User Input
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReportGenerator {
    
    private Connection conn;
    private Scanner scanner; 
    private List<IReport> reports;

    public ReportGenerator(Connection conn, Scanner scanner) {
        this.conn = conn;
        this.scanner = scanner;
        this.reports = new ArrayList<>();
        
        reports.add(new EmployeeInformationReport());
        reports.add(new PayByJobTitleReport());
        reports.add(new PayByDivisionReport());
    }

    public void reportHandling() {

        System.out.println("\nWhat kind of report would you like?");

        for (int i = 0; i < reports.size(); i++) {
            System.out.println((i + 1) + ". " + reports.get(i).getReportName());
        }

        System.out.print("\nPlease enter a number for a corresponding option: ");
        int reportType = scanner.nextInt();

        if (reportType >= 1 && reportType <= reports.size()) {
            reports.get(reportType - 1).generate(conn, scanner);
        } else {
            System.out.println("Invalid user input.");
        }
    }
}