// filepath: src/UpdateEmployeeSalaryUI.java
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * UI class for handling employee salary updates.
 */
public class UpdateEmployeeSalaryUI {

    private Connection conn;
    private Scanner scanner;
    private List<ISalaryUpdate> updates;

    public UpdateEmployeeSalaryUI(Connection conn, Scanner scanner) {
        this.conn = conn;
        this.scanner = scanner;
        this.updates = new ArrayList<>();
        
        updates.add(new PercentageIncrease());
        updates.add(new SetSalary());
    }

    public void updateSalaryHandling() {
        System.out.println("\nHow would you like to update employee salary?");
        
        for (int i = 0; i < updates.size(); i++) {
            System.out.println((i + 1) + ". " + updates.get(i).getUpdateName());
        }
        
        System.out.print("\nEnter choice: ");
        int userChoice = scanner.nextInt();
        
        if (userChoice >= 1 && userChoice <= updates.size()) {
            updates.get(userChoice - 1).update(conn, scanner);
        } else {
            System.out.println("Invalid option.");
        }
    }
}