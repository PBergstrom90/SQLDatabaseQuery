package SystemIntegration;

import java.util.Scanner;

public class Menu {

    public void runMenu() {

        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);

        while(isRunning) {
            System.out.println(" ");
            System.out.println("--- WELCOME TO THE WEBSHOP ---");
            System.out.println("1. Choose a query for the database");
            System.out.println("2. Exit");
            System.out.print("ENTER: ");
            int option = scanner.nextInt();
            switch(option) {
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
        }
    }

    public void queryMenu() {
        Scanner scanner = new Scanner(System.in);
        Queries queries = new Queries();
        System.out.println("--- QUERY MENU ---");
        System.out.println("1. Show customers that have bought black Sweatpants in size 38");
        System.out.println("2. List amount of products per category");
        System.out.println("3. Create a list customerlist with total purchased value");
        System.out.println("4. Show Top 5 best selling products");
        System.out.println("5. Return to main menu");
        System.out.print("ENTER: ");
        int option = scanner.nextInt();
        switch(option) {
            case 1:
                queries.showCustomers();
                break;
            case 2:
                queries.listProducts();
                break;
            case 3:
                queries.createCustomerList();
                break;
            case 4:
                queries.showTopProducts();
                break;
            case 5:
                System.out.println("Returning...");
                break;
            default:
                System.out.println("INVALID ENTRY. TRY AGAIN.");
                break;
        }
    }
}
