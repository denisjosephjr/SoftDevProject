import java.sql.Connection;
import java.util.Scanner;

public class ChangeEmployeeTableUI {
    private Connection conn;
    private Scanner scanner;
    private ChangeEmployeeTable tableChange;

    public ChangeEmployeeTableUI(Connection conn, Scanner scanner) {
        this.conn = conn;
        this.scanner = scanner;
        this.tableChange = new ChangeEmployeeTable(conn, scanner);
    }

    public void showMenu() {
        System.out.println("\nWhat would you like to change about the Employee Table?");

        System.out.println("1. Add SSN Column");
        System.out.println("2. Delete SSN Column");

        System.out.print("\nPlease enter a number for a corresponding option: ");
        int changeType = scanner.nextInt();

        switch (changeType) {
            case 1 -> tableChange.addSSNColumn();
            case 2 -> tableChange.deleteSSNColumn();
            default -> System.out.println("Invalid user input.");
        }
    }
}