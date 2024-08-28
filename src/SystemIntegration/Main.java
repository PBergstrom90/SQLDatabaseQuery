package SystemIntegration;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        Properties p = new Properties();
        p.load(new FileInputStream("src/SystemIntegration/settings.properties"));

        System.out.println(p.getProperty("connectionString"));

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select id, name, address from customer")
        ){

            while(rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String address = rs.getString("address");
            }
        }

    }
}