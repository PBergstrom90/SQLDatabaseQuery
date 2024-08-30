package SystemIntegration;

import java.io.IOException;
import java.sql.SQLException;

public class Queries {

    public void showCustomers() throws SQLException, IOException {
        Repository repository = new Repository();
        repository.connectToAndQueryDatabase();
    }

    public void listProducts(){

    }

    public void createCustomerList(){

    }

    public void showTopProducts(){

    }

}
