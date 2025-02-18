package SystemIntegration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public void runMenu() {
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);

        while(isRunning) {
            try {
                System.out.println(" ");
                System.out.println("--- WELCOME TO THE WEBSHOP ---");
                System.out.println("1. CHOOSE: a query for the database");
                System.out.println("2. EXIT.");
                System.out.print("ENTER: ");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        queryMenu();
                        break;
                    case 2:
                        isRunning = false;
                        System.out.println("System logging off...");
                        break;
                    default:
                        System.out.println("INVALID ENTRY. TRY AGAIN.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("INVALID ENTRY. Please enter a number.");
                scanner.next();
            } catch (SQLException e) {
                System.out.println("DATABASE ERROR" + e.getMessage());
            } catch (IOException e) {
                System.out.println("IO ERROR" + e.getMessage());
            } catch (Exception e) {
                System.out.println("ERROR" + e.getMessage());
            }
        }
    }

    public void queryMenu() throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);
        Repository repository = new Repository();
        try {
            System.out.println(" ");
            System.out.println("--- QUERY MENU ---");
            System.out.println("1. SHOW: customers that have bought black Sweetpants in size 38");
            System.out.println("2. SHOW: amount of products per category");
            System.out.println("3. SHOW: all customers with total purchased value");
            System.out.println("4. SHOW: top 5 best selling products");
            System.out.println("5. SHOW: all customers in the database");
            System.out.println("6. RETURN: to main menu");
            System.out.print("ENTER: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    repository.showCustomersWithBlackPants();
                    break;
                case 2:
                    repository.listProducts();
                    break;
                case 3:
                    repository.showCustomersTotalSpent();
                    break;
                case 4:
                    repository.showTop5BestSelling();
                    break;
                case 5:
                    repository.connectToAndQueryCustomerDatabase();
                    break;
                case 6:
                    System.out.println("Returning... ");
                    break;
                default:
                    System.out.println("INVALID ENTRY. TRY AGAIN.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("INVALID ENTRY: Please enter a number.");
            scanner.next();
        } catch (SQLException e) {
            System.out.println("DATABASE ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
