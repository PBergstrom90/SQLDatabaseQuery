package SystemIntegration;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        Menu menu = new Menu();
        menu.runMenu();
    }
}