import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchForEmployee {
    private Connection conn;
    private Scanner scanner;
    private List<ISearch> searches;

    public SearchForEmployee(Connection conn, Scanner scanner) {
        this.conn = conn;
        this.scanner = scanner;
        this.searches = new ArrayList<>();
        
        searches.add(new NameSearch());
        searches.add(new SSNSearch());
        searches.add(new EmpidSearch());
    }

    public void searchHandling() {
        System.out.println("How would you like to search for your Employee?");

        for (int i = 0; i < searches.size(); i++) {
            System.out.println((i + 1) + ". " + searches.get(i).getSearchTypeName());
        }
        int searchType;
        try {
            System.out.print("\nPlease enter a number for a corresponding option: ");
            searchType = scanner.nextInt();
        }
        catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear the invalid input
            return; // Exit the method to allow the user to try again
        }

        if (searchType >= 1 && searchType <= searches.size()) {
            searches.get(searchType - 1).search(conn, scanner);
        } else {
            System.out.println("Invalid user input.");
        }
    }
}