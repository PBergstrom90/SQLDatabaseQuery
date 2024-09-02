package SystemIntegration;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Repository {

    private final Properties p = new Properties();

    public Repository() {
        try {
            p.load(new FileInputStream("src/SystemIntegration/settings.properties"));
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void showCustomersWithBlackPants() throws SQLException {
        String sqlQuery = "select distinct customer.name as Customer," +
                "sum(adding.quantity) as Amount from customer " +
                "inner join places on customer.id = places.customerId " +
                "inner join booking on places.bookingId = booking.id " +
                "inner join adding on booking.id = adding.bookingId " +
                "inner join product on adding.productId = product.id " +
                "where product.name = 'Sweatpants' and product.color = 'black' and product.size = '38' " +
                "group by customer.name order by Amount desc;";
        try (Connection con = DriverManager.getConnection(
                        p.getProperty("connectionString"),
                        p.getProperty("name"),
                        p.getProperty("password"));
                PreparedStatement stmt = con.prepareStatement(sqlQuery)) {
            ResultSet rs = stmt.executeQuery();

            // Printout - Header
            System.out.println(" ");
            System.out.printf("%-30s %-10s%n", "Customer", "Amount");
            System.out.println("-------------------------------------------");
            // Printout - Result
            while (rs.next()) {
                String customerName = rs.getString("Customer");
                int amount = rs.getInt("Amount");
                System.out.printf("%-30s %-10d%n", customerName, amount);
            }
            System.out.println("-------------------------------------------");
        }
    }

    public void listProducts() throws SQLException {
        String sqlQuery = "select category.name as Category, count(product.id) as NumberOfProducts from category " +
        "inner join identifies on category.id = identifies.categoryId " +
        "inner join product on identifies.productId = product.id " +
        "group by category.name " +
        "order by NumberOfProducts desc;";
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement(sqlQuery)) {
            ResultSet rs = stmt.executeQuery();

            // Printout - Header
            System.out.println(" ");
            System.out.printf("%-30s %-10s%n", "Category", "Number of Products");
            System.out.println("-------------------------------------------");
            // Printout - Result
            while (rs.next()) {
                String categoryName = rs.getString("Category");
                int NumberOfProducts = rs.getInt("NumberOfProducts");
                System.out.printf("%-30s %-10d%n", categoryName, NumberOfProducts);
            }
            System.out.println("-------------------------------------------");
        }
    }

    public void showCustomersTotalSpent() throws SQLException {
        String sqlQuery = "select customer.name as Customer, sum(product.price * adding.quantity) as TotalSpent from customer " +
        "inner join places on customer.id = places.customerId " +
        "inner join booking on places.bookingId = booking.id " +
        "inner join adding on booking.id = adding.bookingId " +
        "inner join product on adding.productId = product.id " +
        "group by customer.name " +
        "order by TotalSpent desc;";
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement(sqlQuery)) {
            ResultSet rs = stmt.executeQuery();

            // Printout - Header
            System.out.println(" ");
            System.out.printf("%-30s %-10s%n", "Customer", "Total Spent");
            System.out.println("-------------------------------------------");
            // Printout - Result
            while (rs.next()) {
                String customerName = rs.getString("Customer");
                int totalSpent = rs.getInt("TotalSpent");
                System.out.printf("%-30s %-10d%n", customerName, totalSpent);
            }
            System.out.println("-------------------------------------------");
        }
    }

    public void showTop5BestSelling() throws SQLException {
        String sqlQuery = "select product.name as Product, sum(adding.quantity) as TotalSold from product " +
        "inner join adding on product.id = adding.productId " +
        "group by product.name " +
        "order by TotalSold desc " +
        "limit 5;";
        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement(sqlQuery)) {
            ResultSet rs = stmt.executeQuery();

            // Printout - Header
            System.out.println(" ");
            System.out.printf("%-30s %-10s%n", "Product", "Total Sold");
            System.out.println("-------------------------------------------");
            // Printout - Result
            while (rs.next()) {
                String productName = rs.getString("Product");
                int totalSold = rs.getInt("TotalSold");
                System.out.printf("%-30s %-10d%n", productName, totalSold);
            }
            System.out.println("-------------------------------------------");
        }
    }

    // Testmethod, to access customers in the database.
    public void connectToAndQueryCustomerDatabase() throws IOException, SQLException {
        p.load(new FileInputStream("src/SystemIntegration/settings.properties"));

        try (Connection con = DriverManager.getConnection(
                        p.getProperty("connectionString"),
                        p.getProperty("name"),
                        p.getProperty("password"));
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select id, name, address from customer")
        ) {
            System.out.println(" ");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                System.out.println(id + " " + name + " - " + address);
            }
        }
    }
}
